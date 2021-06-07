package Presentacion;

import Aplicacion.AplicacionException;
import Aplicacion.GestorEscenas;
import Aplicacion.LogicSecretario;
import Aplicacion.Modelo.Secretario;
import Aplicacion.Reglas;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
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
public class AdministrarSecretariosController implements Initializable {

    ObservableList<Secretario> listaSecretarios;

    @FXML
    private TableView<Secretario> tvSecretarios;
    @FXML
    private TableColumn colDNI, colNombre, colApellidos, colTelefono, colFecha;
    @FXML
    private TextField fieldDNI, fieldNombre, fieldApellidos, fieldTelefono;
    @FXML
    private DatePicker fieldFecha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mostrarSecretarios();

            colDNI.setCellValueFactory(new PropertyValueFactory<>("DNI"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            colApellidos.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
            colTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
            colFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha_Nacimiento"));

        } catch (Exception ex) {
            mostrarError("Error al inicializar: " + ex.toString());
            System.exit(1);
        }
    }

    @FXML
    private void btnAñadirAction(ActionEvent event) throws AplicacionException {

        if (comprobarCampos()) {
            String msgDNI = Reglas.DNI(fieldDNI.getText());
            String msgTLF = Reglas.telefono(fieldTelefono.getText());
            if (msgDNI.equals("") && msgTLF.equals("")) {
                Instant instant = Instant.from(fieldFecha.getValue().atStartOfDay(ZoneId.systemDefault()));
                Date date = Date.from(instant);
                try {
                    Secretario s = new Secretario(fieldDNI.getText(), fieldNombre.getText(), fieldApellidos.getText(), Integer.parseInt(fieldTelefono.getText()), date);
                    LogicSecretario.añadir(s);

                    mostrarSecretarios();
                    limpiarCampos();
                } catch (AplicacionException ex) {
                    Logger.getLogger(AdministrarClientesController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                if (!msgDNI.equals("")) {
                    mostrarError(msgDNI);
                }
                if (!msgTLF.equals("")) {
                    mostrarError(msgTLF);
                }
            }
        }

    }

    @FXML
    private void btnEliminarAction(ActionEvent event) throws AplicacionException {
        Secretario s = tvSecretarios.getSelectionModel().getSelectedItem();

        if (s != null) {
            LogicSecretario.eliminar(s);
            mostrarSecretarios();
        } else {
            mostrarError("Selecciona un secretario!");
        }
    }

    @FXML
    private void btnActualizarAction(ActionEvent event) throws AplicacionException {

        Secretario s1 = tvSecretarios.getSelectionModel().getSelectedItem();

        if (s1 != null) {

            if (comprobarCampos()) {
                String msgTLF = Reglas.telefono(fieldTelefono.getText());
                if (msgTLF.equals("")) {

                    Instant instant = Instant.from(fieldFecha.getValue().atStartOfDay(ZoneId.systemDefault()));
                    Date date = Date.from(instant);
                    Secretario s2 = new Secretario(fieldDNI.getText(), fieldNombre.getText(), fieldApellidos.getText(), Integer.parseInt(fieldTelefono.getText()), date);

                    LogicSecretario.actualizar(s2);
                    mostrarSecretarios();

                } else {
                    if (!msgTLF.equals("")) {
                        mostrarError(msgTLF);
                    }
                }

            }

        } else {
            mostrarError("Selecciona un secretario!");
        }
    }

    @FXML
    private void btnLimpiarAction(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    private void mouseClicked(MouseEvent event) {

        Secretario secretario = tvSecretarios.getSelectionModel().getSelectedItem();

        if (secretario != null) {
            setSecretarioToView(secretario);
        }

    }

    @FXML
    private void btnAtrasAction(ActionEvent event) throws IOException {
        GestorEscenas escenas = new GestorEscenas();
        escenas.cambioEscena("MechanicalZ", "PrincipalAdmin.fxml", (Node) event.getSource());
    }

    private void mostrarError(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(txt);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    private void mostrarSecretarios() throws AplicacionException {
        listaSecretarios = FXCollections.<Secretario>observableArrayList(LogicSecretario.getSecretarios());

        tvSecretarios.setItems(listaSecretarios);
    }

    private void limpiarCampos() {
        fieldDNI.setText(null);
        fieldNombre.setText(null);
        fieldApellidos.setText(null);
        fieldFecha.setValue(null);
        fieldTelefono.setText(null);
    }

    private void setSecretarioToView(Secretario s) {

        fieldDNI.setText(s.getDNI());
        fieldNombre.setText(s.getNombre());
        fieldApellidos.setText(s.getApellidos());
        fieldTelefono.setText(String.valueOf(s.getTelefono()));
        fieldFecha.setValue(s.getFecha_Nacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    private boolean comprobarCampos() {
        fieldDNI.setStyle(null);
        fieldNombre.setStyle(null);
        fieldApellidos.setStyle(null);
        fieldTelefono.setStyle(null);
        fieldFecha.setStyle(null);

        boolean campos = true;
        if (fieldDNI.getText().isEmpty()) {
            fieldDNI.setStyle("-fx-border-color: red;");
            campos = false;
        }
        if (fieldNombre.getText().isEmpty()) {
            fieldNombre.setStyle("-fx-border-color: red;");
            campos = false;
        }
        if (fieldApellidos.getText().isEmpty()) {
            fieldApellidos.setStyle("-fx-border-color: red;");
            campos = false;
        }
        if (fieldTelefono.getText().isEmpty()) {
            fieldTelefono.setStyle("-fx-border-color: red;");
            campos = false;
        }
        if (fieldFecha.getValue() == null) {
            fieldFecha.setStyle("-fx-border-color: red;");
            campos = false;
        }

        return campos;
    }

}
