package model;

public class PacienteAsegurado extends Paciente {

    private String aseguradora;
    private double porcentajeDescuento;

    public PacienteAsegurado(String nombre, int edad, String especialidad,
            double costoConsulta, String aseguradora, double porcentajeDescuento) {
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

    public double calcularDescuento() {
        return costoConsulta * porcentajeDescuento / 100;
    }

    @Override
    public double calcularTotal() {
        return costoConsulta - calcularDescuento();
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Seguro medico: " + aseguradora);
        System.out.println("Descuento (" + porcentajeDescuento + "%): S/ " + String.format("%.2f", calcularDescuento()));
    }
}
