package Aplicacion;

import Aplicacion.Modelo.Usuario;
import Datos.UsuarioBD;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class LogicUsuario {

    public static void añadir(Usuario u) {

        UsuarioBD.añadir(u);

    }

    public static void eliminar(Usuario u) throws AplicacionException {
        UsuarioBD.eliminar(u);
    }

    public static void actualizar(Usuario u) throws AplicacionException {
        UsuarioBD.actualizar(u);

    }

    public static ArrayList<Usuario> getUsuarios() throws AplicacionException {

        ArrayList<Usuario> ret = UsuarioBD.getUsuarios();

        return ret;

    }

    public static int getNumUsers() {

        return UsuarioBD.getNumUsuarios();

    }

    public static Usuario getUsuario(String usuario) {

        return UsuarioBD.getUsuario(usuario);
    }

}
