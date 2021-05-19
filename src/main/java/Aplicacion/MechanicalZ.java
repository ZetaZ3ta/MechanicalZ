package Aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Victor
 */
public class MechanicalZ extends Application {

    String nombreEscena;
    String escenaFxml;
    GestorEscenas escena;

    @Override
    public void start(Stage stage) throws Exception {

        int numUsers = LogicUsuario.getNumUsers();

        if (numUsers == 0) {
            nombreEscena = "Registro";
            escenaFxml = "PrimerUsuario.fxml";
            Scene scene;
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + escenaFxml));
            scene = new Scene(root);
            stage.setTitle(nombreEscena);
            stage.setScene(scene);
            stage.show();

//            escena = new GestorEscenas();
//            escena.cambioEscena(nombreEscena, escenaFxml);
        } else {
            nombreEscena = "Login";
            escenaFxml = "Login.fxml";
            Scene scene;
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + escenaFxml));
            scene = new Scene(root);
            stage.setTitle(nombreEscena);
            stage.setScene(scene);
            stage.show();

//            escena = new GestorEscenas();
//            escena.cambioEscena(nombreEscena, escenaFxml);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
