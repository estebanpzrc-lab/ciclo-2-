package modelo;

public class PacienteParticular extends Paciente {
    private static final long serialVersionUID = 1L;

    public PacienteParticular(String nombre, int edad, Especialidad especialidad) {
        super(nombre, edad, especialidad);
    }

    @Override
    public double calcularTotal() {
        return getCostoConsulta();
    }
}
