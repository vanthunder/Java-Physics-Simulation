package PhysicSimulation.Controller;

import PhysicSimulation.Objects.Manager.AssetBrowser;
import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Objects.Manager.AssetManager;
import PhysicSimulation.Objects.Manager.ParameterPane;
import PhysicSimulation.SimualtionPipeline.Renderer;
import PhysicSimulation.SimualtionPipeline.SimulationLoop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
 * @version 0.4
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
    public Renderer renderer = new Renderer();
    public AssetBrowser assetBrowser = new AssetBrowser();
    public BorderPane borderPaneContainer;
    public AssetManager assetManager = new AssetManager();
    public TextField xField;
    public TextField yField;
    public TextField radiusField;
    public ParameterPane parameterPane = new ParameterPane();
    public SimulationLoop Loop = new SimulationLoop();
    public Image circleTexture = new Image("/Images/kugel.png");
    public Button showListBtn;
    public TableView objectList;
    boolean isList = false;


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
        objectList.setVisible(false);
        showListBtn.setVisible(false);

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

    // This Button starts the reset method from the loop
    public void resetBtnPress(ActionEvent actionEvent)
    {
        Loop.resetLoop();
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
                    Loop.updateSimulation();
                    parameterPane.setVisible(false);
                } else if (radius >= x & radius >= y)
                {
                    System.out.println("Der Radius muss kleiner als der Viewport sein!");
                }
            }
        });
    }


    // For Debug
    // This Method handles the button input action from the showListBtn
    public void showListBtnPress(ActionEvent actionEvent)
    {
        if (isList == false)
        {
            Loop.renderer.setVisible(false);
            objectList.setVisible(true);
            borderPaneContainer.setCenter(objectList);
            System.out.println("Renderer Disabled");
            isList = true;
            listInit();
        } else if (isList == true)
        {
            Loop.renderer.setVisible(true);
            objectList.setVisible(false);
            borderPaneContainer.setCenter(Loop.renderer);
            System.out.println("Renderer Activated");
            isList = false;
        }

    }

    // For Debug
    public void listInit()
    {
        Rectangle r = new Rectangle(10, 10);
        ObservableList items = FXCollections.observableArrayList(new AssetData("Kreis", r, 0, 0, 0, 0, "static"));
        objectList.getItems().add(0, "Test");

    }
}
