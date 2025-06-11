import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ConversorApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Conversor de Monedas ===");
            System.out.println("Códigos de monedas más comunes:");
            System.out.printf("%-10s %-25s %-10s %-25s%n",
                    "USD", "Dólar estadounidense", "EUR", "Euro");
            System.out.printf("%-10s %-25s %-10s %-25s%n",
                    "GBP", "Libra esterlina", "JPY", "Yen japonés");
            System.out.printf("%-10s %-25s %-10s %-25s%n",
                    "MXN", "Peso mexicano", "COP", "Peso colombiano");
            System.out.printf("%-10s %-25s %-10s %-25s%n",
                    "BRL", "Real brasileño", "ARS", "Peso argentino");
            System.out.println("... puedes usar cualquier código ISO válido.\n");


            System.out.print("Escribe la moneda de ORIGEN (por ejemplo, USD): ");
            String monedaOrigen = scanner.nextLine().toUpperCase();

            System.out.print("Escribe la moneda de DESTINO (por ejemplo, EUR): ");
            String monedaDestino = scanner.nextLine().toUpperCase();

            System.out.print("Ingresa la cantidad en " + monedaOrigen + ": ");
            double cantidad = Double.parseDouble(scanner.nextLine());

            try {
                double tasa = ConsultarMoneda.obtenerTasa(monedaOrigen, monedaDestino);

                if (tasa <= 0) {
                    System.out.println("Conversión cancelada. La tasa obtenida no es válida.\n");
                    continue;
                }

                double resultado = cantidad * tasa;

                LocalDateTime ahora = LocalDateTime.now();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String fechaHora = ahora.format(formato);

                String registro = String.format("[%s] %.2f %s => %.2f %s (Tasa: %.4f)",
                        fechaHora, cantidad, monedaOrigen, resultado, monedaDestino, tasa);

                System.out.println("\nConversión realizada con éxito");
                System.out.println("-----------------------------------------");
                System.out.println("Fecha y hora: " + fechaHora);
                System.out.printf("Cantidad:     %.2f %s%n", cantidad, monedaOrigen);
                System.out.printf("Resultado:    %.2f %s%n", resultado, monedaDestino);
                System.out.printf("Tasa usada:   %.4f%n", tasa);
                System.out.println("-----------------------------------------\n");


                Historial.guardar(registro);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("¿Otra conversión (s), ver historial (h), salir (n)? ");
            String opcion = scanner.nextLine().trim().toLowerCase();

            if (opcion.equals("h")) {
                Historial.mostrar();
            } else if (opcion.equals("n")) {
                break;
            }
        }

        System.out.println("Gracias por usar el conversor.");
    }

}
