package Aplicacion;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

/**
 *
 * @author Victor
 */
public class GestorEscenas {

    public void cambioEscena(String nombreEscena, String escenaFxml, Node source) throws IOException {
        Stage stage = new Stage();
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + escenaFxml));
        scene = new Scene(root);
        stage.setTitle(nombreEscena);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> cerrar());
        Stage thisStage = (Stage) source.getScene().getWindow();
        thisStage.close();
    }

    public void primeraEscena(String nombreEscena, String escenaFxml) throws IOException {
        Stage stage = new Stage();
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + escenaFxml));
        scene = new Scene(root);
        stage.setTitle(nombreEscena);
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> cerrar());
        stage.show();
    }

    private void cerrar() {
        Platform.exit();
        Session ses = SingleSession.getSesio();
        ses.close();
    }

}
