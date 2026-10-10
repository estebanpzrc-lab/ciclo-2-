package interfaces;

import modelo.Usuario;
import java.io.IOException;
import java.util.List;

public interface RepositorioUsuarios {
    List<Usuario> cargar() throws IOException, ClassNotFoundException;
    void guardar(List<Usuario> usuarios) throws IOException;
}
