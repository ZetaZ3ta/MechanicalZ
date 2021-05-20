package Presentacion;

import Aplicacion.AplicacionException;
import Aplicacion.GestorEscenas;
import Aplicacion.LogicCliente;
import Aplicacion.LogicFactura;
import Aplicacion.LogicSecretario;
import Aplicacion.Modelo.Cliente;
import Aplicacion.Modelo.Factura;
import Aplicacion.Modelo.Secretario;
import Datos.DatosException;
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
public class AdministrarFacturasController implements Initializable {

    ObservableList<Factura> listaFacturas;
    ObservableList<Cliente> listaClientes;
    ObservableList<Secretario> listaSecretarios;

    @FXML
    private TextField fieldID, fieldPrecio, fieldDescripcion;
    @FXML
    private TableView<Factura> tvFacturas;
    @FXML
    private TableColumn colID, colDescripcion, colPrecio, colIVA, colCliente, colSecretario;
    @FXML
    private ChoiceBox choiceCliente;
    @FXML
    private ChoiceBox choiceSecretario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            listaClientes = FXCollections.<Cliente>observableArrayList(LogicCliente.getClientes());
            listaSecretarios = FXCollections.<Secretario>observableArrayList(LogicSecretario.getSecretarios());

            for (Cliente clientes : listaClientes) {
                choiceCliente.getItems().add(clientes.getNombre() + " " + clientes.getApellidos());
            }

            for (Secretario secretarios : listaSecretarios) {
                choiceSecretario.getItems().add(secretarios.getNombre() + " " + secretarios.getApellidos());
            }

            mostrarFacturas();

            colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            colDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
            colPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
            colIVA.setCellValueFactory(new PropertyValueFactory<>("IVA"));
            colCliente.setCellValueFactory(new PropertyValueFactory<>("Cliente_DNI"));
            colSecretario.setCellValueFactory(new PropertyValueFactory<>("Secretario_DNI"));

        } catch (Exception ex) {
            mostrarError("Error al inicializar: " + ex.toString());
            System.exit(1);
        }

    }

    @FXML
    private void btnAñadirAction(ActionEvent event) throws AplicacionException, DatosException {
        Cliente c = LogicCliente.getCliente((String) choiceCliente.getSelectionModel().getSelectedItem());
        Secretario s = LogicSecretario.getSecretario((String) choiceSecretario.getSelectionModel().getSelectedItem());

        Factura f = new Factura(Integer.parseInt(fieldID.getText()), fieldDescripcion.getText(), Integer.parseInt(fieldPrecio.getText()), 21, c, s);
        LogicFactura.añadir(f);

        mostrarFacturas();
        limpiarCampos();
    }

    @FXML
    private void btnEliminarAction(ActionEvent event) {

    }

    @FXML
    private void btnActualizarAction(ActionEvent event) {

    }

    @FXML
    private void btnLimpiarAction(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    private void btnAtrasAction(ActionEvent event) throws IOException {
        GestorEscenas escenas = new GestorEscenas();
        escenas.cambioEscena("MechanicalZ", "PantallaPrincipal.fxml", (Node) event.getSource());
    }

    @FXML
    private void onMouseClickedTableClientes(MouseEvent event) {
        Factura factura = tvFacturas.getSelectionModel().getSelectedItem();

        if (factura != null) {
            setFacturaToView(factura);
        }
    }

    private void mostrarFacturas() throws AplicacionException {
        listaFacturas = FXCollections.<Factura>observableArrayList(LogicFactura.getFacturas());

        tvFacturas.setItems(listaFacturas);
    }

    private void limpiarCampos() {
        fieldID.setText(null);
        fieldDescripcion.setText(null);
        fieldPrecio.setText(null);
        choiceCliente.setValue(null);
        choiceSecretario.setValue(null);
    }

    private void mostrarError(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(txt);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    private void setFacturaToView(Factura u) {
        fieldID.setText(String.valueOf(u.getID()));
        fieldDescripcion.setText(u.getDescripcion());
        fieldPrecio.setText(String.valueOf(u.getPrecio()));
        choiceCliente.setValue(u.getCliente().getNombre() + u.getCliente().getApellidos());
        choiceSecretario.setValue(u.getSecretario().getNombre() + u.getCliente().getApellidos());
    }

}
