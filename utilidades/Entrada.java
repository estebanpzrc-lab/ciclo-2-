package utilidades;

import java.util.Scanner;

public class Entrada {
    private final Scanner scanner;

    public Entrada(Scanner scanner) {
        if (scanner == null) throw new IllegalArgumentException("Scanner no puede ser nulo.");
        this.scanner = scanner;
    }

    public String leerTextoNoVacio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim();
            if (!valor.isEmpty()) return valor;
            System.out.println("El dato es obligatorio. Intente nuevamente.");
        }
    }

    public int leerEntero(String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim();
            try {
                int numero = Integer.parseInt(valor);
                if (numero >= minimo && numero <= maximo) return numero;
            } catch (NumberFormatException ignored) { }
            System.out.println("Ingrese un número entre " + minimo + " y " + maximo + ".");
        }
    }
}
