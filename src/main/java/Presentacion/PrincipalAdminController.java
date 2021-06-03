package Presentacion;

import Aplicacion.GestorEscenas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

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
    private void btnAdminUsuariosAction(ActionEvent event) throws IOException {
        Escena = "AdministrarUsuarios.fxml";
        tituloEscena = "Administrar Usuarios";

        CambioEscena(event);
    }

    @FXML
    private void btnAdminMecanicosAction(ActionEvent event) throws IOException {
        Escena = "AdministrarMecanicos.fxml";
        tituloEscena = "Administrar Mecanicos";

        CambioEscena(event);

    }

    @FXML

    private void btnAdminServiciosAction(ActionEvent event) throws IOException {
        Escena = "AdminsitrarServicios.fxml";
        tituloEscena = "Administrar Servicios";

        CambioEscena(event);

    }

    @FXML
    private void btnCerrarAction(ActionEvent event) throws IOException {
        Escena = "Login.fxml";
        tituloEscena = "Login";

        CambioEscena(event);

    }

    @FXML
    private void btnAdminSecretariosAction(ActionEvent event) throws IOException {
        Escena = "AdministrarSecretarios.fxml";
        tituloEscena = "Administrar Secretarios";

        CambioEscena(event);
    }

    @FXML
    private void btnCambiarAction(ActionEvent event) throws IOException {
        Escena = "PantallaPrincipal.fxml";
        tituloEscena = "MechanicalZ";

        CambioEscena(event);
    }

    private void CambioEscena(ActionEvent event) throws IOException {
        GestorEscenas escenas = new GestorEscenas();
        escenas.cambioEscena(tituloEscena, Escena, (Node) event.getSource());
    }

}
