package Aplicacion;

import Datos.UsuarioBD;

/**
 *
 * @author ZetaZeta
 */
class LogicUsuario {

    public static int getNumUsers() {

        return UsuarioBD.getNumUsuarios();

    }

}
