package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Person;

import java.util.Objects;

public class PeopleController {

    @FXML
    private TableColumn<Person, Integer> colAge;

    @FXML
    private TableColumn<Person, String> colLastName;

    @FXML
    private TableColumn<Person, String> colName;

    @FXML
    private TextField inputAge;

    @FXML
    private TextField inputLastName;

    @FXML
    private TextField inputName;

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

    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(message);

        // Obtener la ventana del Alert
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        // Agregar un icono al Alert
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/people.png"))));

        alert.showAndWait();
    }

    private Person getInputPerson() {
        String name = inputName.getText();
        String lastName = inputLastName.getText();
        Integer age = Integer.parseInt(inputAge.getText());

        if (name.isEmpty() || lastName.isEmpty()) {
            showErrorMessage("Todos los campos son obligatorios");
            return null;
        }

        return new Person(name, lastName, age);
    }

    private Person getSelectedPerson() {
        return tablePeople.getSelectionModel().getSelectedItem();
    }

    private void refreshTable() {
        tablePeople.setItems(people);
        tablePeople.refresh();
    }

    private void clearInputs() {
        inputName.clear();
        inputLastName.clear();
        inputAge.clear();
    }

    @FXML
    void addPerson() {
        try {
            Person person = getInputPerson();

            if (person != null && !people.contains(person)) {
                // Agregar la persona a la lista de personas.
                people.add(person);

                // Actualizar la tabla de personas.
                refreshTable();

                // Limpiar los campos de texto.
                clearInputs();
            }else {
                showErrorMessage("La persona ya existe");
            }
        } catch (NumberFormatException e) {
            showErrorMessage("La edad debe ser un número entero");
        }
    }

    @FXML
    void delete() {
        Person person = getSelectedPerson();

        if (person == null) {
            showErrorMessage("Debes seleccionar una persona");
        }else {
            people.remove(person);

            // Actualizar la tabla de personas.
            refreshTable();

            // Limpiar los campos de texto.
            clearInputs();
        }
    }

    @FXML
    void modify() {
        Person person = getSelectedPerson();

        if (person == null) {
            showErrorMessage("Debes seleccionar una persona");
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
                    showErrorMessage("La persona ya existe");
                }
            } catch (NumberFormatException e) {
                showErrorMessage("La edad debe ser un número entero");
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
