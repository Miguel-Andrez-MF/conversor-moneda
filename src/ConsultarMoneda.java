import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarMoneda {

    public static double obtenerTasa(String base, String destino) {
        String url = "https://v6.exchangerate-api.com/v6/a0d952b3eadb486728287c41/latest/" + base.toUpperCase();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

            // Verificar si la API respondió correctamente
            if (!json.get("result").getAsString().equals("success")) {
                String tipoError = json.has("error-type") ? json.get("error-type").getAsString() : "desconocido";
                System.out.println("Error en la respuesta de la API: \"" + tipoError + "\"");
                return -1;
            }

            JsonObject tasas = json.getAsJsonObject("conversion_rates");

            if (!tasas.has(destino.toUpperCase())) {
                System.out.println("Moneda de destino \"" + destino + "\" no encontrada.");
                return -1;
            }

            return tasas.get(destino.toUpperCase()).getAsDouble();

        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("La operación fue interrumpida.");
            Thread.currentThread().interrupt(); // buena práctica
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        return -1; // Valor de error
    }

}
