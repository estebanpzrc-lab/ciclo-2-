package servicios;

import interfaces.RepositorioUsuarios;
import modelo.Usuario;
import java.io.IOException;
import java.util.List;

public class ServicioUsuarios {
    private final RepositorioUsuarios repositorio;
    public ServicioUsuarios(RepositorioUsuarios repositorio) { this.repositorio = repositorio; }

    public Usuario registrar(String nombre, String correo, char[] contrasena)
            throws IOException, ClassNotFoundException {
        List<Usuario> usuarios = repositorio.cargar();
        String normalizado = Usuario.normalizarCorreo(correo);
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(normalizado))
                throw new IllegalArgumentException("Ya existe una cuenta con ese correo.");
        }
        Usuario nuevo = Usuario.registrar(nombre, correo, contrasena);
        usuarios.add(nuevo);
        repositorio.guardar(usuarios);
        return nuevo;
    }

    public Usuario iniciarSesion(String correo, char[] contrasena)
            throws IOException, ClassNotFoundException {
        String normalizado = Usuario.normalizarCorreo(correo);
        for (Usuario usuario : repositorio.cargar()) {
            if (usuario.getCorreo().equals(normalizado) && usuario.verificarContrasena(contrasena))
                return usuario;
        }
        return null;
    }
}
