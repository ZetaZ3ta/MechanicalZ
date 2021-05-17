package Aplicacion;

import Aplicacion.Modelo.Cliente;
import Aplicacion.Modelo.Usuario;
import Datos.ClienteBD;
import Datos.DatosException;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class LogicCliente {

    public static void añadir(Cliente c) throws AplicacionException {

        try {
            ClienteBD.añadir(c);

        } catch (DatosException ex) {
            throw new AplicacionException("Error añadir moto: " + ex.toString());
        }

    }

    public static void eliminar(Cliente c) throws AplicacionException {
        try {

            ClienteBD.eliminar(c);

        } catch (DatosException ex) {
            throw new AplicacionException("Error añadir moto: " + ex.toString());
        }
    }

    public static void actualizar(Cliente c1) throws AplicacionException {
        try {
            ClienteBD.actualizar(c1);
        } catch (DatosException ex) {
            throw new AplicacionException("Error añadir moto: " + ex.toString());
        }

    }

    public static ArrayList<Cliente> getClientes() throws AplicacionException {
        ArrayList<Cliente> ret = null;
        try {
            ret = ClienteBD.getClientes();

        } catch (DatosException ex) {
            throw new AplicacionException("Error getClientes: " + ex.toString());
        }

        return ret;

    }

    public static void añadir(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
