package PhysicSimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Marvin Schubert
 * @version 0.2
 */

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXML\\GUI.fxml"));
        primaryStage.setTitle("Physic Simulation");
        Scene scene = new Scene(root, 1200, 1000);
        scene.getStylesheets().add(getClass().getResource("CSS\\Stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
