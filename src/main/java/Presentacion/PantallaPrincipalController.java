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
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ZetaZeta
 */
public class PantallaPrincipalController implements Initializable {

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
    private void btnAdminClientesAction(ActionEvent event) {
        Escena = "AdministrarClientes.fxml";
        tituloEscena = "Administrar Clientes";

        CambioEscena(event);

    }

    @FXML
    private void btnAdminMotosAction(ActionEvent event) {
        Escena = "AdministrarMotos.fxml";
        tituloEscena = "Administrar Motos";

        CambioEscena(event);
    }

    @FXML
    private void btnAdminFacturasAction(ActionEvent event) {
        Escena = "AdministrarFacturas.fxml";
        tituloEscena = "Administrar Facturas";
        
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

    private void mostrarError(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(txt);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    private void mostrarInfo(String txt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info:");
        alert.setContentText(txt);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

}
