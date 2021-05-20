package Aplicacion;

import Aplicacion.Modelo.Usuario;
import Datos.UsuarioBD;

/**
 *
 * @author Victor
 */
public class LogicLogin {

    public static boolean verificarLogin(String usuario, String contraseña) throws AplicacionException {

        Usuario u = UsuarioBD.getUsuario(usuario);

        if (!u.getContraseña().equals(contraseña)) {
            return false;
        } else {
            return true;
        }

    }
}
