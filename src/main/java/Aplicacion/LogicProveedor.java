package Aplicacion;

import Aplicacion.Modelo.Cliente;
import Aplicacion.Modelo.Proveedor;
import Datos.ClienteBD;
import Datos.DatosException;
import Datos.ProveedorBD;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class LogicProveedor {

    public static void añadir(Proveedor p) throws AplicacionException {

        try {
            ProveedorBD.añadir(p);

        } catch (DatosException ex) {
            throw new AplicacionException("Error añadir Proveedor: " + ex.toString());
        }

    }

    public static void eliminar(Proveedor p) throws AplicacionException {
        try {

            ProveedorBD.eliminar(p);

        } catch (DatosException ex) {
            throw new AplicacionException("Error eliminar Proveedor: " + ex.toString());
        }
    }

    public static void actualizar(Proveedor p1) throws AplicacionException {
        try {
            ProveedorBD.actualizar(p1);
        } catch (DatosException ex) {
            throw new AplicacionException("Error actualizar Proveedor: " + ex.toString());
        }

    }

    public static ArrayList<Proveedor> getProveedores() throws AplicacionException {
        ArrayList<Proveedor> ret = null;
        try {
            ret = ProveedorBD.getProveedores();

        } catch (DatosException ex) {
            throw new AplicacionException("Error getProveedores: " + ex.toString());
        }

        return ret;

    }
}
