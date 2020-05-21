package PhysicSimulation.SimualtionPipeline;

import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Physics.*;
import PhysicSimulation.Physics.Debug.Movement;
import PhysicSimulation.Physics.Debug.MovementWithAngle;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
/**
 * @author Marvin Schubert
 * @version 0.2
 */
public class SimulationLoop extends AnimationTimer
{
    public Label fpsCount = new Label("A");
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
    Collision collision = new Collision();

    long last_time = System.nanoTime();
    long time = 0;
    boolean loopStart = false;

    // Time example
    double t = 0.0;
    double dt = 0.03;
    double current_Time = System.nanoTime()*1E-9;
    double accumulator = 0;

    double preY = 0;
    double curY = 0;
    // Time example
    double timeNow = 0;
    double timeBetween = 0;
    public MovementWithAngle moveAngle = new MovementWithAngle();
    public Movement move = new Movement();


    private float delta;


    private Gravitation gravitation1 = new Gravitation();
    private Rotation rotate = new Rotation();
    public Path path = new Path();




    @Override
    public void handle(long now)
    {

            if(intCounter == 0)
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
                        gravitation1.debugGravitation(physicsCalculator.physicAssets.get(i).getShape(), physicsCalculator.physicAssets.get(i), dt, t);
                        //rotate.rotate(physicsCalculator.physicAssets.get(0),dt);
                        //rotate.rollDownDebug(physicsCalculator.physicAssets.get(0), activeAssetList.get(4), dt);

                    }
                    else
                    {
                        if(collision.physicObject.get(i).getCollision() && collision.physicObject.get(i).isIncCollision())
                        {
                            rotate.rollDownDebug(physicsCalculator.physicAssets.get(i), activeAssetList.get(4), dt);
                            //moveAngle.debugMovement(physicsCalculator.physicAssets.get(0), dt, rotate);
                            //rotate.rollDown(physicsCalculator.physicAssets.get(0), activeAssetList.get(4), dt);
                            System.out.println("Angle");
                            //rotate.rotate(physicsCalculator.physicAssets.get(0),dt);
                        }
                        else
                        if(collision.physicObject.get(i).getCollision() && collision.physicObject.get(i).isPlaneCollision())
                        {
                            //move.debugMove(physicsCalculator.physicAssets.get(0));
                            System.out.println("plane");
                            rotate.rotate(physicsCalculator.physicAssets.get(i),dt);
                        }

                        //moveAngle.debugMovement(physicsCalculator.physicAssets.get(0), dt);
                    }
                }
            }
        intCounter = 1;






    }
    //Update the Simulation with an physic object
    public void updateSimulation()
    {
        intCounter = 0;
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
        renderer.getChildren().add(fpsCount);
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
        fps.setTextFill(Color.ORANGE);
        frames.setText(String.valueOf("Frames: "+simulationLoopHelper.getAmountOfFrames()));
        frames.setTextFill(Color.ORANGE);
    }

    public void resetLoop()
    {
        for (int i = 0; i < collision.physicObject.size(); i++)
        {
            physicsCalculator.resetPhysic();
            collision.physicObject.get(i).getShape().setLayoutX(collision.physicObject.get(i).getStartPositionX());
            collision.physicObject.get(i).getShape().setLayoutY(collision.physicObject.get(i).getStartPositionY());
            collision.physicObject.get(i).setVelocityY(0);
            collision.physicObject.get(i).setVelocityX(0);
            collision.physicObject.get(i).setAngleVelocity(0);
            collision.physicObject.get(i).setAngleInclineVelocity(0);
            collision.physicObject.get(i).getShape().setRotate(0);
        }
    }

    @Override
    public void start()
    {
        super.start();
        time = System.nanoTime();
        last_time = System.nanoTime();
        loopStart = true;
    }
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
