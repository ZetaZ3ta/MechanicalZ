/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import Aplicacion.Modelo.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

/**
 *
 * @author ZetaZeta
 */
public class MechanicalZ extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        int numUsers = LogicUsuario.getNumUsers();
        System.out.println(numUsers);
        Parent root;
        Scene scene;

        if (numUsers == 0) {
            root = FXMLLoader.load(getClass().getResource("/fxml/PrimerUsuario.fxml"));
            scene = new Scene(root);
            stage.setTitle("Registro");
            stage.setScene(scene);
            stage.show();
        } else {
            root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));

            scene = new Scene(root);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private static void transaction1() {
        Session ses = SingleSession.getSesio();
        ses.beginTransaction();

        Usuario p1 = new Usuario("Zeta", "12", "admin");

        ses.save(p1);

        ses.getTransaction().commit();
        ses.close();
    }

}
