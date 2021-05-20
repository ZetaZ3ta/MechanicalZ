package Aplicacion;

import Aplicacion.Modelo.Factura;
import Datos.DatosException;
import Datos.FacturaBD;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class LogicFactura {

    public static ArrayList<Factura> getFacturas() throws AplicacionException {
        ArrayList<Factura> ret = null;
        try {
            ret = FacturaBD.getFacturas();

        } catch (DatosException ex) {
            throw new AplicacionException("Error getClientes: " + ex.toString());
        }

        return ret;

    }

    public static void añadir(Factura f) throws AplicacionException {
        try {
            FacturaBD.añadir(f);

        } catch (DatosException ex) {
            throw new AplicacionException("Error añadir moto: " + ex.toString());
        }

    }
}
