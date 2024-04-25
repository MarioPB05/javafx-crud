package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;

import java.io.IOException;
import java.util.Objects;

import static utils.Utils.showAlert;

public class PeopleController {

    @FXML
    private TableColumn<Person, Integer> colAge;

    @FXML
    private TableColumn<Person, String> colLastName;

    @FXML
    private TableColumn<Person, String> colName;

    @FXML
    private TableView<Person> tablePeople;

    private ObservableList<Person> people;

    /**
     * Inicializa el controlador.
     */
    public void initialize() {
        people = FXCollections.observableArrayList();

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
    }

    /**
     * Obtiene la persona seleccionada en la tabla.
     * @return La persona seleccionada.
     */
    private Person getSelectedPerson() {
        return tablePeople.getSelectionModel().getSelectedItem();
    }

    /**
     * Refresca la tabla de personas.
     */
    private void refreshTable() {
        tablePeople.setItems(people);
        tablePeople.refresh();
    }

    /**
     * Muestra la ventana modal para agregar o modificar una persona.
     * @param person La persona a modificar, si es null se agregará una nueva persona.
     * @return La persona modificada o agregada.
     */
    private Person setModalScene(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modal-view.fxml"));

            Parent root = loader.load();

            ModalController controller = loader.getController();

            if (person != null) {
                controller.initAttributes(people, person);
            }else {
                controller.initAttributes(people);
            }

            Scene scene = new Scene(root, 200, 400);
            Stage stage = new Stage();

            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/people.png"))));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

            return controller.getPerson();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Agrega una persona a la lista de personas.
     */
    @FXML
    void addPerson() {
        Person person = setModalScene(null);

        if (person == null) return;

        people.add(person);
        refreshTable();
    }

    /**
     * Elimina una persona de la lista de personas.
     */
    @FXML
    void delete() {
        Person person = getSelectedPerson();

        if (person == null) {
            showAlert("Información", "Debes seleccionar una persona", Alert.AlertType.INFORMATION);
        }else {
            people.remove(person);

            // Actualizar la tabla de personas.
            refreshTable();

            showAlert("Información", "Persona eliminada correctamente", Alert.AlertType.INFORMATION);
        }
    }

    /**
     * Modifica una persona de la lista de personas.
     */
    @FXML
    void modify() {
        Person person = getSelectedPerson();

        if (person == null) {
            showAlert("Información", "Debes seleccionar una persona", Alert.AlertType.INFORMATION);
        }else {
            Person aux = setModalScene(person);

            if (aux != null) refreshTable();
        }
    }

}
