package Presentacion;

import Aplicacion.AplicacionException;
import Aplicacion.LogicUsuario;
import Aplicacion.Modelo.Mecanico;
import Aplicacion.Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class AdministrarMecanicosController implements Initializable {

    ObservableList<Mecanico> listaMecanicos;
    
    @FXML
    private TextField fieldDNI;
    @FXML
    private TextField fieldNombre;
    @FXML
    private TextField fieldApellidos;
    @FXML
    private TextField fieldTelefono;
    @FXML
    private TextField fieldFecha;
    @FXML
    private TableColumn olDNI;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colApellidos;
    @FXML
    private TableColumn<?, ?> colTelefono;
    @FXML
    private TableColumn<?, ?> colFecha;
    @FXML
    private TableColumn<?, ?> colOcupado;
    @FXML
    private ChoiceBox choiceOcupado;
    @FXML
    private TableView<Mecanico> tvMecanicos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceOcupado.getItems().add("Si");
        choiceOcupado.getItems().add("No");

    }

    @FXML
    private void btnAtrasAction(ActionEvent event) {
    }

    @FXML
    private void btnAñadirAction(ActionEvent event) {
    }

    @FXML
    private void btnEliminarAction(ActionEvent event) {
    }

    @FXML
    private void btnActualizarAction(ActionEvent event) {
    }

    @FXML
    private void btnLimpiarAction(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedTableClientes(MouseEvent event) {
    }

    private void LoginSucces(Node source) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PantallaPrincipal.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add("/styles/Styles.css");
            stage.setTitle("MechanicalZ");
            stage.setScene(scene);
            stage.show();
            Stage thisStage = (Stage) source.getScene().getWindow();
            thisStage.close();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarUsuarios() throws AplicacionException {
        //listaMecanicos = FXCollections.<Usuario>observableArrayList(LogicUsuario.getUsuarios());

        tvMecanicos.setItems(listaMecanicos);
    }

    private void limpiarCampos() {
        fieldDNI.setText(null);
        fieldNombre.setText(null);
        fieldTelefono.setText(null);
        fieldApellidos.setText(null);
        fieldFecha.setText(null);
        choiceOcupado.setValue(null);
    }

    private void mostrarError(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(txt);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    private void setUsuarioToView(Mecanico m) {

        fieldDNI.setText(m.getDNI());
        fieldNombre.setText(m.getNombre());
        fieldTelefono.setText(String.valueOf(m.getTelefono()));
        fieldApellidos.setText(m.getApellidos());
        fieldFecha.setText(m.getFecha_Nacimiento());
        choiceOcupado.setValue(null);
    }

}
