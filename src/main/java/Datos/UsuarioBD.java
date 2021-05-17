package Datos;

import Aplicacion.Modelo.Cliente;
import Aplicacion.Modelo.Usuario;
import Aplicacion.SingleSession;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ZetaZeta
 */
public class UsuarioBD {

    public static Usuario getUsuario(String usuario, String contraseña) {
        Session ses = SingleSession.getSesio();
        Query query = ses.createQuery("FROM Usuario WHERE usuario = :usuario");
        query.setParameter("usuario", usuario);
        Usuario user = (Usuario) query.uniqueResult();

        return user;
    }

    public static int getNumUsuarios() {
        Session ses = SingleSession.getSesio();

        Query query = ses.createQuery("SELECT count(*) FROM Usuario");
        Long numUsuarios = (Long) query.uniqueResult();

        int numUsuariosInt = numUsuarios.intValue();

        return numUsuariosInt;

    }

    public static void añadir(Usuario u) {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.save(u);
        ses.getTransaction().commit();
        ses.close();
    }

    public static void eliminar(Usuario u) {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.delete(u);
        ses.getTransaction().commit();
        ses.close();
    }

    public static void actualizar(Usuario u1) {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();
        ses.update(u1);
        ses.getTransaction().commit();
        ses.close();
    }

    public static ArrayList<Usuario> getUsuarios() {
        Session ses = SingleSession.getSesio();
        Query query = ses.createQuery("FROM Usuario");
        ArrayList<Usuario> u = (ArrayList<Usuario>) query.list();

        ses.close();

        return u;
    }

}
