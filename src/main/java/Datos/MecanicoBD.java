package Datos;

import Aplicacion.Modelo.Mecanico;
import Aplicacion.SingleSession;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ZetaZeta
 */
public class MecanicoBD {

    public static void añadir(Mecanico m) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.save(m);
        ses.getTransaction().commit();
    }

    public static void eliminar(Mecanico m) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.delete(m);
        ses.getTransaction().commit();
    }

    public static void actualizar(Mecanico m1) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.update(m1);
        ses.getTransaction().commit();
    }

    public static ArrayList<Mecanico> getMecanicos() throws DatosException {
        Session ses = SingleSession.getSesio();
        Query query = ses.createQuery("FROM Mecanico");
        ArrayList<Mecanico> m = (ArrayList<Mecanico>) query.list();

        return m;

    }
}
