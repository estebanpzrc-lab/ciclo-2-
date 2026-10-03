import model.Paciente;
import model.PacienteAsegurado;

public class Main {

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("       SISTEMA DE CITAS MEDICAS");
        System.out.println("====================================");

        // Objeto de la clase padre
        Paciente paciente1 = new Paciente("Carlos Ramos Torres", 45, "Pediatria", 70);
        paciente1.mostrarInformacion();

        System.out.println("************************************");

        // Objeto de la clase hija
        PacienteAsegurado paciente2 = new PacienteAsegurado(
                "Maria Flores Rodriguez", 60, "Cardiologia", 120, "Rimac Seguros", 10);
        paciente2.mostrarInformacion();

        System.out.println("************************************");

        // Polimorfismo: una referencia de tipo Paciente apunta a un objeto PacienteAsegurado
        Paciente paciente3 = new PacienteAsegurado(
                "Luis Quispe Diaz", 35, "Medicina General", 80, "Pacifico Seguros", 10);
        System.out.println("Total a pagar (polimorfismo): S/ " + paciente3.calcularTotal());
    }
}
