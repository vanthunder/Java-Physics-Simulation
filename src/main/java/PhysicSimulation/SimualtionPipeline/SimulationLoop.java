package PhysicSimulation.SimualtionPipeline;

import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Objects.ShapeHelper;
import PhysicSimulation.Physics.*;
import com.sun.glass.events.WheelEvent;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
/**
 * @author Marvin Schubert
 * @version 0.6
 */
public class SimulationLoop extends AnimationTimer
{
    public ArrayList<AssetData> tempList = new ArrayList<>();
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

    // Mouse Coordinates
    double mouseX;
    double mouseY;
    // Proof boolean for drag detection
    boolean dragDetected = false;
    ShapeHelper helper = new ShapeHelper();
    double deltaX;
    double deltaY;

    public SimulationLoop()
    {
        renderer.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        renderer.addEventFilter(MouseEvent.MOUSE_MOVED, mouseMove);
        renderer.addEventFilter(ScrollEvent.ANY, mouseWheel);
        initDragnDrop("click");
    }


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
                    collision.checkShapeIntersection(collision.physicObject.get(i).getShape(), collision.physicObject.get(i));
                    if (!collision.physicObject.get(i).getCollision())
                    {
                        gravitation.gravitationForce(collision.physicObject.get(i).getShape(), collision.physicObject.get(i), dt, t);
                    } else
                    {
                        if (collision.physicObject.get(i).getCollision() && collision.physicObject.get(i).isIncCollision())
                        {
                            rotate.rollDownDebug(collision.physicObject.get(i), activeAssetList.get(4), dt);
                            System.out.println("Angle");
                        } else if (collision.physicObject.get(i).getCollision() && collision.physicObject.get(i).isPlaneCollision())
                        {
                            System.out.println("plane");
                            rotate.rotate(collision.physicObject.get(i), dt);
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

        if (tempList.get(0).getPhysicType() == "static")
        {
            System.out.println("Static Object");
            collision.staticObject.add(tempList.get(0));
            tempList.remove(0);
        } else if (tempList.get(0).getPhysicType() == "physic")
        {
            System.out.println("physic Object");
            collision.physicObject.add(tempList.get(0));
            tempList.remove(0);
        }
        /*

        physicsCalculator.initCalculation(activeAssetList);
        collision.physicObject.addAll(physicsCalculator.physicAssets);
        collision.staticObject.addAll(physicsCalculator.staticAssets);
        System.out.println(physicsCalculator.physicAssets.get(0));
        collision.collisions();

         */
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
            //collision.physicObject.get(i).getShape().setLayoutY(collision.physicObject.get(i).getStartPositionY());
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
            //collision.physicObject.get(i).getShape().setTranslateY(10);
            //collision.physicObject.get(i).getShape().setTranslateX(10);
            physicsCalculator.physicAssets.get(i).getShape().setLayoutY(70);
            System.out.println("X: " + collision.physicObject.get(i).getStartPositionX() + " Y: " + collision.physicObject.get(i).getStartPositionY() + " Echter Wert: " + collision.physicObject.get(i).getShape().getLayoutY());
        }
    }

    // Creating the mouse event handler for a Mouse click event
    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent e)
        {
            System.out.println("Mouse Click Detected!");
            mouseX = e.getX();
            mouseY = e.getY();
            initDragnDrop("click");
        }
    };
    // Creating the mouse event handler for moving the mouse
    EventHandler<MouseEvent> mouseMove = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent mouseEvent)
        {
            mouseX = mouseEvent.getX();
            mouseY = mouseEvent.getY();
            initDragnDrop("move");
        }
    };
    // Creating the mouse event handler for rotating mousewheel
    EventHandler<ScrollEvent> mouseWheel = new EventHandler<ScrollEvent>()
    {
        @Override
        public void handle(ScrollEvent scrollEvent)
        {

            deltaX = scrollEvent.getDeltaX();
            deltaY = scrollEvent.getDeltaY();
            initDragnDrop("scroll");
        }

    };


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

    // Inits the DragnDrop mechanics
    public void initDragnDrop(String event)
    {
        for (int i = 0; i < activeAssetList.size(); i++)
        {
            int a = i;
            switch (event)
            {
                case "click":
                    activeAssetList.get(i).getShape().setOnMouseClicked((t) ->
                    {
                        activeAssetList.get(a).getShape().toFront();
                        if (activeAssetList.get(a).isMouseDragDetected() == false)
                        {
                            activeAssetList.get(a).setMouseDragDetected(true);
                            activeAssetList.get(a).getShape().setStroke(Color.INDIANRED);
                        } else if (activeAssetList.get(a).isMouseDragDetected() == true)
                        {
                            activeAssetList.get(a).setMouseDragDetected(false);
                            activeAssetList.get(a).getShape().setStroke(Color.TRANSPARENT);
                        }
                        System.out.println("Shape Drag Detected!");

                    });
                    break;
                case "move":

                    System.out.println("Shape Move Detected!" + activeAssetList.get(a).isMouseDragDetected());
                    activeAssetList.get(a).getShape().toFront();
                    if (activeAssetList.get(a).isMouseDragDetected() == true)
                    {
                        activeAssetList.get(a).getShape().setLayoutX(mouseX);
                        activeAssetList.get(a).getShape().setLayoutY(mouseY);
                        System.out.println("X: " + " Y: " + mouseY);
                        //activeAssetList.get(a).getShape().getBoundsInLocal().
                        //activeAssetList.get(a).getShape().setStroke(Color.TRANSPARENT);
                    }
                    break;
                case "scroll":

                    if (activeAssetList.get(a).isMouseDragDetected() == true)
                    {
                        double scaleFactor = 35;
                        //Calculate Angle
                        double oldAngle = helper.getAngle(activeAssetList.get(a).getShape());
                        double rotate = deltaY;
                        if (deltaY < 0)
                        {
                            rotate += scaleFactor;
                        } else if (deltaY > 0)
                        {
                            rotate -= scaleFactor;
                        }
                        double newAngle = rotate + oldAngle;

                        //Set new Angle
                        helper.setAngle(activeAssetList.get(a).getShape(), newAngle);
                        helper.getAngle(activeAssetList.get(a).getShape());
                    }
                    break;

            }

            activeAssetList.get(i).getShape().setOnScroll(new EventHandler<ScrollEvent>()
            {
                @Override
                public void handle(ScrollEvent scrollEvent)
                {
                    if (dragDetected == true)
                    {
                        //rectangle.setRotate(rectangle.getRotate() + scrollEvent.getTextDeltaY());
                    }
                }
            });

        }
    }

    public ArrayList<AssetData> getTempList()
    {
        return tempList;
    }

    public void setTempList(ArrayList<AssetData> tempList)
    {
        this.tempList = tempList;
    }

}
