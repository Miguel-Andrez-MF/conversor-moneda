# Conversor de Monedas

## Descripción
Esta aplicación de consola permite convertir entre diferentes monedas utilizando tasas de cambio en tiempo real. El conversor soporta una amplia variedad de divisas internacionales y mantiene un historial de todas las conversiones realizadas.

## Características
- Conversión entre múltiples monedas usando códigos ISO (USD, EUR, GBP, JPY, etc.)
- Obtención de tasas de cambio actualizadas mediante API externa
- Historial persistente de todas las conversiones realizadas
- Interfaz de consola fácil de usar
- Visualización del historial de conversiones

## Requisitos
- Java 17 o superior
- Conexión a Internet (para obtener tasas de cambio actualizadas)

## Instalación
1. Clone este repositorio:
   ```
   git clone https://github.com/usuario/conversor-monedas.git
   ```


2. Ejecute la aplicación:
   ```
   java -cp "bin;lib/gson-2.10.1.jar" ConversorApp
   ```

## Uso
Al iniciar la aplicación, se mostrará un menú que permite:

1. Realizar una nueva conversión:
   - Ingrese la moneda de origen (ej. USD)
   - Ingrese la moneda de destino (ej. EUR)
   - Ingrese la cantidad a convertir

2. Ver el historial de conversiones previas

3. Salir de la aplicación

## Estructura del Proyecto
- `ConversorApp.java`: Clase principal con la interfaz de usuario
- `ConsultarMoneda.java`: Maneja la conexión con la API de tasas de cambio
- `Historial.java`: Gestiona el almacenamiento y visualización del historial
- `historial.txt`: Archivo generado automáticamente con las conversiones realizadas.

## API de Tasas de Cambio
El proyecto utiliza la API de [ExchangeRate-API](https://www.exchangerate-api.com/) para obtener tasas de cambio actualizadas. Se requiere una clave de API válida que debe ser configurada en el código.

## Limitaciones
- El número de solicitudes a la API puede estar limitado según el plan utilizado
- Se requiere conexión a Internet para obtener tasas actualizadas

## Autor
Miguel Andres Marin F.

Desarrollado como parte del programa Oracle Next Education + Alura Latam.
