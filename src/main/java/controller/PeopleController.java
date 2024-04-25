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
import javafx.scene.control.TextField;
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

    private Person getSelectedPerson() {
        return tablePeople.getSelectionModel().getSelectedItem();
    }

    private void refreshTable() {
        tablePeople.setItems(people);
        tablePeople.refresh();
    }

    @FXML
    void addPerson() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modal-view.fxml"));

            Parent root = loader.load();

            ModalController controller = loader.getController();
            controller.initAttributes(people);

            Scene scene = new Scene(root, 200, 400);
            Stage stage = new Stage();

            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/people.png"))));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

            Person person = controller.getPerson();

            if (person != null) {
                people.add(person);
                refreshTable();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    @FXML
    void modify() {
        Person person = getSelectedPerson();

        if (person == null) {
            showAlert("Información", "Debes seleccionar una persona", Alert.AlertType.INFORMATION);
        }else {
            try {
                Person aux = getInputPerson();

                if (aux != null && !people.contains(aux)) {
                    person.setName(aux.getName());
                    person.setLastName(aux.getLastName());
                    person.setAge(aux.getAge());

                    // Actualizar la tabla de personas.
                    refreshTable();
                }else {
                    showAlert("Error", "La persona ya existe", Alert.AlertType.ERROR);
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "La edad debe ser un número entero", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    void selectedRow() {
        Person person = getSelectedPerson();

        if (person != null) {
            inputName.setText(person.getName());
            inputLastName.setText(person.getLastName());
            inputAge.setText(person.getAge().toString());
        }
    }

}
