package PhysicSimulation.Controller;

import PhysicSimulation.Objects.Manager.AssetBrowser;
import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Objects.Manager.AssetManager;
import PhysicSimulation.Objects.Manager.ParameterPane;
import PhysicSimulation.SimualtionPipeline.Renderer;
import PhysicSimulation.SimualtionPipeline.SimulationLoop;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Marvin Schubert
 * @version 0.2
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
    public TextField xField;
    public TextField yField;
    public TextField radiusField;
    public TextField massField;
    public TextField directionField;
    public TextField velocityField;
    public Button createBtn;
    public ParameterPane parameterPane = new ParameterPane();
    public SimulationLoop Loop = new SimulationLoop();
    public Image circleTexture = new Image("PhysicSimulation\\Ressources\\Images\\kugel.png");
    

    // Init Method of the controller Method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Loop.activeAssetList = assetManager.getAssets();
        Loop.initRenderer(Loop.activeAssetList);
        //renderer = Loop.getRenderer();
        borderPaneContainer.setCenter(Loop.getRenderer());
        borderPaneContainer.setBottom(assetBrowser);
        borderPaneContainer.setRight(parameterPane);
        parameterPane.setVisible(false);
        initAssetBrowser();
        initParameterStage();

    }
    // Needs new Method
    public void updateRenderer(Shape shape)
    {
        renderer.updateRenderer(shape);
    }
    // This Button starts the simulation
    public void startBtnPress(ActionEvent actionEvent)
    {
        Loop.start();
    }
    // This Button stops the process
    public void pauseBtnPress(ActionEvent actionEvent)
    {
        Loop.stop();
    }
    public void resetBtnPress(ActionEvent actionEvent)
    {
        Loop.resetLoop();
    }
    // This Button is only for debug purposes
    public void debugBtnPress(ActionEvent actionEvent)
    {
        // It renders a black rectangle
        Rectangle rectangle = new Rectangle(20,40,100,100);
        rectangle.setFill(Color.ORANGE);
        AssetData assetData = new AssetData("Button Debug Shape", rectangle, 0, 0, 0, 0, "static");
        renderer.createShape(assetData);
    }
    // Inits the Asset Browser with all its components
    public void initAssetBrowser()
    {
        Button createCircleBtn = assetBrowser.getCreateCircleBtn();
        createCircleBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                System.out.println("True");
                parameterPane.setVisible(true);
            }
        });
    }
    // Inits the Parameter Stage with all its components
    public void initParameterStage()
    {
        Button createCircleBtn = parameterPane.getCreateBtn();
        
        // This button creates a circle based on the parameters
        createCircleBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                
                double radius = Double.valueOf(parameterPane.getRadiusTextField().getText());
                double x = Double.valueOf(parameterPane.getxPositionTextField().getText());
                double y = Double.valueOf(parameterPane.getyPositionTextField().getText());
                int mass = Integer.parseInt(parameterPane.getMassTextField().getText());
                double velocity = Double.valueOf(parameterPane.getVelocityTextField().getText());
                int direction = Integer.parseInt(parameterPane.getDirectionTextField().getText());
                if(radius <= x & radius <=y)
                {
                    Circle circle = new Circle(x, y, radius);
                    circle.setFill(new ImagePattern(circleTexture));
                    AssetData newCircle = new AssetData("Kreis", circle, mass, velocity, 0,direction, "physic");
                    //renderer.createShape(newCircle);
                    Loop.activeAssetList.add(newCircle);
                    Loop.updateLoop(newCircle);
                    Loop.getRenderer().getChildren().add(newCircle.getShape());
                    parameterPane.setVisible(false);
                }
                else
                if(radius >= x & radius >=y)
                {
                    System.out.println("Der Radius muss kleiner als der Viewport sein!");
                }


            }
        });
    }

    public void createBtnPress(ActionEvent actionEvent)
    {
        Circle circle = new Circle(Double.valueOf(xField.getText()),Double.valueOf(yField.getText()),Double.valueOf(radiusField.getText()));
        renderer.updateRenderer(circle);
    }





}
