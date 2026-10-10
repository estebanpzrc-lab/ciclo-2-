package datos;

import interfaces.RepositorioCitas;
import modelo.CitaMedica;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivoCitas implements RepositorioCitas {
    private final Path ruta;

    public RepositorioArchivoCitas(Path ruta) {
        if (ruta == null) throw new IllegalArgumentException("La ruta no puede ser nula.");
        this.ruta = ruta;
    }

    @Override
    public List<CitaMedica> cargar() throws IOException, ClassNotFoundException {
        if (!Files.exists(ruta)) return new ArrayList<>();
        try (ObjectInputStream entrada = new ObjectInputStream(Files.newInputStream(ruta))) {
            Object objeto = entrada.readObject();
            if (!(objeto instanceof List<?>)) {
                throw new IOException("El archivo de citas tiene un formato no válido.");
            }
            List<CitaMedica> resultado = new ArrayList<>();
            for (Object elemento : (List<?>) objeto) {
                if (!(elemento instanceof CitaMedica)) {
                    throw new IOException("El archivo contiene un registro que no es una cita.");
                }
                resultado.add((CitaMedica) elemento);
            }
            return resultado;
        } catch (EOFException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void guardar(List<CitaMedica> citas) throws IOException {
        Path carpeta = ruta.getParent();
        if (carpeta != null) Files.createDirectories(carpeta);
        try (ObjectOutputStream salida = new ObjectOutputStream(Files.newOutputStream(ruta))) {
            salida.writeObject(new ArrayList<>(citas));
        }
    }
}
