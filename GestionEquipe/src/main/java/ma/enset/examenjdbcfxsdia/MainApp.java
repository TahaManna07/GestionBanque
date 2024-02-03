package ma.enset.examenjdbcfxsdia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger le fichier FXML de la vue principale
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));

        // Configurer la scène
        Scene scene = new Scene(root, 800, 600);

        // Configurer le stage
        primaryStage.setTitle("My Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}