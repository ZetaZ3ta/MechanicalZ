package Aplicacion;

import Aplicacion.Modelo.Mecanico;
import Datos.DatosException;
import Datos.MecanicoBD;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class LogicMecanico {

    public static void añadir(Mecanico m) throws AplicacionException {

        try {
            MecanicoBD.añadir(m);

        } catch (DatosException ex) {
            throw new AplicacionException("Error añadir moto: " + ex.toString());
        }

    }

    public static void eliminar(Mecanico m) throws AplicacionException {
        try {

            MecanicoBD.eliminar(m);

        } catch (DatosException ex) {
            throw new AplicacionException("Error añadir moto: " + ex.toString());
        }
    }

    public static void actualizar(Mecanico m1) throws AplicacionException {
        try {
            MecanicoBD.actualizar(m1);
        } catch (DatosException ex) {
            throw new AplicacionException("Error añadir moto: " + ex.toString());
        }

    }

    public static ArrayList<Mecanico> getMecanicos() throws AplicacionException {
        ArrayList<Mecanico> ret = null;
        try {
            ret = MecanicoBD.getMecanicos();

        } catch (DatosException ex) {
            throw new AplicacionException("Error getClientes: " + ex.toString());
        }

        return ret;

    }
}
