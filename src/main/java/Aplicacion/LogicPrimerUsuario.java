package Aplicacion;

import Aplicacion.Modelo.Usuario;
import Datos.UsuarioBD;

/**
 *
 * @author ZetaZeta
 */
public class LogicPrimerUsuario {

    public static void añadir(Usuario u) {
        UsuarioBD.añadir(u);
    }

}
