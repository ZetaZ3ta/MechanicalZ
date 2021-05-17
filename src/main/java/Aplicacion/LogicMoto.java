package Aplicacion;

import Aplicacion.Modelo.Moto;
import Datos.DatosException;
import Datos.MotoBD;
import java.util.ArrayList;

/**
 *
 * @author ZetaZeta
 */
public class LogicMoto {

    public static Moto getMoto(String bastidor) {

        Moto m = MotoBD.getMoto(bastidor);

        return m;

    }

    public static void añadir(Moto m) throws AplicacionException {

        try {
            MotoBD.añadir(m);

        } catch (DatosException ex) {
            throw new AplicacionException("Error añadir moto: " + ex.toString());
        }

    }

    public static void eliminar(Moto m) throws AplicacionException {
        try {

            MotoBD.eliminar(m);

        } catch (DatosException ex) {
            throw new AplicacionException("Error añadir moto: " + ex.toString());
        }
    }

    public static void actualizar(Moto m1) throws AplicacionException {
        try {
            MotoBD.actualizar(m1);
        } catch (DatosException ex) {
            throw new AplicacionException("Error añadir moto: " + ex.toString());
        }

    }

    public static ArrayList<Moto> getMotos() {

        ArrayList<Moto> motos = MotoBD.getMotos();

        return motos;

    }

}
