package pruebas;

import datos.RepositorioArchivoCitas;
import datos.RepositorioArchivoUsuarios;
import interfaces.Asegurable;
import interfaces.Descontable;
import modelo.CitaMedica;
import modelo.Especialidad;
import modelo.Paciente;
import modelo.PacienteAsegurado;
import modelo.PacienteParticular;
import servicios.ServicioCitas;
import servicios.ServicioUsuarios;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class PruebasUnitarias {
    private static int correctas = 0;

    public static void main(String[] args) throws Exception {
        comprobar("Paciente es abstracto", Modifier.isAbstract(Paciente.class.getModifiers()));
        PacienteParticular particular = new PacienteParticular("Ana Soto", 30, Especialidad.MEDICINA_GENERAL);
        PacienteAsegurado asegurado = new PacienteAsegurado("Luis Quispe", 60,
                Especialidad.CARDIOLOGIA, "Rímac Seguros", 10.0);
        comprobar("Paciente particular paga tarifa completa", particular.calcularTotal() == 80.0);
        comprobar("Descuento del asegurado", Math.abs(asegurado.calcularDescuento() - 12.0) < 0.001);
        comprobar("Total del asegurado", Math.abs(asegurado.calcularTotal() - 108.0) < 0.001);
        comprobar("Atención preferencial desde 60 años", "Preferencial".equals(asegurado.getTipoAtencion()));
        comprobar("Paciente asegurado implementa dos interfaces",
                asegurado instanceof Descontable && asegurado instanceof Asegurable);
        Paciente[] pacientes = {particular, asegurado};
        double total = 0;
        for (Paciente paciente : pacientes) total += paciente.calcularTotal();
        comprobar("Polimorfismo calcula montos según el subtipo", Math.abs(total - 188.0) < 0.001);
        boolean rechazoEdad = false;
        try { new PacienteParticular("Edad inválida", 0, Especialidad.PEDIATRIA); }
        catch (IllegalArgumentException e) { rechazoEdad = true; }
        comprobar("Rechaza edad menor a 1", rechazoEdad);
        probarPersistenciaYHorario(particular);
        probarUsuarios();
        System.out.println("Pruebas completadas: " + correctas + "/16 correctas.");
    }

    private static void probarPersistenciaYHorario(Paciente paciente) throws Exception {
        Path archivo = Files.createTempFile("citas-aa4-test", ".dat");
        Files.deleteIfExists(archivo);
        try {
            ServicioCitas servicio = new ServicioCitas(new RepositorioArchivoCitas(archivo));
            CitaMedica primera = servicio.registrar(paciente, 8, LocalDate.now());
            comprobar("Registra y persiste una cita", primera != null && Files.exists(archivo));
            comprobar("Evita dos citas en el mismo turno", servicio.registrar(paciente, 8, LocalDate.now()) == null);
            ServicioCitas recargado = new ServicioCitas(new RepositorioArchivoCitas(archivo));
            comprobar("Recupera la cita guardada", recargado.listar().size() == 1);
        } finally {
            Files.deleteIfExists(archivo);
        }
    }

    private static void probarUsuarios() throws Exception {
        Path archivo = Files.createTempFile("usuarios-aa4-test", ".dat");
        Files.deleteIfExists(archivo);
        try {
            ServicioUsuarios servicio = new ServicioUsuarios(new RepositorioArchivoUsuarios(archivo));
            servicio.registrar("María Pérez", " Maria@ejemplo.com ", "claveSegura123".toCharArray());
            comprobar("Registra una cuenta y normaliza el correo",
                    servicio.iniciarSesion("maria@ejemplo.com", "claveSegura123".toCharArray()) != null);
            comprobar("Rechaza una contraseña incorrecta",
                    servicio.iniciarSesion("maria@ejemplo.com", "incorrecta".toCharArray()) == null);
            boolean duplicado = false;
            try { servicio.registrar("Otra persona", "MARIA@ejemplo.com", "otraClave123".toCharArray()); }
            catch (IllegalArgumentException e) { duplicado = true; }
            comprobar("Impide registrar correos duplicados sin distinguir mayúsculas", duplicado);
            boolean claveCorta = false;
            try { servicio.registrar("Persona", "p@ejemplo.com", "123".toCharArray()); }
            catch (IllegalArgumentException e) { claveCorta = true; }
            comprobar("Exige una contraseña de al menos 8 caracteres", claveCorta);
            ServicioUsuarios recargado = new ServicioUsuarios(new RepositorioArchivoUsuarios(archivo));
            comprobar("Conserva la cuenta después de recargar el archivo",
                    recargado.iniciarSesion("maria@ejemplo.com", "claveSegura123".toCharArray()) != null);
        } finally {
            Files.deleteIfExists(archivo);
        }
    }

    private static void comprobar(String nombre, boolean resultado) {
        if (!resultado) throw new AssertionError("Falló: " + nombre);
        correctas++;
        System.out.println("OK: " + nombre);
    }
}
