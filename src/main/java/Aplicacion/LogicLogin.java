package Aplicacion;

import Aplicacion.Modelo.Usuario;
import Datos.UsuarioBD;

/**
 *
 * @author ZetaZeta
 */
public class LogicLogin {

    public static void verificarLogin(String usuario, String contraseña) throws AplicacionException {

        Usuario user = UsuarioBD.getUsuario(usuario, contraseña);

        System.out.println(usuario + " " + contraseña);

        if (!user.getContraseña().equals(contraseña)) {
            throw new AplicacionException("contraseña incorrecta");

        }

    }
}
