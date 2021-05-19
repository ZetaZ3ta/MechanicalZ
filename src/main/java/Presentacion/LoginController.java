package Presentacion;

import Aplicacion.AplicacionException;
import Aplicacion.GestorEscenas;
import Aplicacion.LogicLogin;
import Aplicacion.LogicUsuario;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 *
 * @author ZetaZeta
 */
public class LoginController implements Initializable {

    @FXML
    private Label errorUsuario, errorContraseña;
    @FXML
    private TextField fieldUsuario;
    @FXML
    private PasswordField fieldContraseña;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnEntrarLogInAction(ActionEvent event) throws AplicacionException, IOException {
        comprobacionLogin((Node) event.getSource());
    }

    @FXML
    private void OnKeyPressedFondo(KeyEvent event) throws AplicacionException, IOException {
        if (event.getCode() == event.getCode().ENTER) {
            comprobacionLogin((Node) event.getSource());
        }

    }

    private void comprobacionLogin(Node nodo) throws AplicacionException, IOException {

        fieldUsuario.setStyle(null);
        fieldContraseña.setStyle(null);
        errorUsuario.setVisible(false);
        errorContraseña.setVisible(false);

        if (fieldUsuario.getText().equals("")) {
            fieldUsuario.setStyle("-fx-border-color: red;");
            errorUsuario.setVisible(true);
        }
        if (fieldContraseña.getText().equals("")) {
            fieldContraseña.setStyle("-fx-border-color: red;");
            errorContraseña.setVisible(true);
        }
        if (!fieldUsuario.getText().equals("") && !fieldContraseña.getText().equals("")) {
            Usuario user = null;
            user = LogicLogin.verificarLogin(fieldUsuario.getText(), fieldContraseña.getText());

            LoginSucces(nodo);
        }
    }

    private void LoginSucces(Node source) throws IOException {

        String escena;

        Usuario u = LogicUsuario.getUsuario(fieldUsuario.getText());

        if (u.getTipo().equalsIgnoreCase("Admin")) {
            escena = "PrincipalAdmin.fxml";
        } else {
            escena = "PantallaPrincipal.fxml";
        }
        GestorEscenas escenas = new GestorEscenas();
        escenas.cambioEscena("MechanicalZ", escena, source);

    }

    private void mostrarError(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(txt);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

}
