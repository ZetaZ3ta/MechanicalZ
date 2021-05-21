package Presentacion;

import Aplicacion.AplicacionException;
import Aplicacion.GestorEscenas;
import Aplicacion.LogicCliente;
import Aplicacion.LogicMoto;
import Aplicacion.Modelo.Cliente;
import Aplicacion.Modelo.Moto;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
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
public class AdministrarClientesController implements Initializable {

    ObservableList<Cliente> listaClientes;

    @FXML
    private TextField fieldDNI, fieldNombre, fieldApellidos, fieldDireccion, fieldTelefono, fieldFiltrar;
    @FXML
    private DatePicker fieldFechaNacimiento;
    @FXML
    private TableView<Cliente> tvClientes;
    @FXML
    private TableColumn colDNI, colApellidos, colDireccion, colFecha, colNombre, colTelefono;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mostrarClientes();

            colDNI.setCellValueFactory(new PropertyValueFactory<>("DNI"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
            colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
            colFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha_Nacimiento"));

        } catch (Exception ex) {
            mostrarError("Error al inicializar: " + ex.toString());
            System.exit(1);
        }
    }

    @FXML
    private void btnAñadirAction(ActionEvent event) throws AplicacionException {

        Instant instant = Instant.from(fieldFechaNacimiento.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        List<Moto> motos = LogicMoto.getMotos();
        try {
            Cliente c = null;
            //Cliente c = new Cliente(fieldDNI.getText(), fieldNombre.getText(), fieldApellidos.getText(), fieldDireccion.getText(), Integer.parseInt(fieldTelefono.getText()), date, motos);
            LogicCliente.añadir(c);

            mostrarClientes();
            limpiarCampos();
        } catch (AplicacionException ex) {
            Logger.getLogger(AdministrarClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnEliminarAction(ActionEvent event) throws AplicacionException {
        Cliente c = tvClientes.getSelectionModel().getSelectedItem();

        if (c != null) {
            LogicCliente.eliminar(c);
            mostrarClientes();
        } else {
            mostrarError("Selecciona un cliente!");
        }

    }

    @FXML
    private void btnActualizarAction(ActionEvent event) throws AplicacionException {

        Cliente c1 = tvClientes.getSelectionModel().getSelectedItem();

        if (c1 != null) {
            Instant instant = Instant.from(fieldFechaNacimiento.getValue().atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            List<Moto> motos = LogicMoto.getMotos();
            Cliente c2 = null;
            //Cliente c2 = new Cliente(fieldDNI.getText(), fieldNombre.getText(), fieldApellidos.getText(), fieldDireccion.getText(), Integer.parseInt(fieldTelefono.getText()), date, motos);

            LogicCliente.actualizar(c2);
            mostrarClientes();
        } else {
            mostrarError("Selecciona un cliente!");
        }

    }

    @FXML
    private void btnLimpiarAction(ActionEvent event) {

        limpiarCampos();

    }

    @FXML
    private void onMouseClickedTableClientes(MouseEvent event) {

        Cliente cliente = tvClientes.getSelectionModel().getSelectedItem();

        if (cliente != null) {
            setClienteToView(cliente);
        }

    }

    @FXML
    private void btnAtrasAction(ActionEvent event) throws IOException {
        GestorEscenas escenas = new GestorEscenas();
        escenas.cambioEscena("MechanicalZ", "PantallaPrincipal.fxml", (Node) event.getSource());
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

    private void mostrarClientes() throws AplicacionException {
        listaClientes = FXCollections.<Cliente>observableArrayList(LogicCliente.getClientes());

        tvClientes.setItems(listaClientes);
    }

    private void limpiarCampos() {
        fieldDNI.setText(null);
        fieldNombre.setText(null);
        fieldApellidos.setText(null);
        fieldDireccion.setText(null);
        fieldFechaNacimiento.setValue(null);
        fieldTelefono.setText(null);
    }

    private void setClienteToView(Cliente c) {

        fieldDNI.setText(c.getDNI());
        fieldNombre.setText(c.getNombre());
        fieldApellidos.setText(c.getApellidos());
        fieldTelefono.setText(String.valueOf(c.getTelefono()));
        fieldDireccion.setText(c.getDireccion());
        fieldFechaNacimiento.setValue(c.getFecha_Nacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

}
