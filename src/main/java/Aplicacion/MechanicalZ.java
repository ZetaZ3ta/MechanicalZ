package Aplicacion;

import javafx.application.Application;
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
        SingleSession.getSesio();
        int numUsers = LogicUsuario.getNumUsers();

        if (numUsers == 0) {
            nombreEscena = "Registro";
            escenaFxml = "PrimerUsuario.fxml";

            escena = new GestorEscenas();
            escena.primeraEscena(nombreEscena, escenaFxml);
        } else {
            nombreEscena = "Login";
            escenaFxml = "Login.fxml";

            escena = new GestorEscenas();
            escena.primeraEscena(nombreEscena, escenaFxml);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
