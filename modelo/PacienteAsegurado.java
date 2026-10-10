package modelo;

import interfaces.Asegurable;
import interfaces.Descontable;
import java.util.Locale;

public class PacienteAsegurado extends Paciente implements Descontable, Asegurable {
    private static final long serialVersionUID = 1L;
    private final String aseguradora;
    private final double porcentajeDescuento;

    public PacienteAsegurado(String nombre, int edad, Especialidad especialidad,
            String aseguradora, double porcentajeDescuento) {
        super(nombre, edad, especialidad);
        if (aseguradora == null || aseguradora.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe indicar la aseguradora.");
        }
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 100.");
        }
        this.aseguradora = aseguradora.trim();
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public String getAseguradora() { return aseguradora; }

    @Override
    public double getPorcentajeDescuento() { return porcentajeDescuento; }

    @Override
    public boolean tieneSeguro() { return true; }

    @Override
    public String getCobertura() { return aseguradora; }

    @Override
    public double calcularDescuento() {
        return getCostoConsulta() * porcentajeDescuento / 100.0;
    }

    @Override
    public double calcularTotal() {
        return getCostoConsulta() - calcularDescuento();
    }

    public String getDescuentoFormateado() {
        return String.format(Locale.US, "%.0f%%", porcentajeDescuento);
    }
}
