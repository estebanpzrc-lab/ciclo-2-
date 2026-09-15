import java.util.Scanner;

public class CitasMedicas {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        String nombre;
        int edad;
        int especialidad;
        int seguro;
        double costo = 0;
        double descuento = 0;
        double total;
        int repetir;

        do {
            System.out.println("====================================");
            System.out.println("       SISTEMA DE CITAS MEDICAS");
            System.out.println("====================================");

            System.out.println("\nHorarios de atencion disponibles para hoy:");
            for (int hora = 8; hora <= 12; hora++) {
                System.out.println(" - Turno disponible a las " + hora + ":00 hrs");
            }
            System.out.println("------------------------------------");

            System.out.print("\nIngrese nombre del paciente: ");
            nombre = entrada.nextLine();

            System.out.print("Ingrese edad: ");
            edad = entrada.nextInt();

            while (edad < 1) {
                System.out.println("Error: La edad no puede ser menor a 1 año.");
                System.out.print("Ingrese edad nuevamente: ");
                edad = entrada.nextInt();
            }

            System.out.println("\nSeleccione una especialidad:");
            System.out.println("1. Medicina General - S/ 80");
            System.out.println("2. Pediatria - S/ 70");
            System.out.println("3. Cardiologia - S/ 120");

            System.out.print("Ingrese opcion: ");
            especialidad = entrada.nextInt();

            while (especialidad < 1 || especialidad > 3) {
                System.out.println("Especialidad no valida. Intente de nuevo.");
                System.out.print("Ingrese opcion (1-3): ");
                especialidad = entrada.nextInt();
            }

            if (especialidad == 1) {
                costo = 80;
            } else if (especialidad == 2) {
                costo = 70;
            } else if (especialidad == 3) {
                costo = 120;
            }

            System.out.println("\n¿Cuenta con seguro medico?");
            System.out.println("1. Si");
            System.out.println("2. No");

            System.out.print("Ingrese opcion: ");
            seguro = entrada.nextInt();

            while (seguro < 1 || seguro > 2) {
                System.out.println("Opcion de seguro no valida. Intente de nuevo.");
                System.out.print("Ingrese opcion (1-2): ");
                seguro = entrada.nextInt();
            }

            if (seguro == 1) {
                descuento = costo * 0.10;
            } else if (seguro == 2) {
                descuento = 0;
            }

            total = costo - descuento;

            System.out.println("\n====================================");
            System.out.println("          RESUMEN DE LA CITA");
            System.out.println("====================================");

            System.out.println("Paciente: " + nombre);
            System.out.println("Edad: " + edad);
            System.out.println("Costo de consulta: S/ " + costo);
            System.out.println("Descuento: S/ " + descuento);
            System.out.println("Total a pagar: S/ " + total);

            if (edad >= 60) {
                System.out.println("Atencion preferencial.");
            } else {
                System.out.println("Atencion regular.");
            }

            System.out.println("Cita registrada correctamente.");
            System.out.println("====================================");

            System.out.println("\n¿Desea registrar otra cita?");
            System.out.println("1. Si");
            System.out.println("2. No, salir");
            System.out.print("Ingrese opcion: ");
            repetir = entrada.nextInt();
            entrada.nextLine();

        } while (repetir == 1);

        System.out.println("\n¡Sistema cerrado con exito. Tenga un buen dia!");

        entrada.close();
    }
}
