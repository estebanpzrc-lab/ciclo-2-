package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CitaMedica implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String codigo;
    private final Paciente paciente;
    private final int hora;
    private final LocalDate fecha;

    public CitaMedica(String codigo, Paciente paciente, int hora, LocalDate fecha) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de cita no puede estar vacío.");
        }
        if (paciente == null) {
            throw new IllegalArgumentException("La cita debe tener un paciente.");
        }
        if (hora < 8 || hora > 12) {
            throw new IllegalArgumentException("El horario debe estar entre las 8 y las 12.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede estar vacía.");
        }
        this.codigo = codigo.trim();
        this.paciente = paciente;
        this.hora = hora;
        this.fecha = fecha;
    }

    public String getCodigo() { return codigo; }
    public Paciente getPaciente() { return paciente; }
    public int getHora() { return hora; }
    public LocalDate getFecha() { return fecha; }

    public String resumen() {
        String seguro = paciente.tieneSeguro()
                ? paciente.getCobertura() + " (descuento S/ " + String.format(Locale.US, "%.2f", paciente.calcularDescuento()) + ")"
                : "Sin seguro";
        return String.format(Locale.US,
                "%s | %s %02d:00 | %s, %d años | %s | %s | Total S/ %.2f | Atención %s",
                codigo, fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), hora,
                paciente.getNombre(), paciente.getEdad(), paciente.getEspecialidad().getNombre(),
                seguro, paciente.calcularTotal(), paciente.getTipoAtencion());
    }
}
