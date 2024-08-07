package proyecto;

import java.util.PriorityQueue;

public class ColaPrioridad {

    private PriorityQueue<Tiquete> colaPreferencial;
    private PriorityQueue<Tiquete> colaUnTramite;
    private PriorityQueue<Tiquete> colaDosOMasTramites;

    public ColaPrioridad() {
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
                System.out.println("Tipo de tiquete inválido: " + tipo);
                break;
        }

        System.out.println("Se ha creado el tiquete para " + nombre);
        imprimirDetalleAtencion(tiquete);
    }

    public void imprimirDetalleAtencion(Tiquete tiquete) {
        PriorityQueue<Tiquete> cola = obtenerColaSegunTipo(tiquete.getTipo());
        int personasDelante = cola.size() - 1;

        if (personasDelante > 0) {
            System.out.println("Está en la cola de espera con " + personasDelante + " persona(s) delante.");
        } else {
            System.out.println("¡Es su turno de atención!");
        }

        System.out.println("Debe ser atendido en la caja " + obtenerNumeroCaja(tiquete.getTipo()));
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
                return 1; // Ejemplo: Caja 1 para preferencial
            case 'A':
                return 2; // Ejemplo: Caja 2 para un solo trámite
            case 'B':
                return 3; // Ejemplo: Caja 3 para dos o más trámites
            default:
                return -1; // Caja no definida
        }
    }

    public void mostrarEstadoColas() {
        System.out.println("Estado de las colas:");
        System.out.println("Cola Preferencial:");
        for (Tiquete t : colaPreferencial) {
            System.out.println(t.toString());
        }
        System.out.println("Cola Un Trámite:");
        for (Tiquete t : colaUnTramite) {
            System.out.println(t.toString());
        }
        System.out.println("Cola Dos o Más Trámites:");
        for (Tiquete t : colaDosOMasTramites) {
            System.out.println(t.toString());
        }
    }
}