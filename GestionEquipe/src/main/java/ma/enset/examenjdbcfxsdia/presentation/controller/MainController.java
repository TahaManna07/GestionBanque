package ma.enset.examenjdbcfxsdia.presentation.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private void initialize() {
        // Code d'initialisation, si nécessaire
    }

    @FXML
    private void ouvrirApplication() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("MyApplication");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Vous pouvez ajouter d'autres méthodes en fonction de vos besoins
}