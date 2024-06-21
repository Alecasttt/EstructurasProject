/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import java.time.LocalTime;

public class Tiquete implements Comparable<Tiquete> {

    private String nombre;
    private int id;
    private int edad;
    private LocalTime horaCreacion;
    private LocalTime horaAtencion; 
    private String tramite;
    private char tipo;

    public Tiquete(String nombre, int id, int edad, String tramite, char tipo) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.horaCreacion = LocalTime.now();
        this.horaAtencion = null; 
        this.tramite = tramite;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public int getEdad() {
        return edad;
    }

    public LocalTime getHoraCreacion() {
        return horaCreacion;
    }

    public LocalTime getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(LocalTime horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    public String getTramite() {
        return tramite;
    }

    public char getTipo() {
        return tipo;
    }

    @Override
    public int compareTo(Tiquete otro) {
        // Orden por tipo de tiquete 
        if (this.tipo != otro.tipo) {
            return this.tipo - otro.tipo;
        } else {
            // Orden por hora de creación si son del mismo tipo
            return this.horaCreacion.compareTo(otro.horaCreacion);
        }
    }

    @Override
    public String toString() {
        return "Tiquete{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", edad=" + edad +
                ", horaCreacion=" + horaCreacion +
                ", horaAtencion=" + horaAtencion +
                ", tramite='" + tramite + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
