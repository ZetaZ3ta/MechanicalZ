package Presentacion;

import Aplicacion.AplicacionException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ZetaZeta
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

            Cliente c = new Cliente(fieldDNI.getText(), fieldNombre.getText(), fieldApellidos.getText(), fieldDireccion.getText(), Integer.parseInt(fieldTelefono.getText()), date, motos);
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

            Cliente c2 = new Cliente(fieldDNI.getText(), fieldNombre.getText(), fieldApellidos.getText(), fieldDireccion.getText(), Integer.parseInt(fieldTelefono.getText()), date, motos);

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
    private void btnAtrasAction(ActionEvent event) {
        LoginSucces((Node) event.getSource());
    }

//    @FXML
//    private void fieldChangedFiltrar(InputMethodEvent event) {
//        System.out.println(event.getCommitted());
//
//    }

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

    @FXML
    private void fieldChangedFiltrar(InputMethodEvent event) {
    }

}
