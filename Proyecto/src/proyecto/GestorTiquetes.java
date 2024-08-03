package proyecto;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.PriorityQueue;
import javax.swing.JOptionPane;

public class GestorTiquetes {

    private PriorityQueue<Tiquete> colaPreferencial;
    private PriorityQueue<Tiquete> colaUnTramite;
    private PriorityQueue<Tiquete> colaDosOMasTramites;

    public GestorTiquetes(int cajasNormales, int cajaPreferencial, int cajaTramiteRapido) {
        this.colaPreferencial = new PriorityQueue<>((t1, t2) -> t1.getHoraCreacion().compareTo(t2.getHoraCreacion()));
        this.colaUnTramite = new PriorityQueue<>((t1, t2) -> t1.getHoraCreacion().compareTo(t2.getHoraCreacion()));
        this.colaDosOMasTramites = new PriorityQueue<>((t1, t2) -> t1.getHoraCreacion().compareTo(t2.getHoraCreacion()));
    }

    public void crearTiquete(String nombre, int id, int edad, String tramite, char tipo) {
        Tiquete tiquete = new Tiquete(nombre, id, edad, tramite, tipo);
        
        switch (tipo) {
            case 'P':
                colaPreferencial.offer(tiquete);
                break;
            case 'A':
                colaUnTramite.offer(tiquete);
                break;
            case 'B':
                colaDosOMasTramites.offer(tiquete);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Tipo de tiquete inválido: " + tipo);
                break;
        }

        imprimirDetalleAtencion(tiquete);
    }

    private void imprimirDetalleAtencion(Tiquete tiquete) {
        PriorityQueue<Tiquete> cola = obtenerColaSegunTipo(tiquete.getTipo());
        int personasDelante = cola.size() - 1;

        if (personasDelante > 0) {
            JOptionPane.showMessageDialog(null, "Está en la cola de espera con " + personasDelante + " persona(s) delante.");
        } else {
            JOptionPane.showMessageDialog(null, "¡Es su turno de atención!");
        }

        JOptionPane.showMessageDialog(null, "Debe ser atendido en la caja " + obtenerNumeroCaja(tiquete.getTipo()));
    }

    private PriorityQueue<Tiquete> obtenerColaSegunTipo(char tipo) {
        switch (tipo) {
            case 'P':
                return colaPreferencial;
            case 'A':
                return colaUnTramite;
            case 'B':
                return colaDosOMasTramites;
            default:
                return null;
        }
    }

    private int obtenerNumeroCaja(char tipo) {
        switch (tipo) {
            case 'P':
                return 1;
            case 'A':
                return 2;
            case 'B':
                return 3;
            default:
                return -1;
        }
    }



    public void reasignarTiquete(int id) {
        Tiquete tiqueteReasignado = null;
        
        for (Tiquete t : colaPreferencial) {
            if (t.getId() == id) {
                tiqueteReasignado = t;
                colaPreferencial.remove(t);
                break;
            }
        }

        if (tiqueteReasignado == null) {
            for (Tiquete t : colaUnTramite) {
                if (t.getId() == id) {
                    tiqueteReasignado = t;
                    colaUnTramite.remove(t);
                    break;
                }
            }
        }

        if (tiqueteReasignado == null) {
            for (Tiquete t : colaDosOMasTramites) {
                if (t.getId() == id) {
                    tiqueteReasignado = t;
                    colaDosOMasTramites.remove(t);
                    break;
                }
            }
        }

        if (tiqueteReasignado != null) {
            JOptionPane.showMessageDialog(null, "Tiquete con ID " + id + " ha sido reasignado.");
            crearTiquete(tiqueteReasignado.getNombre(), tiqueteReasignado.getId(), tiqueteReasignado.getEdad(), tiqueteReasignado.getTramite(), tiqueteReasignado.getTipo());
        } else {
            JOptionPane.showMessageDialog(null, "Tiquete con ID " + id + " no encontrado.");
        }
    }

    public void mostrarEstadoColas() {
 JOptionPane.showMessageDialog(null, "Estado de las colas:");
        JOptionPane.showMessageDialog(null, "Cola Preferencial:");
        for (Tiquete t : colaPreferencial) {
            JOptionPane.showMessageDialog(null, t.toString() + " - Creación: " + t.getHoraCreacion() + ", Atención: " + t.getHoraAtencion());
        }
        JOptionPane.showMessageDialog(null, "Cola Un Trámite:");
        for (Tiquete t : colaUnTramite) {
            JOptionPane.showMessageDialog(null, t.toString() + " - Creación: " + t.getHoraCreacion() + ", Atención: " + t.getHoraAtencion());
        }
        JOptionPane.showMessageDialog(null, "Cola Dos o Más Trámites:");
        for (Tiquete t : colaDosOMasTramites) {
            JOptionPane.showMessageDialog(null, t.toString() + " - Creación: " + t.getHoraCreacion() + ", Atención: " + t.getHoraAtencion());
    }
    }
}
