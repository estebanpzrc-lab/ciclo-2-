package datos;

import interfaces.RepositorioUsuarios;
import modelo.Usuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivoUsuarios implements RepositorioUsuarios {
    private final Path archivo;
    public RepositorioArchivoUsuarios(Path archivo) { this.archivo = archivo; }

    @Override public List<Usuario> cargar() throws IOException, ClassNotFoundException {
        if (!Files.exists(archivo)) return new ArrayList<>();
        try (ObjectInputStream entrada = new ObjectInputStream(Files.newInputStream(archivo))) {
            Object datos = entrada.readObject();
            if (!(datos instanceof List<?>)) throw new IOException("El archivo de usuarios no tiene un formato válido.");
            List<Usuario> usuarios = new ArrayList<>();
            for (Object dato : (List<?>) datos) {
                if (!(dato instanceof Usuario)) throw new IOException("El archivo contiene un usuario inválido.");
                usuarios.add((Usuario) dato);
            }
            return usuarios;
        }
    }

    @Override public void guardar(List<Usuario> usuarios) throws IOException {
        Path carpeta = archivo.getParent();
        if (carpeta != null) Files.createDirectories(carpeta);
        try (ObjectOutputStream salida = new ObjectOutputStream(Files.newOutputStream(archivo))) {
            salida.writeObject(new ArrayList<>(usuarios));
        }
    }
}
