package Datos;

import Aplicacion.Modelo.Proveedor;
import Aplicacion.SingleSession;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Victor
 */
public class ProveedorBD {

    public static void añadir(Proveedor p) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.save(p);
        ses.getTransaction().commit();
    }

    public static void eliminar(Proveedor p) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.delete(p);
        ses.getTransaction().commit();
    }

    public static void actualizar(Proveedor p1) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.update(p1);
        ses.getTransaction().commit();
    }

    public static ArrayList<Proveedor> getProveedores() throws DatosException {
        Session ses = SingleSession.getSesio();
        Query query = ses.createQuery("FROM Proveedor");
        ArrayList<Proveedor> p = (ArrayList<Proveedor>) query.list();

        return p;

    }
}
