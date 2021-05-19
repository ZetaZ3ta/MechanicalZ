package Datos;

import Aplicacion.Modelo.Factura;
import Aplicacion.SingleSession;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ZetaZeta
 */
public class FacturaBD {

    public static void añadir(Factura f) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.save(f);
        ses.getTransaction().commit();
    }

    public static void eliminar(Factura f) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.delete(f);
        ses.getTransaction().commit();
    }

    public static void actualizar(Factura f1) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.update(f1);
        ses.getTransaction().commit();
    }

    public static ArrayList<Factura> getFacturas() throws DatosException {
        Session ses = SingleSession.getSesio();
        Query query = ses.createQuery("FROM Factura");
        ArrayList<Factura> f = (ArrayList<Factura>) query.list();

        return f;

    }
}
