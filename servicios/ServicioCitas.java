package servicios;

import interfaces.RepositorioCitas;
import modelo.CitaMedica;
import modelo.Paciente;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServicioCitas {
    private final RepositorioCitas repositorio;
    private final List<CitaMedica> citas;

    public ServicioCitas(RepositorioCitas repositorio) throws IOException, ClassNotFoundException {
        if (repositorio == null) throw new IllegalArgumentException("Debe indicar un repositorio.");
        this.repositorio = repositorio;
        this.citas = new ArrayList<>(repositorio.cargar());
    }

    public boolean horaDisponible(int hora, LocalDate fecha) {
        for (CitaMedica cita : citas) {
            if (cita.getHora() == hora && cita.getFecha().equals(fecha)) return false;
        }
        return true;
    }

    public CitaMedica registrar(Paciente paciente, int hora, LocalDate fecha) throws IOException {
        if (!horaDisponible(hora, fecha)) return null;
        String codigo = String.format("C-%04d", siguienteNumero());
        CitaMedica cita = new CitaMedica(codigo, paciente, hora, fecha);
        citas.add(cita);
        try {
            repositorio.guardar(citas);
        } catch (IOException e) {
            citas.remove(cita);
            throw e;
        }
        return cita;
    }

    private int siguienteNumero() {
        int maximo = 0;
        for (CitaMedica cita : citas) {
            String codigo = cita.getCodigo();
            if (codigo.startsWith("C-")) {
                try { maximo = Math.max(maximo, Integer.parseInt(codigo.substring(2))); }
                catch (NumberFormatException ignored) { }
            }
        }
        return maximo + 1;
    }

    public List<CitaMedica> listar() {
        return Collections.unmodifiableList(new ArrayList<>(citas));
    }
}
