package PhysicSimulation.SimualtionPipeline;

import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Physics.*;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
/**
 * @author Marvin Schubert
 * @version 0.6
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
    // Inits the Gravitation
    private Gravitation gravitation = new Gravitation();
    // Inits the Physics Calculator
    public PhysicsCalculator physicsCalculator = new PhysicsCalculator();
    // The simulation loop handles frames in nanoseconds
    private int intCounter = 0;
    public ArrayList<Circle> Points = new ArrayList<Circle>();
    int i = 0;
    public ArrayList<Circle> proofListA = new ArrayList<>();
    public ArrayList<Circle> proofListB = new ArrayList<>();
    // Inits the Renderer
    public Renderer renderer = new Renderer();
    Collision collision = new Collision();
    long last_time = System.nanoTime();
    long time = 0;
    boolean loopStart = false;
    private Rotation rotate = new Rotation();
    // Time
    double t = 0.0;
    double dt = 0.03;


    //The main loop
    @Override
    public void handle(long now)
    {

        if (intCounter == 0)
        {
                physicsCalculator.initCalculation(activeAssetList);
                collision.physicObject.addAll(physicsCalculator.physicAssets);
                collision.staticObject.addAll(physicsCalculator.staticAssets);
                System.out.println(physicsCalculator.physicAssets.get(0));
                collision.collisions();
            }
            if(intCounter == 1)
            {
                for (int i = 0; i <collision.physicObject.size(); i++)
                {
                    collision.checkShapeIntersection(physicsCalculator.physicAssets.get(i).getShape(),physicsCalculator.physicAssets.get(i));
                    if(!physicsCalculator.physicAssets.get(i).getCollision())
                    {
                        gravitation.gravitationForce(physicsCalculator.physicAssets.get(i).getShape(), physicsCalculator.physicAssets.get(i), dt, t);
                    }
                    else
                    {
                        if(collision.physicObject.get(i).getCollision() && collision.physicObject.get(i).isIncCollision())
                        {
                            rotate.rollDownDebug(physicsCalculator.physicAssets.get(i), activeAssetList.get(4), dt);
                            System.out.println("Angle");
                        } else if (collision.physicObject.get(i).getCollision() && collision.physicObject.get(i).isPlaneCollision())
                        {
                            System.out.println("plane");
                            rotate.rotate(physicsCalculator.physicAssets.get(i), dt);
                        }
                    }
                }
            }
        intCounter = 1;
        simulationLoopHelper.calculateFPS();
        setDebugLabel(fpsCount, framesCount);
    }

    //Update the Simulation with an physic object
    public void updateSimulation()
    {
        intCounter = 0;
    }
    //Inits the renderer to display the visuals
    public void initRenderer(ArrayList<AssetData> list)
    {
        renderer.getChildren().add(fpsCount);
        renderer.getChildren().get(0).setLayoutY(20);
        renderer.getChildren().get(0).setLayoutX(20);
        for(int i = 0; i<list.size(); i++)
        {
            renderer.getChildren().add(list.get(i).getShape());
            System.out.println(list.get(i).getShape());
        }
        renderer.getChildren().add(framesCount);
        //updateRenderer(framesCount);
        //updateRenderer(showAcceleration);
    }
    public void updateLoop(AssetData assetData)
    {
        physicsCalculator.physicAssets.add(assetData);
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
        fps.setTextFill(Color.RED);
        //frames.setText(String.valueOf("Frames: "+simulationLoopHelper.getAmountOfFrames()));
        //frames.setTextFill(Color.ORANGE);
    }

    // Resets the physic object with its start parameters
    public void resetLoop()
    {
        for (int i = 0; i < collision.physicObject.size(); i++)
        {
            collision.physicObject.get(i).getShape().setLayoutX(collision.physicObject.get(i).getStartPositionX());
            collision.physicObject.get(i).getShape().setLayoutY(collision.physicObject.get(i).getStartPositionY());
            collision.physicObject.get(i).setVelocityY(0);
            collision.physicObject.get(i).setVelocityX(0);
            collision.physicObject.get(i).setAngleVelocity(0);
            collision.physicObject.get(i).setAngleInclineVelocity(0);
            collision.physicObject.get(i).getShape().setRotate(0);
            collision.physicObject.get(i).setVelocity(0);
            collision.physicObject.get(i).setPositive(true);
            collision.physicObject.get(i).setPlaneCollision(false);
            collision.physicObject.get(i).setIncCollision(false);
            collision.physicObject.get(i).setCollision(false);
        }
    }

    // Starts the loop
    @Override
    public void start()
    {
        super.start();
        time = System.nanoTime();
        last_time = System.nanoTime();
        loopStart = true;
    }

    // pauses the loop
    @Override
    public void stop()
    {
        super.stop();
        last_time = System.nanoTime();
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

    public Renderer getRenderer()
    {
        return renderer;
    }

    public ArrayList<AssetData> getActiveAssetList()
    {
        return activeAssetList;
    }

}
