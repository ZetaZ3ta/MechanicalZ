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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ZetaZeta
 */
public class AdministrarMotosController implements Initializable {

    ObservableList<Moto> listaMotos;

    @FXML
    private TableView<Moto> tvMotos;
    @FXML
    private TextField fieldBastidor, fieldMarca, fieldModelo, fieldMatricula, fieldKM, fieldDueño, fieldFiltrar;
    @FXML
    private TableColumn colBastidor, colMarca, colModelo, colMatricula, colDueño, colKM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
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
        try {

            Moto m = new Moto(fieldBastidor.getText(), fieldMarca.getText(), fieldModelo.getText(), fieldMatricula.getText(), Integer.parseInt(fieldKM.getText()));
            LogicMoto.añadir(m);

            mostrarMotos();
            limpiarCampos();
        } catch (AplicacionException ex) {
            Logger.getLogger(AdministrarClientesController.class.getName()).log(Level.SEVERE, null, ex);
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

            Moto m2 = new Moto(fieldBastidor.getText(), fieldMarca.getText(), fieldModelo.getText(), fieldMatricula.getText(), Integer.parseInt(fieldKM.getText()));

            LogicMoto.actualizar(m2);
            mostrarMotos();
        } else {
            mostrarError("Selecciona una moto!");
        }

    }

    @FXML
    private void btnAtrasAction(ActionEvent event) {
        LoginSucces((Node) event.getSource());
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

    private void mostrarInfo(String txt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info:");
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
        fieldDueño.setText(null);
        fieldFiltrar.setText(null);
    }

    private void setClienteToView(Moto m) {

        fieldBastidor.setText(m.getBastidor());
        fieldMarca.setText(m.getMarca());
        fieldModelo.setText(m.getModelo());
        fieldMatricula.setText(m.getMatricula());
        fieldKM.setText(String.valueOf(m.getKM()));
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

}
