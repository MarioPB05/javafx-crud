package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PeopleApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PeopleApplication.class.getResource("/view/people-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 840, 400);
        stage.setTitle("Control de Personas");
        stage.setScene(scene);
        stage.setResizable(false);

        // Obtener el icono de la aplicación
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/people.png")));

        // Establecer el icono de la ventana
        stage.getIcons().add(icon);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
