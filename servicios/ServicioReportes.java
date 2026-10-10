package servicios;

import modelo.CitaMedica;
import modelo.Especialidad;
import java.util.EnumMap;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;

public class ServicioReportes {
    public String generarResumen(List<CitaMedica> citas) {
        List<CitaMedica> delDia = new ArrayList<>();
        if (citas != null) {
            for (CitaMedica cita : citas) {
                if (cita.getFecha().equals(LocalDate.now())) delDia.add(cita);
            }
        }
        if (delDia.isEmpty()) return "Todavía no hay citas registradas para hoy.";
        int asegurados = 0;
        double recaudacion = 0.0;
        Map<Especialidad, Integer> porEspecialidad = new EnumMap<>(Especialidad.class);
        for (Especialidad especialidad : Especialidad.values()) porEspecialidad.put(especialidad, 0);
        for (CitaMedica cita : delDia) {
            if (cita.getPaciente().tieneSeguro()) asegurados++;
            recaudacion += cita.getPaciente().calcularTotal();
            Especialidad especialidad = cita.getPaciente().getEspecialidad();
            porEspecialidad.put(especialidad, porEspecialidad.get(especialidad) + 1);
        }
        StringBuilder texto = new StringBuilder();
        texto.append("RESUMEN DE CITAS").append(System.lineSeparator());
        texto.append("Total de citas: ").append(delDia.size()).append(System.lineSeparator());
        texto.append("Con seguro: ").append(asegurados).append(System.lineSeparator());
        texto.append("Particulares: ").append(delDia.size() - asegurados).append(System.lineSeparator());
        texto.append(String.format(Locale.US, "Total por cobrar: S/ %.2f%n", recaudacion));
        texto.append("Citas por especialidad:").append(System.lineSeparator());
        for (Especialidad especialidad : Especialidad.values()) {
            texto.append("- ").append(especialidad.getNombre()).append(": ")
                    .append(porEspecialidad.get(especialidad)).append(System.lineSeparator());
        }
        return texto.toString();
    }
}
