package PhysicSimulation.Controller;

import PhysicSimulation.Objects.Manager.AssetBrowser;
import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Objects.Manager.AssetManager;
import PhysicSimulation.SimualtionPipeline.Renderer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * @author Marvin Schubert
 * @version 0.1
 */
public class GUIController implements Initializable
{
    public Button startBtn;
    public Button pauseBtn;
    public Button resetBtn;
    public Slider speedSlider;
    public TextField speedInput;
    public Slider runningTimeSlider;
    public TextField runningTimeInput;
    public Slider startingAngleSlider;
    public TextField startAngleInput;
    public Slider elasticitySlider;
    public TextField elasticityInput;
    public Pane renderPane = new Renderer();
    public Renderer renderer = new Renderer();
    public AssetBrowser assetBrowser = new AssetBrowser();
    public BorderPane borderPaneContainer;
    public Button debugBtn;
    public Label fpsDebugLabel = new Label();
    public Label framesDebugLabel = new Label();
    public AssetManager assetManager = new AssetManager();

    // Init Method of the controller Method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        borderPaneContainer.setCenter(renderer);
        borderPaneContainer.setBottom(assetBrowser);
        renderer.createShape(assetManager.getShapeFromList(1));
    }
    // This Button starts the simulation
    public void startBtnPress(ActionEvent actionEvent)
    {
        renderer.startRenderer();
    }
    // This Button stops the process
    public void pauseBtnPress(ActionEvent actionEvent)
    {
        renderer.stopRenderer();
    }
    public void resetBtnPress(ActionEvent actionEvent)
    {
        renderer.resetRenderer();
    }
    // This Button is only for debug purposes
    public void debugBtnPress(ActionEvent actionEvent)
    {
        // It renders a black rectangle
        Rectangle rectangle = new Rectangle(20,40,100,100);
        rectangle.setFill(Color.ORANGE);
        AssetData assetData = new AssetData("Button Debug Shape", rectangle, 0, 0, 0);
        renderer.createShape(assetData);
    }
}
