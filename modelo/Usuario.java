package modelo;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Locale;

public final class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int ITERACIONES = 210_000;
    private static final int LONGITUD_HASH = 256;
    private final String nombre;
    private final String correo;
    private final byte[] sal;
    private final byte[] hashContrasena;

    private Usuario(String nombre, String correo, byte[] sal, byte[] hashContrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.sal = sal;
        this.hashContrasena = hashContrasena;
    }

    public static Usuario registrar(String nombre, String correo, char[] contrasena) {
        String n = nombre == null ? "" : nombre.trim();
        String c = normalizarCorreo(correo);
        if (n.isEmpty()) throw new IllegalArgumentException("El nombre es obligatorio.");
        if (!c.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$"))
            throw new IllegalArgumentException("Ingrese un correo válido.");
        if (contrasena == null || contrasena.length < 8)
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        byte[] sal = new byte[16];
        new SecureRandom().nextBytes(sal);
        return new Usuario(n, c, sal, derivar(contrasena, sal));
    }

    public boolean verificarContrasena(char[] contrasena) {
        if (contrasena == null) return false;
        return MessageDigest.isEqual(hashContrasena, derivar(contrasena, sal));
    }

    public static String normalizarCorreo(String correo) {
        return correo == null ? "" : correo.trim().toLowerCase(Locale.ROOT);
    }

    private static byte[] derivar(char[] contrasena, byte[] sal) {
        PBEKeySpec spec = new PBEKeySpec(contrasena, sal, ITERACIONES, LONGITUD_HASH);
        try {
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(spec).getEncoded();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("No se pudo proteger la contraseña.", e);
        } finally {
            spec.clearPassword();
        }
    }

    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
}
