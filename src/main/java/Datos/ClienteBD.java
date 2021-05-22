package Datos;

import Aplicacion.Modelo.Cliente;
import Aplicacion.SingleSession;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Victor
 */
public class ClienteBD {

    public static void añadir(Cliente c) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.save(c);
        ses.getTransaction().commit();
    }

    public static void eliminar(Cliente c) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.delete(c);
        ses.getTransaction().commit();
    }

    public static void actualizar(Cliente c1) throws DatosException {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.update(c1);
        ses.getTransaction().commit();
    }

    public static ArrayList<Cliente> getClientes() throws DatosException {
        Session ses = SingleSession.getSesio();
        Query query = ses.createQuery("FROM Cliente");
        ArrayList<Cliente> c = (ArrayList<Cliente>) query.list();

        return c;

    }

    public static Cliente getCliente(/*String nombre*/String DNI) {
        Session ses = SingleSession.getSesio();
        Query query = ses.createQuery("FROM Cliente WHERE DNI = '" + DNI + "'");
        //Query query = ses.createQuery("FROM Cliente WHERE CONCAT(nombre,' ',apellidos) = '" + nombre + "'");
        Cliente c = (Cliente) query.uniqueResult();
        
        System.out.println(c.getDNI());
        return c;
    }
}
