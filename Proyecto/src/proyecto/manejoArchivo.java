/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//////////////////CONFIGURACION INICIAL//////////////////////////////
public class manejoArchivo {

    private static final String NOMBRE_ARCHIVO = "prod.txt";

    public static void escribirConfiguracionInicial(String nombreBanco, int cajasNormales, int cajaPreferencial,
            int cajaTramiteRapido) throws IOException {
        File archivo = new File(NOMBRE_ARCHIVO);

        try (FileWriter escribir = new FileWriter(archivo, true);
                PrintWriter linea = new PrintWriter(escribir)) {

            linea.println("Nombre del Banco: " + nombreBanco);
            linea.println("Cantidad de cajas normales: " + cajasNormales);
            linea.println("Cantidad de caja preferencial: " + cajaPreferencial);
            linea.println("Cantidad de caja para trámite rápido: " + cajaTramiteRapido);

        } catch (IOException ex) {
            throw new IOException("Error al escribir en el archivo", ex);
        }
    }

    public static boolean archivoExiste() {
        File archivo = new File(NOMBRE_ARCHIVO);
        return archivo.exists();
    }

    public static void crearConfiguracionInicial(String nombreBanco, int cajasTotales) throws IOException {
        if (!archivoExiste()) {
            try {
                File archivo = new File(NOMBRE_ARCHIVO);
                archivo.createNewFile();

                // Asignar cajas automáticamente
                int cajasNormales = cajasTotales - 2; // Se restan 2 para caja preferencial y rápida
                int cajaPreferencial = 1;
                int cajaTramiteRapido = 1;

                escribirConfiguracionInicial(nombreBanco, cajasNormales, cajaPreferencial, cajaTramiteRapido);

            } catch (IOException ex) {
                throw new IOException("Error al crear archivo de configuración inicial", ex);
            }
        }
    }

    public static String[] obtenerConfiguracion() throws IOException {
        String[] configuracion = new String[4];

        try (BufferedReader reader = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null && index < 4) {
                configuracion[index] = line;
                index++;
            }
        } catch (IOException ex) {
            throw new IOException("Error al leer configuración desde el archivo", ex);
        }

        return configuracion;
    }
}
