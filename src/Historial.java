import java.io.*;

public class Historial {
    private static final String ARCHIVO = "historial.txt";

    public static void guardar(String entrada) {
        try (FileWriter writer = new FileWriter(ARCHIVO, true)) {
            writer.write(entrada + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar historial: " + e.getMessage());
        }
    }

    public static void mostrar() {
        File archivo = new File(ARCHIVO);
        System.out.println("\n=== Historial de conversiones ===");

        if (!archivo.exists()) {
            System.out.println("No hay conversiones registradas.\n");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error al leer historial: " + e.getMessage());
        }

        System.out.println();
    }
}
