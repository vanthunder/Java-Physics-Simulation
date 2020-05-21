package PhysicSimulation.SimualtionPipeline;

import PhysicSimulation.Objects.Manager.AssetData;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

/**
 * @author Marvin Schubert
 * @version 0.1
 */
public class Renderer extends Pane
{
    // Instants the Simulation Loop
    //public SimulationLoop simualtionLoop = new SimulationLoop();
    public ArrayList<Circle> proofListA = new ArrayList<>();
    public ArrayList<Circle> proofListB = new ArrayList<>();
    int i = 0;
    Circle circle = new Circle(10, 10, 10);
    // Variables for the width and height
    int width = 800;
    int height = 800;
    // Constructor of the renderer class
    public Renderer ()
    {
        super();
        this.setWidth(width);
        this.setHeight(height);
        //this.setStyle("-fx-background-color: #0040ff");
        this.getStyleClass().add("render-window");
        this.setMaxHeight(height);
        this.setMaxWidth(width);
        this.setMinHeight(height);
        this.setMinWidth(width);
        // Needs to implement into the New Loop Methods
        /*
        this.getChildren().add(simualtionLoop.getFpsCount());
        this.getChildren().add(simualtionLoop.getFramesCount());
        this.getChildren().set(1, simualtionLoop.getFramesCount()).setLayoutY(20);
        this.getChildren().add(simualtionLoop.getShowAcceleration());
        this.getChildren().set(2, simualtionLoop.getShowAcceleration()).setLayoutY(10);
        this.getChildren().set(2, simualtionLoop.getShowAcceleration()).setLayoutX(500);

         */

    }


    // Method to create a shape, adds it to the renderer and saves the shape into an arraylist
    public void createShape(AssetData assetData)
    {
        //simualtionLoop.addShapeToList(assetData);
        //this.getChildren().add(assetData.getShape());
    }
    // This Method starts the Loop
    public void startRenderer()
    {
        //simualtionLoop.start();
    }
    // This Methods pauses the Loop
    public void stopRenderer()
    {
        //simualtionLoop.stop();
    }
    // This method resets the loop
    public void resetRenderer()
    {
        //simualtionLoop.resetLoop();
    }

    public void updateRenderer(Node node)
    {
        //this.getChildren().add(node);
    }

    public void updateDebugLabel(Label fps, Label frames)
    {
        //simualtionLoop.setDebugLabel(fps, frames);
    }

    public void updateLoop(SimulationLoop Loop)
    {
        //simualtionLoop = Loop;
    }




}