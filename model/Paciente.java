package model;

public class Paciente {

    protected String nombre;
    protected int edad;
    protected String especialidad;
    protected double costoConsulta;

    public Paciente(String nombre, int edad, String especialidad, double costoConsulta) {
        this.nombre = nombre;
        this.edad = edad;
        this.especialidad = especialidad;
        this.costoConsulta = costoConsulta;
    }

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

    public String obtenerTipoAtencion() {
        if (edad >= 60) {
            return "Atencion preferencial";
        }
        return "Atencion regular";
    }

    public double calcularTotal() {
        return costoConsulta;
    }

    public void mostrarInformacion() {
        System.out.println("Paciente: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Costo de consulta: S/ " + String.format("%.2f", costoConsulta));
        System.out.println("Tipo de atencion: " + obtenerTipoAtencion());
        System.out.println("Total a pagar: S/ " + String.format("%.2f", calcularTotal()));
    }
}
