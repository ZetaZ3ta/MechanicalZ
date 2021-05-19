package Aplicacion;

import Aplicacion.Modelo.Factura;
import Datos.DatosException;
import Datos.FacturaBD;
import java.util.ArrayList;

/**
 *
 * @author ZetaZeta
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

    public static void añadir(Factura f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
