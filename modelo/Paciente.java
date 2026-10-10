package modelo;

import java.io.Serializable;

public abstract class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String nombre;
    private final int edad;
    private final Especialidad especialidad;

    protected Paciente(String nombre, int edad, Especialidad especialidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (edad < 1) {
            throw new IllegalArgumentException("La edad debe ser al menos 1.");
        }
        if (especialidad == null) {
            throw new IllegalArgumentException("Debe seleccionar una especialidad.");
        }
        this.nombre = nombre.trim();
        this.edad = edad;
        this.especialidad = especialidad;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public Especialidad getEspecialidad() { return especialidad; }
    public double getCostoConsulta() { return especialidad.getCosto(); }

    public String getTipoAtencion() {
        return edad >= 60 ? "Preferencial" : "Regular";
    }

    public boolean tieneSeguro() { return false; }
    public String getCobertura() { return "Particular"; }
    public double calcularDescuento() { return 0.0; }

    public abstract double calcularTotal();
}
