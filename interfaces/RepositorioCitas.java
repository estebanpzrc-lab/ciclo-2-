package interfaces;

import modelo.CitaMedica;
import java.io.IOException;
import java.util.List;

public interface RepositorioCitas {
    List<CitaMedica> cargar() throws IOException, ClassNotFoundException;
    void guardar(List<CitaMedica> citas) throws IOException;
}
