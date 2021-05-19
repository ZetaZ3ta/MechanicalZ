package Datos;

import Aplicacion.Modelo.Secretario;
import Aplicacion.SingleSession;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Victor
 */
public class SecretarioBD {

    public static void añadir(Secretario s) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.save(s);
        ses.getTransaction().commit();
    }

    public static void eliminar(Secretario s) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.delete(s);
        ses.getTransaction().commit();
    }

    public static void actualizar(Secretario s1) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.update(s1);
        ses.getTransaction().commit();
    }

    public static ArrayList<Secretario> getSecretarios() throws DatosException {
        Session ses = SingleSession.getSesio();
        Query query = ses.createQuery("FROM Secretario");
        ArrayList<Secretario> s = (ArrayList<Secretario>) query.list();

        return s;

    }

    public static Secretario getSecretario(String nombre) {
        Session ses = SingleSession.getSesio();

        Query query = ses.createQuery("FROM Secretario WHERE CONCAT(nombre,' ',apellidos) = '" + nombre + "'");
        Secretario s = (Secretario) query.uniqueResult();
        return s;
    }
}
