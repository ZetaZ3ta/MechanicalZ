package Datos;

import Aplicacion.Modelo.Usuario;
import Aplicacion.SingleSession;
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

}
