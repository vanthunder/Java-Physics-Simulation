package PhysicSimulation.SimualtionPipeline;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 * @author Marvin Schubert
 * @version 0.1
 */
public class Renderer extends Pane
{
    // Instants the Simulation Loop
    public SimulationLoop simualtionLoop = new SimulationLoop();
    // Variables for the width and height
    int width = 800;
    int height = 800;
    // Constructor of the renderer class
    public Renderer ()
    {
        super();
        this.setWidth(width);
        this.setHeight(height);
        this.setStyle("-fx-background-color: #0040ff");
        this.setMaxHeight(height);
        this.setMaxWidth(width);
        this.setMinHeight(height);
        this.setMinWidth(width);
        this.getChildren().add(simualtionLoop.getFpsCount());
        this.getChildren().add(simualtionLoop.getFramesCount());
        this.getChildren().set(1, simualtionLoop.getFramesCount()).setLayoutY(20);
        System.out.println(this.getChildren());
    }
    // Method to create a shape, adds it to the renderer and saves the shape into an arraylist
    public void createShape(Shape shape)
    {
        simualtionLoop.addShapeToList(shape);
        this.getChildren().add(shape);
    }
    // This Method starts the Loop
    public void startRenderer()
    {
        simualtionLoop.start();
    }
    // This Methods pauses the Loop
    public void stopRenderer()
    {
        simualtionLoop.stop();
    }

    public void updateDebugLabel(Label fps, Label frames)
    {
        simualtionLoop.setDebugLabel(fps, frames);
    }
}
