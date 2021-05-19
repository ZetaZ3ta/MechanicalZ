package Datos;

import Aplicacion.Modelo.Moto;
import Aplicacion.SingleSession;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ZetaZeta
 */
public class MotoBD {

    public static void añadir(Moto m) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.save(m);
        ses.getTransaction().commit();
    }

    public static void eliminar(Moto m) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.delete(m);
        ses.getTransaction().commit();
    }

    public static void actualizar(Moto m1) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.update(m1);
        ses.getTransaction().commit();
    }

    public static ArrayList<Moto> getMotos() {
        Session ses = SingleSession.getSesio();
        Query query = ses.createQuery("FROM Moto");
        ArrayList<Moto> motos = (ArrayList<Moto>) query.list();

        return motos;

    }

    public static Moto getMoto(String bastidor) {
        Session ses = SingleSession.getSesio();

        Query query = ses.createQuery("FROM Usuario WHERE usuario = :bastidor");
        query.setParameter("bastidor", bastidor);
        Moto m = (Moto) query.uniqueResult();

        return m;

    }

}
