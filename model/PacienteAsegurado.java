package model;

public class PacienteAsegurado extends Paciente {

    private String aseguradora;
    private double porcentajeDescuento;

    // Constructor: usa super() para inicializar los atributos de la clase padre
    public PacienteAsegurado(String nombre, int edad, String especialidad,
            double costoConsulta, String aseguradora,
            double porcentajeDescuento) {
        super(nombre, edad, especialidad, costoConsulta);
        this.aseguradora = aseguradora;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    // Metodo propio de la clase hija
    public double calcularDescuento() {
        return getCostoConsulta() * porcentajeDescuento / 100;
    }

    // Polimorfismo: se sobrescribe el calculo del total aplicando el descuento
    @Override
    public double calcularTotal() {
        return getCostoConsulta() - calcularDescuento();
    }

    // Polimorfismo: se sobrescribe mostrarInformacion() y se reutiliza el de la
    // clase padre
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Seguro medico: " + aseguradora);
        System.out.println("Descuento (" + porcentajeDescuento + "%): S/ " + calcularDescuento());
    }
}
