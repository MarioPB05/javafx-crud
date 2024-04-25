package controller;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Person;

import static utils.Utils.showAlert;

public class ModalController {

    public TextField inputAge;
    public TextField inputLastName;
    public TextField inputName;

    private Person person;
    private ObservableList<Person> people;

    public void initAttributes(ObservableList<Person> people) {
        this.people = people;
    }

    private Person getInputPerson() {
        String name = inputName.getText();
        String lastName = inputLastName.getText();
        Integer age = Integer.parseInt(inputAge.getText());

        if (name.isEmpty() || lastName.isEmpty()) {
            showAlert("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return null;
        }

        return new Person(name, lastName, age);
    }

    public void savePerson() {
        try {
            Person newPerson = getInputPerson();

            if (newPerson != null && !people.contains(newPerson)) {
                person = newPerson;

                showAlert("Información", "Persona agregada correctamente", Alert.AlertType.INFORMATION);

                Stage stage = (Stage) inputName.getScene().getWindow();
                stage.close();
            }else {
                showAlert("Error", "La persona ya existe", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "La edad debe ser un número entero", Alert.AlertType.ERROR);
        }
    }

    public Person getPerson() {
        return person;
    }

}
