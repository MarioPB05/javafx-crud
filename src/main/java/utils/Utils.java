package utils;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Utils {

    /**
     * Muestra un mensaje de alerta.
     * @param title El título del mensaje.
     * @param message El contenido del mensaje.
     * @param type El tipo de alerta.
     */
    public static void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);

        // Obtener la ventana del Alert
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        // Agregar un icono al Alert
        stage.getIcons().add(new Image(Objects.requireNonNull(Utils.class.getResourceAsStream("/icons/people.png"))));

        alert.showAndWait();
    }

}
