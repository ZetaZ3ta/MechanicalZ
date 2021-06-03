package Presentacion;

import Aplicacion.AplicacionException;
import Aplicacion.GestorEscenas;
import Aplicacion.LogicCliente;
import Aplicacion.LogicProveedor;
import Aplicacion.LogicSecretario;
import Aplicacion.Modelo.Cliente;
import Aplicacion.Modelo.Proveedor;
import Aplicacion.Modelo.Secretario;
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
public class AdministrarProveedoresController implements Initializable {

    ObservableList<Proveedor> listaProveedores;
    ObservableList<Secretario> listaSecretarios;

    @FXML
    private TextField fieldCIF, fieldNombre, fieldDireccion;
    @FXML
    private TableView<Proveedor> tvProveedores;
    @FXML
    private TableColumn colCIF, colNombre, colDireccion, colSecretario;
    @FXML
    private ChoiceBox choiceSecretario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            listaSecretarios = FXCollections.<Secretario>observableArrayList(LogicSecretario.getSecretarios());

            for (Secretario listaSecretario : listaSecretarios) {

                choiceSecretario.getItems().add(listaSecretario.getDNI());
                //choiceSecretario.getItems().add(listaSecretario.getNombre() + " " + listaSecretario.getApellidos());
            }

            mostrarProveedores();

            colCIF.setCellValueFactory(new PropertyValueFactory<>("CIF"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre_Empresa"));
            colDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
            colSecretario.setCellValueFactory(new PropertyValueFactory<>("Secretario"));

        } catch (AplicacionException ex) {
            mostrarError("Error al inicializar: " + ex.toString());
            System.exit(1);
        }
    }

    @FXML
    private void btnAtrasAction(ActionEvent event) throws IOException {
        GestorEscenas escenas = new GestorEscenas();
        escenas.cambioEscena("MechanicalZ", "PantallaPrincipal.fxml", (Node) event.getSource());
    }

    @FXML
    private void btnAñadirAction(ActionEvent event) throws AplicacionException {

        Secretario s = LogicSecretario.getSecretario((String) choiceSecretario.getSelectionModel().getSelectedItem());

        Proveedor p = new Proveedor(Integer.parseInt(fieldCIF.getText()), fieldNombre.getText(), fieldDireccion.getText(), s);
        LogicProveedor.añadir(p);

        mostrarProveedores();
        limpiarCampos();
    }

    @FXML
    private void btnEliminarAction(ActionEvent event) throws AplicacionException {
        Proveedor p = tvProveedores.getSelectionModel().getSelectedItem();

        if (p != null) {
            LogicProveedor.eliminar(p);
            mostrarProveedores();
        } else {
            mostrarError("Selecciona un proveedor!");
        }

    }

    @FXML
    private void btnActualizarAction(ActionEvent event) throws AplicacionException {
        Secretario s = LogicSecretario.getSecretario((String) choiceSecretario.getSelectionModel().getSelectedItem());
        Proveedor p1 = tvProveedores.getSelectionModel().getSelectedItem();

        if (p1 != null) {

            Proveedor p2 = new Proveedor(Integer.parseInt(fieldCIF.getText()), fieldNombre.getText(), fieldDireccion.getText(), s);

            LogicProveedor.actualizar(p2);
            mostrarProveedores();
        } else {
            mostrarError("Selecciona una factura!");
        }
    }

    @FXML
    private void btnLimpiarAction(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    private void onMouseClickedTableClientes(MouseEvent event) {
        Proveedor proveedor = tvProveedores.getSelectionModel().getSelectedItem();

        if (proveedor != null) {
            setSecretarioToView(proveedor);
        }
    }

    private void mostrarError(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(txt);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    private void mostrarProveedores() throws AplicacionException {
        listaProveedores = FXCollections.<Proveedor>observableArrayList(LogicProveedor.getProveedores());

        tvProveedores.setItems(listaProveedores);
    }

    private void limpiarCampos() {
        fieldCIF.setText(null);
        fieldNombre.setText(null);
        fieldDireccion.setText(null);
        choiceSecretario.setValue(null);
    }

    private void setSecretarioToView(Proveedor p) {

        fieldCIF.setText(String.valueOf(p.getCIF()));
        fieldNombre.setText(p.getNombre_Empresa());
        fieldDireccion.setText(p.getDireccion());
        choiceSecretario.setValue(p.getSecretario().getDNI());

    }

}
