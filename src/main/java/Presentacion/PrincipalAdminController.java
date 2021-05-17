package Presentacion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class PrincipalAdminController implements Initializable {

    String tituloEscena;
    String Escena;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnAdminUsuariosAction(ActionEvent event) {
        Escena = "AdministrarUsuarios.fxml";
        tituloEscena = "Adminsitrar Usuarios";

        CambioEscena(event);
    }

    @FXML
    private void btnAdminServiciosAction(ActionEvent event) {
        Escena = "AdminsitrarServicios.fxml";
        tituloEscena = "Administrar Servicios";

        CambioEscena(event);

    }

    @FXML
    private void btnCerrarAction(ActionEvent event) {
        Escena = "Login.fxml";
        tituloEscena = "Login";

        CambioEscena(event);

    }

    private void CambioEscena(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + Escena));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add("/styles/Styles.css");
            stage.setTitle(tituloEscena);
            stage.setScene(scene);
            stage.show();
            Node source = (Node) event.getSource();
            Stage thisStage = (Stage) source.getScene().getWindow();
            thisStage.close();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
