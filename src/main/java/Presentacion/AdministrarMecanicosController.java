package Presentacion;

import Aplicacion.AplicacionException;
import Aplicacion.GestorEscenas;
import Aplicacion.LogicMecanico;
import Aplicacion.Modelo.Mecanico;
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
import javafx.scene.control.ChoiceBox;
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
public class AdministrarMecanicosController implements Initializable {

    ObservableList<Mecanico> listaMecanicos;

    @FXML
    private TextField fieldDNI, fieldNombre, fieldApellidos, fieldTelefono;
    @FXML
    private DatePicker fieldFecha;
    @FXML
    private TableColumn colDNI, colNombre, colApellidos, colTelefono, colFecha, colOcupado;
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

        try {
            mostrarMecanicos();

            colDNI.setCellValueFactory(new PropertyValueFactory<>("DNI"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            colApellidos.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
            colTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
            colFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha_Nacimiento"));
            colOcupado.setCellValueFactory(new PropertyValueFactory<>("Ocupado"));

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

        if (comprobarCampos()) {
            String msgDNI = Reglas.DNI(fieldDNI.getText());
            String msgTLF = Reglas.telefono(fieldTelefono.getText());
            if (msgDNI.equals("") && msgTLF.equals("")) {
                boolean ocupado;
                Instant instant = Instant.from(fieldFecha.getValue().atStartOfDay(ZoneId.systemDefault()));
                Date date = Date.from(instant);
                try {
                    if (choiceOcupado.getSelectionModel().getSelectedItem().equals("Si")) {
                        ocupado = true;
                    } else {
                        ocupado = false;
                    }
                    Mecanico m = new Mecanico(fieldDNI.getText(), fieldNombre.getText(), fieldApellidos.getText(), Integer.parseInt(fieldTelefono.getText()), date, ocupado);
                    LogicMecanico.añadir(m);

                    mostrarMecanicos();
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
        Mecanico m = tvMecanicos.getSelectionModel().getSelectedItem();

        if (m != null) {
            LogicMecanico.eliminar(m);
            mostrarMecanicos();
        } else {
            mostrarError("Selecciona un mecanico!");
        }
    }

    @FXML
    private void btnActualizarAction(ActionEvent event) throws AplicacionException {
        boolean ocupado;

        Mecanico m1 = tvMecanicos.getSelectionModel().getSelectedItem();

        if (m1 != null) {
            if (comprobarCampos()) {
                String msgTLF = Reglas.telefono(fieldTelefono.getText());
                if (msgTLF.equals("")) {

                    Instant instant = Instant.from(fieldFecha.getValue().atStartOfDay(ZoneId.systemDefault()));
                    Date date = Date.from(instant);

                    if (choiceOcupado.getSelectionModel().getSelectedItem().equals("Si")) {
                        ocupado = true;
                    } else {
                        ocupado = false;
                    }

                    Mecanico m2 = new Mecanico(fieldDNI.getText(), fieldNombre.getText(), fieldApellidos.getText(), Integer.parseInt(fieldTelefono.getText()), date, ocupado);

                    LogicMecanico.actualizar(m2);
                    mostrarMecanicos();

                } else {
                    if (!msgTLF.equals("")) {
                        mostrarError(msgTLF);
                    }
                }

            }
        } else {
            mostrarError("Selecciona un mecanico!");
        }
    }

    @FXML
    private void btnLimpiarAction(ActionEvent event
    ) {
        limpiarCampos();
    }

    @FXML
    private void onMouseClickedTableClientes(MouseEvent event
    ) {
        Mecanico mecanico = tvMecanicos.getSelectionModel().getSelectedItem();

        if (mecanico != null) {
            setMecanicoToView(mecanico);
        }
    }

    private void mostrarMecanicos() throws AplicacionException {
        listaMecanicos = FXCollections.<Mecanico>observableArrayList(LogicMecanico.getMecanicos());

        tvMecanicos.setItems(listaMecanicos);
    }

    private void limpiarCampos() {
        fieldDNI.setText(null);
        fieldNombre.setText(null);
        fieldTelefono.setText(null);
        fieldApellidos.setText(null);
        fieldFecha.setValue(null);
        choiceOcupado.setValue(null);
    }

    private void mostrarError(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(txt);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    private void setMecanicoToView(Mecanico m) {

        fieldDNI.setText(m.getDNI());
        fieldNombre.setText(m.getNombre());
        fieldTelefono.setText(String.valueOf(m.getTelefono()));
        fieldApellidos.setText(m.getApellidos());
        fieldFecha.setValue(m.getFecha_Nacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        if (m.isOcupado()) {
            choiceOcupado.setValue("Si");
        } else {
            choiceOcupado.setValue("No");
        }
    }

    private boolean comprobarCampos() {
        fieldDNI.setStyle(null);
        fieldNombre.setStyle(null);
        fieldApellidos.setStyle(null);
        fieldTelefono.setStyle(null);
        fieldFecha.setStyle(null);
        choiceOcupado.setStyle(null);

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
        if (choiceOcupado.getValue() == null) {
            choiceOcupado.setStyle("-fx-border-color: red;");
            campos = false;
        }

        return campos;
    }

}
