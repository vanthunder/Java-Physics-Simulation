package PhysicSimulation.SimualtionPipeline;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
/**
 * @author Marvin Schubert
 * @version 0.1
 */
public class SimulationLoop extends AnimationTimer
{
    public Label fpsCount = new Label();
    public Label framesCount = new Label();
    // Inits the Helper class of the Simulation loop
    public SimulationLoopHelper simulationLoopHelper = new SimulationLoopHelper();
    // Arraylist which contains all the shapes
    public ArrayList<Shape> objectList = new ArrayList<Shape>();
    // The simulation loop
    @Override
    public void handle(long l)
    {
        // Calculates the fps
        simulationLoopHelper.calculateFPS();
        // Counts the frames
        simulationLoopHelper.calculateAmountOfFrames();
        // Updates the Label
        setDebugLabel(fpsCount, framesCount);
    }
    // This Method adds a shape to the ArrayList
    public void addShapeToList (Shape shape)
    {
        objectList.add(shape);
    }

    public Shape getShape()
    {
        return objectList.get(0);
    }

    public void setDebugLabel(Label fps, Label frames)
    {
        fps.setText(String.valueOf("FPS: "+simulationLoopHelper.getFps()));
        fps.setTextFill(Color.ORANGE);
        frames.setText(String.valueOf("Frames: "+simulationLoopHelper.getAmountOfFrames()));
        frames.setTextFill(Color.ORANGE);
    }
    public Label getFpsCount()
    {
        return fpsCount;
    }

    public Label getFramesCount()
    {
        return framesCount;
    }
}
