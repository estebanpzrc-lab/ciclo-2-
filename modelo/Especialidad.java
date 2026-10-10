package modelo;

import java.io.Serializable;

public enum Especialidad implements Serializable {
    MEDICINA_GENERAL("Medicina General", 80.0),
    PEDIATRIA("Pediatría", 70.0),
    CARDIOLOGIA("Cardiología", 120.0);

    private final String nombre;
    private final double costo;

    Especialidad(String nombre, double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    public String getNombre() { return nombre; }
    public double getCosto() { return costo; }

    public static Especialidad desdeOpcion(int opcion) {
        switch (opcion) {
            case 1: return MEDICINA_GENERAL;
            case 2: return PEDIATRIA;
            case 3: return CARDIOLOGIA;
            default: throw new IllegalArgumentException("La especialidad debe estar entre 1 y 3.");
        }
    }
}
