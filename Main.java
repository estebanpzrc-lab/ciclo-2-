import model.Paciente;
import model.PacienteAsegurado;

public class Main {

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("       SISTEMA DE CITAS MEDICAS");
        System.out.println("====================================");

        System.out.println("\n--- CLASE PADRE: Paciente ---");
        Paciente paciente1 = new Paciente("Carlos Ramos Torres", 45, "Pediatria", 70);
        paciente1.mostrarInformacion();

        System.out.println("\n--- CLASE HIJA: PacienteAsegurado ---");
        PacienteAsegurado paciente2 = new PacienteAsegurado(
                "Maria Flores Rodriguez", 60, "Cardiologia", 120, "Rimac Seguros", 10);
        paciente2.mostrarInformacion();

        System.out.println("\n--- HERENCIA: metodos del padre usados por el hijo ---");
        System.out.println("Nombre (getNombre): " + paciente2.getNombre());
        System.out.println("Edad (getEdad): " + paciente2.getEdad());
        System.out.println("Tipo (obtenerTipoAtencion): " + paciente2.obtenerTipoAtencion());
        paciente2.setCostoConsulta(150);
        System.out.println("Nuevo costo (setCostoConsulta): S/ " + paciente2.getCostoConsulta());
        System.out.println("Nuevo total con descuento: S/ " + paciente2.calcularTotal());

        System.out.println("\n--- POLIMORFISMO: referencia Paciente, objetos distintos ---");
        Paciente[] pacientes = {
            new Paciente("Ana Soto Vega", 30, "Dermatologia", 90),
            new PacienteAsegurado("Luis Quispe Diaz", 35, "Medicina General", 80, "Pacifico Seguros", 10),
            new PacienteAsegurado("Rosa Paredes Luna", 68, "Traumatologia", 200, "Rimac Seguros", 20)
        };

        double recaudado = 0;
        for (Paciente p : pacientes) {
            System.out.println("\nTipo de objeto: " + p.getClass().getSimpleName());
            p.mostrarInformacion();
            recaudado += p.calcularTotal();
        }

        System.out.println("\n====================================");
        System.out.println("Total recaudado: S/ " + String.format("%.2f", recaudado));
        System.out.println("====================================");
    }
}
