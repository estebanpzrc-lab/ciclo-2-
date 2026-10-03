package model;

public class Paciente {

    // Atributos encapsulados (private)
    private String nombre;
    private int edad;
    private String especialidad;
    private double costoConsulta;

    // Constructor
    public Paciente(String nombre, int edad, String especialidad, double costoConsulta) {
        this.nombre = nombre;
        this.edad = edad;
        this.especialidad = especialidad;
        this.costoConsulta = costoConsulta;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public double getCostoConsulta() {
        return costoConsulta;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setCostoConsulta(double costoConsulta) {
        this.costoConsulta = costoConsulta;
    }

    // Determina el tipo de atencion segun la edad
    public String obtenerTipoAtencion() {
        if (edad >= 60) {
            return "Atencion preferencial";
        }
        return "Atencion regular";
    }

    // Calcula el total a pagar (sin descuento)
    public double calcularTotal() {
        return costoConsulta;
    }

    // Muestra la informacion del paciente
    public void mostrarInformacion() {
        System.out.println("Paciente: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Costo de consulta: S/ " + costoConsulta);
        System.out.println("Total a pagar: S/ " + calcularTotal());
        System.out.println(obtenerTipoAtencion());
    }
}
