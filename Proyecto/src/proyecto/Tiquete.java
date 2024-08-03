
package proyecto;
import java.time.LocalTime;

public class Tiquete {
    private String nombre;
    private int id;
    private int edad;
    private String tramite;
    private char tipo;
    private LocalTime horaCreacion;
    private LocalTime horaAtencion;

    public Tiquete(String nombre, int id, int edad, String tramite, char tipo) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.tramite = tramite;
        this.tipo = tipo;
        this.horaCreacion = LocalTime.now();
        this.horaAtencion = null;  
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

    public String getTramite() {
        return tramite;
    }

    public char getTipo() {
        return tipo;
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

    @Override
    public String toString() {
return "|Tiquete| \n" +
           "ID: " + id + "\n" +
           "Nombre: " + nombre + "\n" +
           "Edad: " + edad + "\n" +
           "Tipo: " + tipo + "\n" +
           "Trámite: " + tramite + "\n" +
           "Hora Creación: " + horaCreacion + "\n" +
           "Hora Atención: " + horaAtencion;
    }
}