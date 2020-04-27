package PhysicSimulation.SimualtionPipeline;

import PhysicSimulation.Physics.Gravitation;
import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Physics.PhysicsCalculator;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    public Label showAcceleration = new Label();
    // Inits the Helper class of the Simulation loop
    public SimulationLoopHelper simulationLoopHelper = new SimulationLoopHelper();
    // Arraylist which contains all the shapes
    public ArrayList<AssetData> activeAssetList = new ArrayList<AssetData>();
    // Inits the debug Gravitation
    public Gravitation gravitation;
    //Debug Rectangle
    public Rectangle rectangle = new Rectangle(10, 10, 10, 10);
    // Inits the Physics Calculator
    public PhysicsCalculator physicsCalculator = new PhysicsCalculator();
    // The simulation loop handles frames in nanoseconds
    private long lastUpdate = 0;
    private int intCounter = 0;

    @Override
    public void handle(long now)
    {
        // Calls a frame every 12 Milliseconds
        if(now - lastUpdate >= 12000000)
        {
            // Calculates the fps
            simulationLoopHelper.calculateFPS();
            // Counts the frames
            simulationLoopHelper.calculateAmountOfFrames();
            // Updates the Label
            setDebugLabel(fpsCount, framesCount);
            //Debug Gravitation
            if(intCounter == 0)
            {
                physicsCalculator.initCalculation(activeAssetList);
                intCounter = 1;
            }
            physicsCalculator.calculatePhysics();
            //System.out.println(lastUpdate + " NOW: "+ now);
            lastUpdate = now;
            showAcceleration.setText("Geschwindigkeit: "+String.valueOf(physicsCalculator.getVelocity()+" m/s"));
        }


    }
    // This Method adds a shape to the ArrayList
    public void addShapeToList (AssetData assetData)
    {
        activeAssetList.add(assetData);
        intCounter = 0;
        System.out.println("Das Objekt: " + assetData.getName() + " wurde der aktiven Liste übergeben!");
    }

    public Shape getShape(int index)
    {
        return activeAssetList.get(index).getShape();
    }

    public void setDebugLabel(Label fps, Label frames)
    {
        fps.setText(String.valueOf("FPS: "+simulationLoopHelper.getFps()));
        fps.setTextFill(Color.ORANGE);
        frames.setText(String.valueOf("Frames: "+simulationLoopHelper.getAmountOfFrames()));
        frames.setTextFill(Color.ORANGE);
    }

    public void resetLoop()
    {
        activeAssetList.get(0).getShape().setTranslateX(activeAssetList.get(0).getRestX());
        activeAssetList.get(0).getShape().setTranslateY(activeAssetList.get(0).getRestY());
        physicsCalculator.resetPhysic();
    }

    public Label getFpsCount()
    {
        return fpsCount;
    }

    public Label getFramesCount()
    {
        return framesCount;
    }

    public Label getShowAcceleration()
    {
        return showAcceleration;
    }
}
