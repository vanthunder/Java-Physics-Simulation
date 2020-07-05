package PhysicSimulation.Controller;

import PhysicSimulation.Objects.Manager.AssetBrowser;
import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Objects.Manager.AssetManager;
import PhysicSimulation.Objects.Manager.ParameterPane;
import PhysicSimulation.Objects.ShapeHelper;
import PhysicSimulation.SimualtionPipeline.Renderer;
import PhysicSimulation.SimualtionPipeline.SimulationLoop;
import javafx.animation.AnimationTimer;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.transform.Rotate;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author Marvin Schubert, Erwin Kling
 * @version 0.4
 */
public class GUIController implements Initializable {
    public Button startBtn;
    public Button pauseBtn;
    public Button resetBtn;
    public Button showListBtn;
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
    public ShapeHelper helper = new ShapeHelper();

    public TableView objectList;

    @FXML
    public Label debugLabel;
    @FXML
    public Label debugLabel2;
    @FXML
    public Label debugLabel3;

    public Slider speedSliderX;
    public TextField speedInputX;
    public Slider speedSliderY;
    public TextField speedInputY;

    boolean isList = false;

    @FXML
    public static HBox logPane = new HBox();

    public static TextField vLog = new TextField("text");

    private double defaultSliderValue = 0.03;
    DoubleProperty runningTime = new SimpleDoubleProperty(defaultSliderValue);

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
        logPane.getChildren().add(vLog);
        vLog.setVisible(true);
        runningTimeSlider.valueProperty().bindBidirectional(runningTime);
        runningTimeInput.textProperty().bind(runningTime.asString());
        runningTimeInput.setEditable(true);

        runningTimeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue)
            {

                Loop.setDt(runningTimeSlider.getValue());
                /*
                if (newValue == null)
                {
                    setDeltaTime(1);
                    return;
                }

                System.out.println("Value "+runningTimeSlider.getValue());
                setDeltaTime((double) newValue);
                System.out.println("Zeitraffer: x" + newValue);

                 */
            }
        });

        // Adding Listener to value property.
        speedSliderX.valueProperty().addListener(new ChangeListener<Number>()
        {

            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                                Number oldValue, Number newValue) {

                //speedInput.setText(String.valueOf(Loop.collision.physicObject.get(0).getVelocity()));
                speedInputX.setText(String.valueOf(speedSliderX.getValue()));
                for(int a = 0; a<Loop.getActiveAssetList().size(); a++)
                {
                    if(Loop.getActiveAssetList().get(a).getShape().getId().equals("sphere"))
                    {
                        Loop.getActiveAssetList().get(a).setVelocityX(Double.parseDouble(speedInputX.getText()));
                    }
                }
            }
        });

        speedSliderY.valueProperty().addListener(new ChangeListener<Number>()
        {

            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                                Number oldValue, Number newValue) {

                //speedInput.setText(String.valueOf(Loop.collision.physicObject.get(0).getVelocity()));
                speedInputY.setText(String.valueOf(speedSliderY.getValue()));
                for(int a = 0; a<Loop.getActiveAssetList().size(); a++)
                {
                    if(Loop.getActiveAssetList().get(a).getShape().getId().equals("sphere"))
                    {
                        Loop.getActiveAssetList().get(a).setVelocityY(Double.parseDouble(speedInputY.getText()));
                    }
                }
            }
        });

        startingAngleSlider.valueProperty().addListener(new ChangeListener<Number>()
        {

            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                                Number oldValue, Number newValue) {

                //speedInput.setText(String.valueOf(Loop.collision.physicObject.get(0).getVelocity()));
                startAngleInput.setText(String.valueOf(startingAngleSlider.getValue()));
                for(int a = 0; a<Loop.getActiveAssetList().size(); a++)
                {
                    if(Loop.getActiveAssetList().get(a).getShape().getId().equals("sphere"))
                    {
                        helper.setAngle(Loop.getActiveAssetList().get(a).getShape(), Double.parseDouble(startAngleInput.getText()));
                    }
                }
            }
        });

        //speedInputX.setText(String.valueOf(Loop.activeAssetList.get(0).getVelocityX()));
        Loop.setLabel(debugLabel);
        Loop.setLabel2(debugLabel2);
        Loop.setLabel3(debugLabel3);


    }

    // This Button starts the simulation
    public void startBtnPress(ActionEvent actionEvent) {
        Loop.start();
    }

    // This Button stops the process

    public void pauseBtnPress(ActionEvent actionEvent) {
        Loop.stop();
    }

    // This Button starts the reset method from the loop
    public void resetBtnPress(ActionEvent actionEvent) {
        Loop.resetLoop();
    }

    // Inits the Asset Browser with all its components
    public void initAssetBrowser() {
        Button createCircleBtn = assetBrowser.getCreateCircleBtn();
        createCircleBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parameterPane.getRadiusTextField().setText("10");
                parameterPane.getxPositionTextField().setText("40");
                parameterPane.getyPositionTextField().setText("40");
                parameterPane.getMassTextField().setText("4");
                parameterPane.getVelocityTextField().setText("0");
                parameterPane.getDirectionTextField().setText("0");
                parameterPane.chooseParameterPane("sphere");
                parameterPane.getCreateBtn().fire();
                parameterPane.setVisible(true);
                parameterPane.setTranslateX(20);

            }
        });

        Button createPlaneBtn = assetBrowser.getCreatePlaneBtn();
        createPlaneBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                parameterPane.chooseParameterPane("plane");
                parameterPane.setVisible(true);
                parameterPane.setTranslateX(20);
            }
        });

        Button createInclinedPlaneBtn = assetBrowser.getCreateInclinedPlaneBtn();
        createInclinedPlaneBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                parameterPane.chooseParameterPane("inclined_Plane");
                parameterPane.setVisible(true);
                parameterPane.setTranslateX(20);
            }
        });
    }

    // Inits the Parameter Stage with all its components
    public void initParameterStage() {
        Button createCircleBtn = parameterPane.getCreateBtn();

        // This button creates a circle based on the parameters
        createCircleBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                double radius = Double.valueOf(parameterPane.getRadiusTextField().getText());
                double x = Double.valueOf(parameterPane.getxPositionTextField().getText());
                double y = Double.valueOf(parameterPane.getyPositionTextField().getText());
                int mass = Integer.parseInt(parameterPane.getMassTextField().getText());
                double velocity = Double.valueOf(parameterPane.getVelocityTextField().getText());
                int direction = Integer.parseInt(parameterPane.getDirectionTextField().getText());
                if (radius <= x & radius <= y) {
                    Rotate rotate = new Rotate();
                    rotate.setAngle(0);
                    Circle circle = new Circle();
                    circle.setRadius(radius);
                    circle.getTransforms().add(rotate);
                    circle.setFill(new ImagePattern(circleTexture));
                    circle.setId("sphere");
                    circle.setCursor(Cursor.HAND);
                    AssetData newCircle = new AssetData("Kreis", circle, mass, velocity, 0, direction, "physic");
                    newCircle.getShape().setLayoutX(x);
                    newCircle.getShape().setLayoutY(y);
                    //renderer.createShape(newCircle);
                    Loop.activeAssetList.add(newCircle);
                    Loop.getTempList().add(newCircle);
                    Loop.updateLoop(newCircle);
                    Loop.getRenderer().getChildren().add(newCircle.getShape());
                    Loop.updateSimulation();
                    parameterPane.setVisible(false);
                } else if (radius >= x & radius >= y) {
                    System.out.println("Der Radius muss kleiner als der Viewport sein!");
                }
            }
        });

        Button createPlaneBtn = parameterPane.getCreatePlaneBtn();

        createPlaneBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Rotate rotate = new Rotate();
                rotate.setAngle(0);
                double x = Double.valueOf(parameterPane.getxPositionTextField().getText());
                double y = Double.valueOf(parameterPane.getyPositionTextField().getText());
                double width = Double.valueOf(parameterPane.getRadiusTextField().getText());
                Rectangle rectangle = new Rectangle();
                rectangle.setLayoutX(x);
                rectangle.setLayoutX(y);
                rectangle.setWidth(width);
                rectangle.setHeight(10);
                rectangle.setId("plane");
                rectangle.getTransforms().add(rotate);
                rectangle.getStyleClass().add("plane");
                rectangle.setCursor(Cursor.HAND);
                AssetData assetRectangle = new AssetData("Plane", rectangle, 0, 0, 0, 0, "static");
                Loop.activeAssetList.add(assetRectangle);
                Loop.getTempList().add(assetRectangle);
                Loop.getRenderer().getChildren().add(assetRectangle.getShape());
                Loop.updateSimulation();
                parameterPane.setVisible(false);
            }
        });

        Button createInclinedPlaneBtn = parameterPane.getCreateInclinedPlaneBtn();
        createInclinedPlaneBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Rotate rotate = new Rotate();
                double x = Double.valueOf(parameterPane.getxPositionTextField().getText());
                double y = Double.valueOf(parameterPane.getyPositionTextField().getText());
                double width = Double.valueOf(parameterPane.getRadiusTextField().getText());
                double angle = Double.valueOf(parameterPane.getMassTextField().getText());
                rotate.setAngle(angle);
                Rectangle rectangle = new Rectangle();
                rectangle.setLayoutX(x);
                rectangle.setLayoutX(y);
                rectangle.setWidth(width);
                rectangle.setHeight(10);
                rectangle.setId("inclinedPlane");
                rectangle.getTransforms().add(rotate);
                rectangle.getStyleClass().add("plane");
                rectangle.setCursor(Cursor.HAND);
                AssetData assetRectangle = new AssetData("InclinedPlane", rectangle, 0, 0, 0, 0, "static");
                Loop.activeAssetList.add(assetRectangle);
                Loop.getTempList().add(assetRectangle);
                Loop.getRenderer().getChildren().add(assetRectangle.getShape());
                Loop.updateSimulation();
                parameterPane.setVisible(false);
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
        }
        else
        if (isList == true)
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

    public void logChange(MouseEvent mouseEvent)
    {
    }

    public void updateLogPress(ActionEvent actionEvent)
    {
    }

    public static DoubleProperty dtFactor;

    public final DoubleProperty dtFProperty()
    {
        if (dtFactor == null)
        {
            this.dtFactor = new SimpleDoubleProperty(0.03);
        }
        return dtFactor;
    }

    public final void setDeltaTime(double factor)
    {
        this.dtFProperty().set(factor);
    }
}
