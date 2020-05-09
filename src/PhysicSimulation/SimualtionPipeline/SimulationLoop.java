package PhysicSimulation.SimualtionPipeline;

import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Physics.Gravitation;
import PhysicSimulation.Physics.PhysicsCalculator;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
/**
 * @author Marvin Schubert
 * @version 0.2
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
    public ArrayList<Circle> Points = new ArrayList<Circle>();
    int i = 0;
    int counter = 0;
    public ArrayList<Circle>proofListA = new ArrayList<>();
    public ArrayList<Circle>proofListB = new ArrayList<>();
    // Inits the Renderer
    public Renderer renderer = new Renderer();

    public SimulationLoop()
    {
        //This Method inits the renderer to set up all visuals
        renderer.getChildren().add(rectangle);
    }

    @Override
    public void handle(long now)
    {
        // Calls a frame every 24 Milliseconds
        if(now - lastUpdate >= 30000000)
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
            drawPoints();
        }
    }
    //Updates the Renderer to Display Shape
    public void updateRenderer(Node node)
    {
        renderer.updateRenderer(node);
    }
    //Inits the renderer to display the visuals
    public void initRenderer(ArrayList<AssetData> list)
    {
        for(int i = 0; i<list.size(); i++)
        {
            //updateRenderer(list.get(i).getShape());
            renderer.getChildren().add(list.get(i).getShape());
            System.out.println(list.get(i).getShape());
        }
        System.out.println("A");
        updateRenderer(fpsCount);
        updateRenderer(framesCount);
        updateRenderer(showAcceleration);

    }

    //This Method draws the move points into the renderer
    public void drawPoints()
    {
        if(!physicsCalculator.getPointsOfMovementList().isEmpty())
        {
            for (int a = 0;a < physicsCalculator.getPointsOfMovementList().size(); a++ )
            {
                if (!renderer.getChildren().contains(physicsCalculator.getPointsOfMovementList().get(a)))
                {
                    renderer.getChildren().add(physicsCalculator.getPointsOfMovementList().get(a));
                }
            }
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

    public int getI()
    {
        return i;
    }

    public ArrayList<Circle> getProofListA()
    {
        return proofListA;
    }

    public ArrayList<Circle> getProofListB()
    {
        return proofListB;
    }
    public Renderer getRenderer()
    {
        return renderer;
    }
    public ArrayList<AssetData> getActiveAssetList()
    {
        return activeAssetList;
    }

}
