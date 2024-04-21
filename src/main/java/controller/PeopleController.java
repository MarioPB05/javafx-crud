package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Person;

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
        alert.showAndWait();
    }

    @FXML
    void addPerson(ActionEvent event) {
        try {
            String name = inputName.getText();
            String lastName = inputLastName.getText();
            Integer age = Integer.parseInt(inputAge.getText());

            Person person = new Person(name, lastName, age);

            if (!people.contains(person)) {
                // Agregar la persona a la lista de personas.
                people.add(person);

                // Actualizar la tabla de personas.
                tablePeople.setItems(people);

                // Limpiar los campos de texto.
                inputName.clear();
                inputLastName.clear();
                inputAge.clear();
            }else {
                showErrorMessage("La persona ya existe");
            }
        } catch (NumberFormatException e) {
            showErrorMessage("La edad debe ser un número entero");
        }
    }

}
