package Aplicacion;

import Aplicacion.Modelo.Cliente;
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
            throw new AplicacionException("Error añadir cliente: " + ex.toString());
        }

    }

    public static void eliminar(Cliente c) throws AplicacionException {
        try {

            ClienteBD.eliminar(c);

        } catch (DatosException ex) {
            throw new AplicacionException("Error eliminar cliente: " + ex.toString());
        }
    }

    public static void actualizar(Cliente c1) throws AplicacionException {
        try {
            ClienteBD.actualizar(c1);
        } catch (DatosException ex) {
            throw new AplicacionException("Error actualizar cliente: " + ex.toString());
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

    public static Cliente getCliente(String nombre) {

        return ClienteBD.getCliente(nombre);

    }

}
