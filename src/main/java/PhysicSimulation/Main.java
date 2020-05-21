package PhysicSimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



/**
 * @author Marvin Schubert
 * @version 0.2
 */

public class Main extends Application
{
    public Image image = new Image("Images/kugel.png");

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/GUI.fxml"));
        primaryStage.setTitle("Physic Simulation");
        Scene scene = new Scene(root, 1280, 1000);
        scene.getStylesheets().add(getClass().getResource("/CSS/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(image);
        primaryStage.show();

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
