import java.util.Scanner;

public class CitasMedicas2 {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // =========================================================================
        // 1. MATRICES (Inspirado en la clase de Almacenes y Productos)
        // Filas: 3 Especialidades (1: Med. General, 2: Pediatría, 3: Cardiología)
        // Columnas: 2 Tipos de Atención / Seguro (0: Con Seguro, 1: Sin Seguro)
        // =========================================================================
        int[][] conteoMatriz = new int[3][2];
        int contadorCitasEconomicas = 0; // Para contar citas con costo <= S/ 80 (similar a stock bajo)

        // =========================================================================
        // 2. ARREGLOS UNIDIMENSIONALES (Inspirado en Arreglos2 y Arreglos3)
        // Guardaremos la información de hasta 10 citas para promedios y búsquedas
        // =========================================================================
        String[] pacientesNombres = new String[10];
        int[] pacientesEdades = new int[10];
        double[] citasTotales = new double[10];

        int contadorCitas = 0; // Contador global de citas del día
        int repetir;

        do {
            System.out.println("====================================");
            System.out.println("       SISTEMA DE CITAS MEDICAS     ");
            System.out.println("====================================");

            // Horarios de atención
            System.out.println("\nHorarios de atencion disponibles para hoy:");
            for (int hora = 8; hora <= 12; hora++) {
                System.out.println(" - Turno disponible a las " + hora + ":00 hrs");
            }
            System.out.println("------------------------------------");

            // Entrada de datos del paciente
            System.out.print("\nIngrese nombre del paciente: ");
            String nombre = entrada.nextLine();

            System.out.print("Ingrese edad: ");
            int edad = entrada.nextInt();

            // Validación de edad
            while (edad < 1) {
                System.out.println("Error: La edad no puede ser menor a 1 año.");
                System.out.print("Ingrese edad nuevamente: ");
                edad = entrada.nextInt();
            }

            // Selección de Especialidad
            System.out.println("\nSeleccione una especialidad:");
            System.out.println("1. Medicina General - S/ 80");
            System.out.println("2. Pediatria - S/ 70");
            System.out.println("3. Cardiologia - S/ 120");

            System.out.print("Ingrese opcion: ");
            int especialidad = entrada.nextInt();

            while (especialidad < 1 || especialidad > 3) {
                System.out.println("Especialidad no valida. Intente de nuevo.");
                System.out.print("Ingrese opcion (1-3): ");
                especialidad = entrada.nextInt();
            }

            // Asignación de costo según especialidad
            double costo = 0;
            if (especialidad == 1) {
                costo = 80;
            } else if (especialidad == 2) {
                costo = 70;
            } else if (especialidad == 3) {
                costo = 120;
            }

            // Selección de Seguro Médico
            System.out.println("\n¿Cuenta con seguro medico?");
            System.out.println("1. Si (10% descuento)");
            System.out.println("2. No");

            System.out.print("Ingrese opcion: ");
            int seguro = entrada.nextInt();

            while (seguro < 1 || seguro > 2) {
                System.out.println("Opcion de seguro no valida. Intente de nuevo.");
                System.out.print("Ingrese opcion (1-2): ");
                seguro = entrada.nextInt();
            }

            // =========================================================================
            // 3. DEBUGGING / CÁLCULOS PASO A PASO (Inspirado en clase debugging1)
            // =========================================================================
            double subtotal = costo;
            double descuento = 0;

            if (seguro == 1) {
                descuento = subtotal * 0.10;
            } else if (seguro == 2) {
                descuento = 0;
            }

            double total = subtotal - descuento;

            // REGISTRO EN LA MATRIZ:
            // especialidad - 1 (filas 0 a 2) y seguro - 1 (columnas 0 a 1)
            conteoMatriz[especialidad - 1][seguro - 1]++;

            // Conteo similar al "stock bajo": Contar citas con total <= S/ 80
            if (total <= 80) {
                contadorCitasEconomicas++;
            }

            // REGISTRO EN LOS ARREGLOS UNIDIMENSIONALES:
            if (contadorCitas < pacientesNombres.length) {
                pacientesNombres[contadorCitas] = nombre;
                pacientesEdades[contadorCitas] = edad;
                citasTotales[contadorCitas] = total;
                contadorCitas++;
            }

            // Resumen de la Cita individual
            System.out.println("\n====================================");
            System.out.println("          RESUMEN DE LA CITA        ");
            System.out.println("====================================");
            System.out.println("Paciente: " + nombre);
            System.out.println("Edad: " + edad);
            System.out.println("Subtotal: S/ " + subtotal);
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
            entrada.nextLine(); // Limpiar el salto de línea

        } while (repetir == 1);

        // =========================================================================
        // SECCIÓN FINAL: IMPLEMENTACIÓN DE LO APRENDIDO EN CLASE
        // =========================================================================

        System.out.println("\n====================================");
        System.out.println("    REPORTE Y OPERACIONES DEL DIA   ");
        System.out.println("====================================");

        // A. CÁLCULO DE PROMEDIO DE EDADES (Clase Arreglos2)
        if (contadorCitas > 0) {
            int sumaEdades = 0;
            double sumaRecaudada = 0;

            for (int i = 0; i < contadorCitas; i++) {
                sumaEdades += pacientesEdades[i];
                sumaRecaudada += citasTotales[i];
            }

            double promedioEdad = (double) sumaEdades / contadorCitas;
            double promedioGasto = sumaRecaudada / contadorCitas;

            System.out.println("\n--- ESTADISTICAS DE ARREGLOS ---");
            System.out.println("Total de citas atendiadas hoy: " + contadorCitas);
            System.out.println("Promedio de edad de los pacientes: " + promedioEdad + " años");
            System.out.println("Promedio de cobro por cita: S/ " + promedioGasto);

            // B. BÚSQUEDA DE PACIENTE / EDAD (Clase Arreglos3)
            System.out.print("\nIngrese la edad de un paciente que desea buscar: ");
            int edadBuscada = entrada.nextInt();

            boolean encontrado = false;
            for (int i = 0; i < contadorCitas; i++) {
                if (pacientesEdades[i] == edadBuscada) {
                    encontrado = true;
                    break; // Detener la búsqueda al encontrar la coincidencia
                }
            }

            if (encontrado) {
                System.out.println("¡SÍ existe al menos un paciente registrado con " + edadBuscada + " años!");
            } else {
                System.out.println("NO se encontraron pacientes registrados con " + edadBuscada + " años.");
            }
        }

        // C. REPORTES DE MATRICES (Clase Matrices - Almacenes/Productos)
        System.out.println("\n--- MATRIZ DE CITAS REGISTRADAS ---");
        System.out.println("Filas: Especialidades (1:Med. General, 2:Pediatria, 3:Cardiologia)");
        System.out.println("Columnas: Seguro (Columna 1: Con Seguro | Columna 2: Sin Seguro)\n");

        for (int i = 0; i < 3; i++) {
            System.out.print("Especialidad " + (i + 1) + ": ");
            for (int j = 0; j < 2; j++) {
                System.out.print("[" + conteoMatriz[i][j] + "] ");
            }
            System.out.println(); // Salto de línea por fila
        }

        System.out.println("\nTotal de citas con tarifa economica menores a S/ 80): " + contadorCitasEconomicas);

        System.out.println("\n¡Sistema cerrado con exito. Tenga un buen dia!");

        entrada.close();
    }
}