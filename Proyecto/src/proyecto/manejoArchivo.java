
package proyecto;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class manejoArchivo {
    private static final String NOMBRE_ARCHIVO = "prod.txt";

    public static boolean archivoExiste() {
        File archivo = new File(NOMBRE_ARCHIVO);
        return archivo.exists();
    }

    public static void crearConfiguracionInicial(String nombreBanco, int cajasTotales) throws IOException {
        FileWriter fileWriter = new FileWriter(NOMBRE_ARCHIVO);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("Nombre del banco: " + nombreBanco);
        printWriter.println("Total de cajas: " + cajasTotales);

        printWriter.close();
    }
}
