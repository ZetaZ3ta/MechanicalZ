package Aplicacion;

import Aplicacion.Modelo.Secretario;
import Datos.DatosException;
import Datos.SecretarioBD;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class LogicSecretario {

    public static void añadir(Secretario s) throws AplicacionException {

        try {
            SecretarioBD.añadir(s);

        } catch (DatosException ex) {
            throw new AplicacionException("Error añadir moto: " + ex.toString());
        }

    }

    public static void eliminar(Secretario s) throws AplicacionException {
        try {

            SecretarioBD.eliminar(s);

        } catch (DatosException ex) {
            throw new AplicacionException("Error añadir moto: " + ex.toString());
        }
    }

    public static void actualizar(Secretario s1) throws AplicacionException {
        try {
            SecretarioBD.actualizar(s1);
        } catch (DatosException ex) {
            throw new AplicacionException("Error añadir moto: " + ex.toString());
        }

    }

    public static ArrayList<Secretario> getSecretarios() throws AplicacionException {
        ArrayList<Secretario> ret = null;
        try {
            ret = SecretarioBD.getSecretarios();

        } catch (DatosException ex) {
            throw new AplicacionException("Error getClientes: " + ex.toString());
        }

        return ret;

    }

    public static Secretario getSecretario(String nombre) {

        return SecretarioBD.getSecretario(nombre);

    }

}
