
package proyecto;

import javax.swing.JOptionPane;
import java.io.IOException;

public class Proyecto {

    private static final String NOMBRE_ARCHIVO = "prod.txt";
    private static GestorTiquetes gestorTiquetes;

    public static void main(String[] args) {
        boolean salir = false;
        gestorTiquetes = new GestorTiquetes(0, 0, 0);

        while (!salir) {
            try {
                // Menú de opciones
                String opcionStr = JOptionPane.showInputDialog(
                        "Selecciona una opción:\n" +
                        "1. Configuración inicial\n" +
                        "2. Crear tiquete\n" +
                        "3. Mostrar estado de colas\n" +
                        "4. Salir"
                );

                int opcion = Integer.parseInt(opcionStr);

                switch (opcion) {
                    case 1:
                        configuracionInicial();
                        break;
                    case 2:
                        crearTiquete();
                        break;
                    case 3:
                        mostrarEstadoColas();
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Inténtalo de nuevo.");
                        break;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error en la operación: " + ex.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void configuracionInicial() throws IOException {
        if (!manejoArchivo.archivoExiste()) {
            String nombreBanco = JOptionPane.showInputDialog("Ingrese el nombre del banco:");
            String cajasTotalesStr = JOptionPane.showInputDialog("Ingrese la cantidad total de cajas:");
            int cajasTotales = Integer.parseInt(cajasTotalesStr);

            manejoArchivo.crearConfiguracionInicial(nombreBanco, cajasTotales);
            JOptionPane.showMessageDialog(null, "Configuración inicial completada.");
        } else {
            JOptionPane.showMessageDialog(null, "Ya existe una configuración inicial.");
        }
    }

    private static void crearTiquete() {
        if (manejoArchivo.archivoExiste()) {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
            String idStr = JOptionPane.showInputDialog("Ingrese el ID del cliente:");
            int id = Integer.parseInt(idStr);
            String edadStr = JOptionPane.showInputDialog("Ingrese la edad del cliente:");
            int edad = Integer.parseInt(edadStr);
            String tramite = JOptionPane.showInputDialog("Ingrese el tipo de trámite:");
            String tipoStr = JOptionPane.showInputDialog("Ingrese el tipo de tiquete (P, A o B):");
            char tipo = tipoStr.charAt(0);

            gestorTiquetes.crearTiquete(nombre, id, edad, tramite, tipo);
        } else {
            JOptionPane.showMessageDialog(null, "Primero debe realizar la configuración inicial.");
        }
    }

    private static void mostrarEstadoColas() {
        if (manejoArchivo.archivoExiste()) {
            gestorTiquetes.mostrarEstadoColas();
        } else {
            JOptionPane.showMessageDialog(null, "Primero debe realizar la configuración inicial.");
        }
    }
}