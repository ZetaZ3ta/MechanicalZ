package Aplicacion;

import Aplicacion.Modelo.Usuario;
import Datos.UsuarioBD;

/**
 *
 * @author ZetaZeta
 */
public class LogicLogin {

    public static Usuario verificarLogin(String usuario, String contraseña) throws AplicacionException {

        return UsuarioBD.getUsuario(usuario);

    }
}
