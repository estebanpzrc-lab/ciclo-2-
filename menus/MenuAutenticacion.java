package menus;

import modelo.Usuario;
import servicios.ServicioUsuarios;
import utilidades.Entrada;
import java.io.IOException;

public class MenuAutenticacion {
    private final ServicioUsuarios servicio;
    private final Entrada entrada;
    public MenuAutenticacion(ServicioUsuarios servicio, Entrada entrada) {
        this.servicio = servicio;
        this.entrada = entrada;
    }

    public Usuario autenticar() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("       SISTEMA DE CITAS MÉDICAS");
            System.out.println("========================================");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Salir");
            int opcion = entrada.leerEntero("Seleccione una opción: ", 1, 3);
            try {
                if (opcion == 1) {
                    String correo = entrada.leerTextoNoVacio("Correo: ");
                    char[] clave = entrada.leerTextoNoVacio("Contraseña: ").toCharArray();
                    Usuario usuario;
                    try {
                        usuario = servicio.iniciarSesion(correo, clave);
                    } finally {
                        java.util.Arrays.fill(clave, '\0');
                    }
                    if (usuario != null) {
                        System.out.println("Bienvenido/a, " + usuario.getNombre() + ".");
                        return usuario;
                    }
                    System.out.println("Correo o contraseña incorrectos.");
                } else if (opcion == 2) {
                    registrar();
                } else {
                    return null;
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("No se pudo acceder a las cuentas: " + e.getMessage());
            }
        }
    }

    private void registrar() throws IOException, ClassNotFoundException {
        System.out.println("\n--- REGISTRO DE USUARIO ---");
        String nombre = entrada.leerTextoNoVacio("Nombre completo: ");
        String correo = entrada.leerTextoNoVacio("Correo: ");
        char[] clave = entrada.leerTextoNoVacio("Contraseña (mínimo 8 caracteres): ").toCharArray();
        char[] confirmacion = entrada.leerTextoNoVacio("Confirme la contraseña: ").toCharArray();
        if (!java.util.Arrays.equals(clave, confirmacion)) {
            java.util.Arrays.fill(clave, '\0');
            java.util.Arrays.fill(confirmacion, '\0');
            System.out.println("Las contraseñas no coinciden.");
            return;
        }
        try {
            Usuario usuario = servicio.registrar(nombre, correo, clave);
            System.out.println("Cuenta creada para " + usuario.getCorreo() + ". Ya puede iniciar sesión.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            java.util.Arrays.fill(clave, '\0');
            java.util.Arrays.fill(confirmacion, '\0');
        }
    }
}
