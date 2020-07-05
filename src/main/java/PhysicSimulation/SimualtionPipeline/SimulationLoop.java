package PhysicSimulation.SimualtionPipeline;

import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Objects.ShapeHelper;
import PhysicSimulation.Physics.*;
import com.sun.glass.events.WheelEvent;
import com.sun.javafx.css.StyleCache;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static PhysicSimulation.Controller.GUIController.vLog;

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
    public Collision collision = new Collision();
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
    public TextField debugTextField = new TextField();
    public Bouncing bouncing = new Bouncing();
    public double timer = 0;

    //public TextField label = new TextField();
    public Label dLabel = new Label();
    public double velocityChangevalue = 0;
    DecimalFormat df = new DecimalFormat("#.##");


    public SimulationLoop()
    {
        renderer.setFocusTraversable(true);

        renderer.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        renderer.addEventFilter(MouseEvent.MOUSE_MOVED, mouseMove);
        renderer.addEventFilter(ScrollEvent.ANY, mouseWheel);
        initDragnDrop("click");

        renderer.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) ->
        {
            System.out.println("Key Pressed!");
        });
        //renderer.getChildren().add(debugTextField);
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
                    velocityChangevalue = (Math.sqrt(Math.pow(collision.physicObject.get(0).getVelocityX(),2)+Math.pow(collision.physicObject.get(0).getVelocityY(),2)));
                    double velocityChangevalueX = collision.physicObject.get(0).getVelocityX();
                    double velocityChangeValueY = collision.physicObject.get(0).getVelocityY();
                    collision.colliding();
                    collision.checkShapeIntersection(collision.physicObject.get(i).getShape(), collision.physicObject.get(i));
                    dLabel.setText(df.format(velocityChangevalue)+ " x: "+df.format(velocityChangevalueX)+" y: "+df.format(velocityChangeValueY));
                    // Normal gravitation force
                    if (!collision.physicObject.get(i).getCollision())
                    {
                        //label.setText(String.valueOf(collision.physicObject.get(0).getVelocity()));
                            //dt = physicsCalculator.getDeltaTime();
                        gravitation.gravitationForce(collision.physicObject.get(i).getShape(), collision.physicObject.get(i), dt, t);
                        collision.physicObject.get(i).setWasFalling(true);
                        if (timer == 20)
                        {
                            collision.physicObject.get(i).setWasRolling(false);
                            timer = 0;
                        }
                        timer++;
                    } else
                    {
                        // Collision on inclined plane
                        if (collision.physicObject.get(i).getCollision() && collision.physicObject.get(i).isIncCollision())
                        {
                            //dt = physicsCalculator.getDeltaTime();
                            rotate.rollDownDebug(collision.physicObject.get(i), activeAssetList.get(4), dt);
                            System.out.println("Angle");
                            //collision.physicObject.get(i).setWasFalling(false);
                        }
                        // Collision on normal plane
                        else if (collision.physicObject.get(i).getCollision() && collision.physicObject.get(i).isPlaneCollision())
                        {
                            try{
                                //dt = physicsCalculator.getDeltaTime();
                            }catch(NullPointerException e){
                                System.out.println("Nullpointer Exception");
                            }
                            System.out.println("plane");
                            rotate.rotate(collision.physicObject.get(i), dt);
                            //collision.physicObject.get(i).setWasFalling(false);
                            //bouncing.bounceDebug(collision.physicObject.get(i), collision.physicObject.get(i).getShape(), dt);

                        }

                    }

                    // Bouncing effect
                    if (collision.physicObject.get(i).isBouncing() & collision.physicObject.get(i).isWasFalling())
                    {
                        try{
                            //dt = physicsCalculator.getDeltaTime();
                        } catch (NullPointerException e)
                        {
                            System.out.println("Nullpointer Exception");

                        }
                        //bouncing.bounce(collision.physicObject.get(i), collision.physicObject.get(i).getShape(), dt);
                        System.out.println("Bounce");
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
            helper.setAngle(collision.physicObject.get(i).getShape(), 0);
            collision.physicObject.get(i).setVelocity(0);
            collision.physicObject.get(i).setPositive(true);
            collision.physicObject.get(i).setPlaneCollision(false);
            collision.physicObject.get(i).setIncCollision(false);
            collision.physicObject.get(i).setCollision(false);
            //collision.physicObject.get(i).getShape().setTranslateY(10);
            //collision.physicObject.get(i).getShape().setTranslateX(10);
            collision.physicObject.get(i).getShape().setLayoutY(70);
            collision.physicObject.get(i).getShape().setLayoutX(140);
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
                        if (!activeAssetList.get(a).getShape().getId().equals("ground") & !activeAssetList.get(a).getShape().getId().equals("leftWall") & !activeAssetList.get(a).getShape().getId().equals("rightWall") & !activeAssetList.get(a).getShape().getId().equals("wall"))
                        {

                            activeAssetList.get(a).getShape().toFront();
                            if (activeAssetList.get(a).isMouseDragDetected() == false & activeAssetList.get(a).isDoubleClickDetected() == false)
                            {
                                activeAssetList.get(a).setMouseDragDetected(true);
                                activeAssetList.get(a).getShape().setStroke(Color.INDIANRED);
                                activeAssetList.get(a).getShape().setFocusTraversable(true);

                            } else if (activeAssetList.get(a).isMouseDragDetected() == true & activeAssetList.get(a).isDoubleClickDetected() == false)
                            {
                                activeAssetList.get(a).setMouseDragDetected(false);
                                activeAssetList.get(a).setDoubleClickDetected(true);
                                activeAssetList.get(a).getShape().setStroke(Color.YELLOW);
                            } else if (activeAssetList.get(a).isMouseDragDetected() == false & activeAssetList.get(a).isDoubleClickDetected() == true)
                            {
                                activeAssetList.get(a).setMouseDragDetected(false);
                                activeAssetList.get(a).setDoubleClickDetected(false);
                                activeAssetList.get(a).getShape().setStroke(Color.TRANSPARENT);
                            }

                        }
                        System.out.println(activeAssetList.get(a).getShape().getId());

                    });
                    break;
                case "move":

                    activeAssetList.get(a).getShape().toFront();
                    if (activeAssetList.get(a).isMouseDragDetected() == true || activeAssetList.get(a).isDoubleClickDetected() == true)
                    {
                        //helper.isInRadius(collision.physicObject.get(0).getShape(), collision.staticObject.get(0).getShape());
                        if (mouseX > 0 & mouseX < 800 & mouseY > 0 & mouseY < 800)
                        {
                            collision.colliding();
                            activeAssetList.get(a).getShape().setLayoutX(mouseX);
                            activeAssetList.get(a).getShape().setLayoutY(mouseY);
                            System.out.println("X: " + " Y: " + mouseY);
                        }
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

                        // Change the ID of the plane
                        if (newAngle == 0 || newAngle == 180 || newAngle == 360 || newAngle == -180 || newAngle == -360)
                        {
                            if(!activeAssetList.get(a).getShape().getId().equals("sphere"))
                            {
                                activeAssetList.get(a).getShape().setId("plane");
                            }
                        } else
                        {
                            if(!activeAssetList.get(a).getShape().getId().equals("sphere"))
                            {
                                activeAssetList.get(a).getShape().setId("inclinedPlane");
                            }
                        }


                        System.out.println(activeAssetList.get(a).getShape().getId());
                        //Set new Angle
                        helper.setAngle(activeAssetList.get(a).getShape(), newAngle);
                        helper.getAngle(activeAssetList.get(a).getShape());
                    }
                    if (activeAssetList.get(a).isDoubleClickDetected() == true)
                    {
                        double scaleFactor = 0.1;
                        //Calculate Angle
                        double oldScaleX = activeAssetList.get(a).getShape().getScaleX();
                        double oldScaleY = activeAssetList.get(a).getShape().getScaleY();
                        double scale = deltaY;
                        if (deltaY < 0)
                        {
                            scaleFactor = scaleFactor * -1;
                        }
                        else
                        if (deltaY > 0)
                        {
                            scaleFactor = scaleFactor * 1;
                        }
                        double newScaleX = scaleFactor + oldScaleX;

                        double newScaleY = scaleFactor + oldScaleY;

                        System.out.println(newScaleX);
                        Scale scaleA = new Scale();
                        scaleA.setX(newScaleX);
                        if(activeAssetList.get(a).getShape().getId().equals("sphere"))
                        {
                            //scaleA.setY(newScaleY);
                            scaleA.setY(newScaleY);
                            scaleA.setX(newScaleX);
                            System.out.println("Skalieren wird mit Kugel audgeführt!");
                        }
                        activeAssetList.get(a).getShape().getTransforms().add(scaleA);
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

    EventHandler<KeyEvent> key = new EventHandler<KeyEvent>()
    {

        public void handle(KeyEvent keyEvent)
        {
            if (keyEvent.getCode() == KeyCode.K)
            {
                System.out.println("KEY PRESSED");
                keyEvent.consume();
            }

        }
    };

    public double getDt()
    {
        return dt;
    }

    public void setDt(double dt)
    {
        this.dt = dt;
    }

    public void setLabel(Label label)
    {
        dLabel = label;
    }






}
