package Presentacion;

import Aplicacion.GestorEscenas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author Victor
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
    private void btnAdminClientesAction(ActionEvent event) throws IOException {
        Escena = "AdministrarClientes.fxml";
        tituloEscena = "Administrar Clientes";

        CambioEscena(event);

    }

    @FXML
    private void btnAdminMotosAction(ActionEvent event) throws IOException {
        Escena = "AdministrarMotos.fxml";
        tituloEscena = "Administrar Motos";

        CambioEscena(event);
    }

    @FXML
    private void btnAdminFacturasAction(ActionEvent event) throws IOException {
        Escena = "AdministrarFacturas.fxml";
        tituloEscena = "Administrar Facturas";

        CambioEscena(event);
    }

    @FXML
    private void btnCerrarAction(ActionEvent event) throws IOException {
        Escena = "Login.fxml";
        tituloEscena = "Login";
        CambioEscena(event);

    }

    private void CambioEscena(ActionEvent event) throws IOException {
        GestorEscenas escenas = new GestorEscenas();
        escenas.cambioEscena(tituloEscena, Escena, (Node) event.getSource());
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
