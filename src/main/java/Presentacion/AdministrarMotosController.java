package Presentacion;

import Aplicacion.AplicacionException;
import Aplicacion.GestorEscenas;
import Aplicacion.LogicCliente;
import Aplicacion.LogicMoto;
import Aplicacion.Modelo.Cliente;
import Aplicacion.Modelo.Moto;
import Aplicacion.Reglas;
import java.io.IOException;
import java.net.URL;
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
public class AdministrarMotosController implements Initializable {

    ObservableList<Moto> listaMotos;
    ObservableList<Cliente> listaClientes;

    @FXML
    private TableView<Moto> tvMotos;
    @FXML
    private TableColumn colBastidor, colMarca, colModelo, colMatricula, colDueño, colKM;
    @FXML
    private ChoiceBox choiceDueño;
    @FXML
    private TextField fieldBastidor, fieldMarca, fieldModelo, fieldMatricula, fieldKM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            listaClientes = FXCollections.<Cliente>observableArrayList(LogicCliente.getClientes());

            for (Cliente listaCliente : listaClientes) {

                choiceDueño.getItems().add(listaCliente.getDNI());
                //choiceDueño.getItems().add(listaCliente.getNombre() + " " + listaCliente.getApellidos());
            }

            mostrarMotos();

            colBastidor.setCellValueFactory(new PropertyValueFactory<>("Bastidor"));
            colMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
            colModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
            colMatricula.setCellValueFactory(new PropertyValueFactory<>("Matricula"));
            colKM.setCellValueFactory(new PropertyValueFactory<>("KM"));
            colDueño.setCellValueFactory(new PropertyValueFactory<>("Dueño"));

        } catch (Exception ex) {
            mostrarError("Error al inicializar: " + ex.toString());
            System.exit(1);
        }
    }

    @FXML
    private void btnAñadirAction(ActionEvent event) {

        if (comprobarCampos()) {
            String msgBastidor = Reglas.bastidor(fieldBastidor.getText());
            String msgMatricula = Reglas.matricula(fieldMatricula.getText());

            if (msgBastidor.equals("") && msgMatricula.equals("")) {
                try {
                    Cliente c = LogicCliente.getCliente((String) choiceDueño.getSelectionModel().getSelectedItem());
                    Moto m = new Moto(fieldBastidor.getText(), fieldMarca.getText(), fieldModelo.getText(), fieldMatricula.getText(), Integer.parseInt(fieldKM.getText()), c);
                    LogicMoto.añadir(m);

                    mostrarMotos();
                    limpiarCampos();
                } catch (AplicacionException ex) {
                    Logger.getLogger(AdministrarClientesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (!msgBastidor.equals("")) {
                    mostrarError(msgBastidor);
                }
                if (!msgMatricula.equals("")) {
                    mostrarError(msgMatricula);
                }
            }

        }

    }

    @FXML
    private void btnEliminarAction(ActionEvent event) throws AplicacionException {

        Moto m = tvMotos.getSelectionModel().getSelectedItem();

        if (m != null) {
            LogicMoto.eliminar(m);
            mostrarMotos();
        } else {
            mostrarError("Selecciona una moto!");
        }

    }

    @FXML
    private void btnActualizarAction(ActionEvent event) throws AplicacionException {
        Moto m1 = tvMotos.getSelectionModel().getSelectedItem();

        if (m1 != null) {
            if (comprobarCampos()) {
                String msgMatricula = Reglas.matricula(fieldMatricula.getText());

                if (msgMatricula.equals("")) {

                    Cliente c = LogicCliente.getCliente((String) choiceDueño.getSelectionModel().getSelectedItem());
                    Moto m2 = new Moto(fieldBastidor.getText(), fieldMarca.getText(), fieldModelo.getText(), fieldMatricula.getText(), Integer.parseInt(fieldKM.getText()), c);

                    LogicMoto.actualizar(m2);
                    mostrarMotos();
                } else {
                    if (!msgMatricula.equals("")) {
                        mostrarError(msgMatricula);
                    }

                }
            }

        } else {
            mostrarError("Selecciona una moto!");
        }

    }

    @FXML
    private void btnAtrasAction(ActionEvent event) throws IOException {
        GestorEscenas escenas = new GestorEscenas();
        escenas.cambioEscena("MechanicalZ", "PantallaPrincipal.fxml", (Node) event.getSource());
    }

    @FXML
    private void btnLimpiarAction(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    private void onMouseClickedTableMotos(MouseEvent event) {
        Moto moto = tvMotos.getSelectionModel().getSelectedItem();

        if (moto != null) {
            setClienteToView(moto);
        }
    }

    private void mostrarMotos() throws AplicacionException {
        listaMotos = FXCollections.<Moto>observableArrayList(LogicMoto.getMotos());

        tvMotos.setItems(listaMotos);
    }

    private void mostrarError(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(txt);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    private void limpiarCampos() {

        fieldBastidor.setText(null);
        fieldMarca.setText(null);
        fieldModelo.setText(null);
        fieldMatricula.setText(null);
        fieldKM.setText(null);
        choiceDueño.setValue(null);
    }

    private void setClienteToView(Moto m) {

        fieldBastidor.setText(m.getBastidor());
        fieldMarca.setText(m.getMarca());
        fieldModelo.setText(m.getModelo());
        fieldMatricula.setText(m.getMatricula());
        fieldKM.setText(String.valueOf(m.getKM()));
        choiceDueño.setValue(m.getDueño().getDNI());
    }

    private boolean comprobarCampos() {
        fieldBastidor.setStyle(null);
        fieldKM.setStyle(null);
        fieldMarca.setStyle(null);
        fieldMatricula.setStyle(null);
        fieldModelo.setStyle(null);

        boolean campos = true;
        if (fieldBastidor.getText().isEmpty()) {
            fieldBastidor.setStyle("-fx-border-color: red;");
            campos = false;
        }
        if (fieldKM.getText().isEmpty()) {
            fieldKM.setStyle("-fx-border-color: red;");
            campos = false;
        }
        if (fieldMarca.getText().isEmpty()) {
            fieldMarca.setStyle("-fx-border-color: red;");
            campos = false;
        }
        if (fieldMatricula.getText().isEmpty()) {
            fieldMatricula.setStyle("-fx-border-color: red;");
            campos = false;
        }
        if (fieldModelo.getText().isEmpty()) {
            fieldModelo.setStyle("-fx-border-color: red;");
            campos = false;
        }

        return campos;
    }

}
