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

    public void initAttributes(ObservableList<Person> people, Person person) {
        this.people = people;
        this.person = person;

        inputName.setText(person.getName());
        inputLastName.setText(person.getLastName());
        inputAge.setText(person.getAge().toString());
    }

    private void closeWindow() {
        Stage stage = (Stage) inputName.getScene().getWindow();
        stage.close();
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

                if (person != null) { // Estamos editando
                    person.setName(newPerson.getName());
                    person.setLastName(newPerson.getLastName());
                    person.setAge(newPerson.getAge());

                    showAlert("Información", "Persona editada correctamente", Alert.AlertType.INFORMATION);
                }else {
                    person = newPerson;
                    showAlert("Información", "Persona agregada correctamente", Alert.AlertType.INFORMATION);
                }

                closeWindow();
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

    public void exit() {
        person = null;
        closeWindow();
    }

}
