package Aplicacion;

import Aplicacion.Modelo.Cliente;
import Aplicacion.Modelo.Factura;
import Datos.ClienteBD;
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

    public static void eliminar(Factura f) throws AplicacionException {
        try {

            FacturaBD.eliminar(f);

        } catch (DatosException ex) {
            throw new AplicacionException("Error eliminar cliente: " + ex.toString());
        }
    }

    public static void actualizar(Factura f1) throws AplicacionException {
        try {
            FacturaBD.actualizar(f1);
        } catch (DatosException ex) {
            throw new AplicacionException("Error actualizar cliente: " + ex.toString());
        }

    }
}
