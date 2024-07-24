/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        this.colaPreferencial = new PriorityQueue<>();
        this.colaUnTramite = new PriorityQueue<>();
        this.colaDosOMasTramites = new PriorityQueue<>();
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
                JOptionPane.showMessageDialog(null,"Tipo de tiquete inválido: " + tipo);
                break;
        }

        JOptionPane.showMessageDialog(null,"Se ha creado el tiquete para " + nombre);
        imprimirDetalleAtencion(tiquete);
    }
    
    private void asignarTiqueteACaja(Tiquete tiquete, char tipo) {
        tiquete.setHoraAtencion(LocalTime.now());
        registrarEnReporte(tiquete);
        JOptionPane.showMessageDialog(null,
                "Tiquete atendido inmediatamente en la caja " + obtenerNumeroCaja(tipo),
                "Atención de Tiquete",
                JOptionPane.INFORMATION_MESSAGE);
        imprimirDetalleAtencion(tiquete);
    }
    
    public void atenderTiquete(char tipo) {
        PriorityQueue<Tiquete> cola = obtenerColaSegunTipo(tipo);
        if (cola != null) {
            if (!cola.isEmpty()) {
                Tiquete tiquete = cola.poll(); // Atender el primer tiquete en la cola
                tiquete.setHoraAtencion(LocalTime.now());
                registrarEnReporte(tiquete);
                JOptionPane.showMessageDialog(null,
                        "Tiquete atendido en la caja " + obtenerNumeroCaja(tipo),
                        "Atención de Tiquete",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "No hay tiquetes para atender en la caja " + obtenerNumeroCaja(tipo),
                        "Atención de Tiquete",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Tipo de tiquete no válido: " + tipo,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarEnReporte(Tiquete tiquete) {
        try (FileWriter fw = new FileWriter("reportes.txt", true); PrintWriter pw = new PrintWriter(fw)) {
            pw.println("Tiquete Atendido:");
            pw.println(tiquete.toString());
            pw.println("Hora de atención: " + tiquete.getHoraAtencion());
            pw.println("--------------------------");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al guardar el reporte: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
///////////////DA INFORMACION ///////////////////////
    public void imprimirDetalleAtencion(Tiquete tiquete) {
        PriorityQueue<Tiquete> cola = obtenerColaSegunTipo(tiquete.getTipo());
        int personasDelante = cola.size() - 1; 

        if (personasDelante > 0) {
            JOptionPane.showMessageDialog(null,"Está en la cola de espera con " + personasDelante + " persona(s) delante.");
        } else {
            JOptionPane.showMessageDialog(null,"¡Es su turno de atención!");
        }

        JOptionPane.showMessageDialog(null,"Debe ser atendido en la caja " + obtenerNumeroCaja(tiquete.getTipo()));
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
        JOptionPane.showMessageDialog(null,"Estado de las colas:");
       JOptionPane.showMessageDialog(null,"Cola Preferencial:");
        for (Tiquete t : colaPreferencial) {
            JOptionPane.showMessageDialog(null,t.toString());
        }
        JOptionPane.showMessageDialog(null,"Cola Un Trámite:");
        for (Tiquete t : colaUnTramite) {
            JOptionPane.showMessageDialog(null,t.toString());
        }
        JOptionPane.showMessageDialog(null,"Cola Dos o Más Trámites:");
        for (Tiquete t : colaDosOMasTramites) {
            JOptionPane.showMessageDialog(null,t.toString());
        }
    }
}