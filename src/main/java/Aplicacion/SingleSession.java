package Aplicacion;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SingleSession {

    private static SingleSession instancia = null;
    private static SessionFactory sesio = null;

    private SingleSession() {
        InitSessionFactory();
    }

    private static SingleSession getInstance() {
        if (instancia == null) {
            instancia = new SingleSession();
        }

        return instancia;
    }

    private void InitSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        this.sesio = configuration.buildSessionFactory(builder.build());
    }

    public static Session getSesio() {
        getInstance();
        return sesio.openSession();
    }
}
