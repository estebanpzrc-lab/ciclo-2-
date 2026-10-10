package menus;

import modelo.CitaMedica;
import modelo.Especialidad;
import modelo.Paciente;
import modelo.PacienteAsegurado;
import modelo.PacienteParticular;
import servicios.ServicioCitas;
import servicios.ServicioReportes;
import utilidades.Entrada;
import java.io.IOException;
import java.time.LocalDate;

public class MenuPrincipal {
    private final ServicioCitas servicioCitas;
    private final ServicioReportes servicioReportes;
    private final Entrada entrada;

    public MenuPrincipal(ServicioCitas servicioCitas, ServicioReportes servicioReportes, Entrada entrada) {
        this.servicioCitas = servicioCitas;
        this.servicioReportes = servicioReportes;
        this.entrada = entrada;
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n========================================");
            System.out.println("        SISTEMA DE CITAS MÉDICAS");
            System.out.println("========================================");
            System.out.println("1. Registrar una cita");
            System.out.println("2. Listar citas del día");
            System.out.println("3. Ver resumen del día");
            System.out.println("4. Cerrar sesión");
            opcion = entrada.leerEntero("Seleccione una opción: ", 1, 4);
            switch (opcion) {
                case 1: registrarCita(); break;
                case 2: listarCitas(); break;
                case 3: mostrarResumen(); break;
                case 4: System.out.println("Sesión cerrada."); break;
                default: break;
            }
        } while (opcion != 4);
    }

    private void registrarCita() {
        System.out.println("\n--- REGISTRO DE CITA ---");
        String nombre = entrada.leerTextoNoVacio("Nombre del paciente: ");
        int edad = entrada.leerEntero("Edad (1-120): ", 1, 120);
        System.out.println("1. Medicina General (S/ 80.00)");
        System.out.println("2. Pediatría (S/ 70.00)");
        System.out.println("3. Cardiología (S/ 120.00)");
        Especialidad especialidad = Especialidad.desdeOpcion(
                entrada.leerEntero("Seleccione especialidad: ", 1, 3));
        System.out.println("Turnos disponibles: 8:00 a 12:00");
        int hora = entrada.leerEntero("Seleccione la hora (8-12): ", 8, 12);
        LocalDate hoy = LocalDate.now();
        if (!servicioCitas.horaDisponible(hora, hoy)) {
            System.out.println("Ese turno ya está ocupado. Elija otro horario.");
            return;
        }
        System.out.println("1. Con seguro médico (10% de descuento)");
        System.out.println("2. Sin seguro");
        int opcionSeguro = entrada.leerEntero("Seleccione una opción: ", 1, 2);
        Paciente paciente;
        if (opcionSeguro == 1) {
            System.out.println("1. Rímac Seguros");
            System.out.println("2. Pacífico Seguros");
            System.out.println("3. Otra aseguradora");
            int opcionAseguradora = entrada.leerEntero("Seleccione aseguradora: ", 1, 3);
            String aseguradora;
            if (opcionAseguradora == 1) aseguradora = "Rímac Seguros";
            else if (opcionAseguradora == 2) aseguradora = "Pacífico Seguros";
            else aseguradora = entrada.leerTextoNoVacio("Nombre de la aseguradora: ");
            paciente = new PacienteAsegurado(nombre, edad, especialidad, aseguradora, 10.0);
        } else {
            paciente = new PacienteParticular(nombre, edad, especialidad);
        }
        try {
            CitaMedica cita = servicioCitas.registrar(paciente, hora, hoy);
            if (cita == null) {
                System.out.println("Ese turno ya está ocupado. No se registró la cita.");
                return;
            }
            System.out.println("\nCita registrada correctamente:");
            System.out.println(cita.resumen());
        } catch (IOException e) {
            System.out.println("No se pudo guardar la cita: " + e.getMessage());
        }
    }

    private void listarCitas() {
        System.out.println("\n--- CITAS REGISTRADAS ---");
        boolean hayCitas = false;
        for (CitaMedica cita : servicioCitas.listar()) {
            if (cita.getFecha().equals(LocalDate.now())) {
                hayCitas = true;
                System.out.println(cita.resumen());
            }
        }
        if (!hayCitas) System.out.println("No hay citas registradas para hoy.");
    }

    private void mostrarResumen() {
        System.out.println("\n" + servicioReportes.generarResumen(servicioCitas.listar()));
    }
}
