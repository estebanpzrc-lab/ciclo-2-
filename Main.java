import datos.RepositorioArchivoCitas;
import datos.RepositorioArchivoUsuarios;
import menus.MenuAutenticacion;
import menus.MenuPrincipal;
import servicios.ServicioCitas;
import servicios.ServicioReportes;
import servicios.ServicioUsuarios;
import utilidades.Entrada;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Entrada entrada = new Entrada(new Scanner(System.in));
            ServicioUsuarios usuarios = new ServicioUsuarios(
                    new RepositorioArchivoUsuarios(Path.of("datos", "usuarios.dat")));
            ServicioCitas citas = new ServicioCitas(
                    new RepositorioArchivoCitas(Path.of("datos", "citas.dat")));
            MenuAutenticacion acceso = new MenuAutenticacion(usuarios, entrada);
            MenuPrincipal menu = new MenuPrincipal(citas, new ServicioReportes(), entrada);
            while (true) {
                if (acceso.autenticar() == null) break;
                menu.iniciar();
            }
            System.out.println("Sistema cerrado.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudieron cargar los datos: " + e.getMessage());
            System.out.println("Revise que la carpeta datos esté disponible y vuelva a ejecutar.");
        }
    }
}
