package Presentacion;

import Aplicacion.GestorEscenas;
import Aplicacion.LogicPrimerUsuario;
import Aplicacion.Modelo.Usuario;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ZetaZeta
 */
public class PrimerUsuario implements Initializable {

    @FXML
    private Button btnEntrarLogIn;
    @FXML
    private Label errorUsuario, errorContraseña, errorContraseña1;
    @FXML
    private TextField fieldUsuario;
    @FXML
    private PasswordField fieldContraseña, fieldContraseña2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnEntrarLogInAction(ActionEvent event) throws IOException {
        comprobacionRegistro((Node) event.getSource());

    }

    @FXML
    private void OnKeyPressedFondo(KeyEvent event) throws IOException {
        if (event.getCode() == event.getCode().ENTER) {
            comprobacionRegistro((Node) event.getSource());
        }
    }

    private void mostrarError(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(txt);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    private void CambioEscena(Node nodo) throws IOException {
        GestorEscenas escenas = new GestorEscenas();
        escenas.cambioEscena("MechanicalZ", "PrincipalAdmin.fxml", nodo);
    }

    private void comprobacionRegistro(Node nodo) throws IOException {
        fieldUsuario.setStyle(null);
        fieldContraseña.setStyle(null);
        fieldContraseña2.setStyle(null);
        errorUsuario.setVisible(false);
        errorContraseña.setVisible(false);
        errorContraseña1.setVisible(false);

        if (fieldUsuario.getText().isEmpty()) {
            errorUsuario.setVisible(true);
        }

        if (fieldContraseña.getText().isEmpty()) {
            errorContraseña.setVisible(true);
        }

        if (fieldContraseña2.getText().isEmpty()) {
            errorContraseña1.setVisible(true);
        }

        if (!fieldContraseña.getText().equals(fieldContraseña2.getText())) {
            mostrarError("No coinciden las contraseñas!");
        } else {

            if (!(fieldContraseña.getText().isEmpty()) && !(fieldContraseña2.getText().isEmpty())) {
                Usuario user = new Usuario(fieldUsuario.getText(), fieldContraseña.getText(), "admin");
                LogicPrimerUsuario.añadir(user);

                CambioEscena(nodo);
            }

        }
    }

}
