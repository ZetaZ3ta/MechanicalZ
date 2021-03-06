package Presentacion;

import Aplicacion.AplicacionException;
import Aplicacion.GestorEscenas;
import Aplicacion.LogicUsuario;
import Aplicacion.Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class AdministrarUsuariosController implements Initializable {

    ObservableList<Usuario> listaUsuarios;

    @FXML
    private TextField fieldUsuario;
    @FXML
    private ChoiceBox choiceTipo;
    @FXML
    private TableView<Usuario> tvUsuarios;
    @FXML
    private TableColumn colUsuario, colContraseña, colTipo;
    @FXML
    private TextField fieldContraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceTipo.getItems().add("Admin");
        choiceTipo.getItems().add("Secretario");

        try {
            mostrarUsuarios();

            colUsuario.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
            colContraseña.setCellValueFactory(new PropertyValueFactory<>("Contraseña"));
            colTipo.setCellValueFactory(new PropertyValueFactory<>("Tipo"));

        } catch (Exception ex) {
            mostrarError("Error al inicializar: " + ex.toString());
            System.exit(1);
        }
    }

    @FXML
    private void btnAtrasAction(ActionEvent event) throws IOException {
        GestorEscenas escenas = new GestorEscenas();
        escenas.cambioEscena("MechanicalZ", "PrincipalAdmin.fxml", (Node) event.getSource());

    }

    @FXML
    private void btnAñadirAction(ActionEvent event) throws AplicacionException {

        Usuario u = new Usuario(fieldUsuario.getText(), fieldContraseña.getText(), choiceTipo.getSelectionModel().getSelectedItem().toString());
        LogicUsuario.añadir(u);

        mostrarUsuarios();
        limpiarCampos();

    }

    @FXML
    private void btnEliminarAction(ActionEvent event) throws AplicacionException {

        Usuario u = tvUsuarios.getSelectionModel().getSelectedItem();

        if (u != null) {
            LogicUsuario.eliminar(u);
            mostrarUsuarios();
        } else {
            mostrarError("Selecciona un usuario!");
        }
    }

    @FXML
    private void btnActualizarAction(ActionEvent event) throws AplicacionException {
        Usuario u1 = tvUsuarios.getSelectionModel().getSelectedItem();

        if (u1 != null) {
            Usuario u2 = new Usuario(fieldUsuario.getText(), fieldContraseña.getText(), choiceTipo.getSelectionModel().getSelectedItem().toString());

            LogicUsuario.actualizar(u2);
            mostrarUsuarios();
        } else {
            mostrarError("Selecciona un usuario!");
        }
    }

    @FXML
    private void btnLimpiarAction(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    private void onMouseClickedTableClientes(MouseEvent event) {
        Usuario usuario = tvUsuarios.getSelectionModel().getSelectedItem();

        if (usuario != null) {
            setUsuarioToView(usuario);
        }
    }

    private void mostrarUsuarios() throws AplicacionException {
        listaUsuarios = FXCollections.<Usuario>observableArrayList(LogicUsuario.getUsuarios());

        tvUsuarios.setItems(listaUsuarios);
    }

    private void limpiarCampos() {
        fieldUsuario.setText(null);
        fieldContraseña.setText(null);
        choiceTipo.setValue(null);
    }

    private void mostrarError(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(txt);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    private void setUsuarioToView(Usuario u) {

        fieldUsuario.setText(u.getUsuario());
        fieldContraseña.setText(u.getContraseña());
        choiceTipo.setValue(u.getTipo());
    }
}
