package PhysicSimulation.Physics;

import PhysicSimulation.Objects.Manager.AssetData;
import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;

import java.time.Instant;
/*
 *   @author Marvin Schubert, Erwin Kling
 *   @version 0.1.
 */
public class Gravitation
{
    private Bounds boundsOfShape;
    private double Y = 0;
    private double v = 0;
    private float t = 0;
    private double s = 0;
    private double v0 = 1;
    private double t0 = 0;
    private double s0 = 0;
    private double a = 9.81;
    private int debugV0;
    private boolean timeStart = false;
    private boolean isfalling = true;
    private Instant instantStart;
    private Instant instantEnd;
    private boolean start = true;
    public boolean collision = false;


    // New calculation

    // Gravitation constant force g in m/s^2
    double g = 9.81;
    // The velocity
   // double velocity = 0;
    // The new position of the object
    double position = 0;
    // The Time
    double time = 0;
    // The total count time
    double totalTime = 0;
    // The delta time
    double dt = 0;


    // Inits the last time for calculation
    double lastTime = System.nanoTime()*1E-9;
    double positionX = 0;

    float ab = 9.81f;
    float av = 0.0f;

    public void debugGravitation(Shape shape, AssetData asset, double deltaTime, double t)
    {
        if(asset.getVelocityX() == 0)
        {
            System.out.println(deltaTime);
            double velocity = 0;
            velocity = asset.getVelocityY();
            velocity += g*deltaTime;
            asset.setVelocityY(velocity);
            double position = 0;
            position = shape.getLayoutY();
            position += 0.5*velocity*deltaTime;
            //position +=0.5;
            asset.setMoved(true);
            //asset.setVelocityY(velocity);
            //asset.setCurrentPositionY(position);
            shape.setLayoutY(position);
            double fallen = position-120;
            System.out.println(fallen+" m"+" V: "+velocity+" m/s"+" position: "+position+" Time: "+t);
        }
        else
        if(asset.getVelocityX() != 0)
        {
            double pX = asset.getShape().getLayoutX();
            pX += 0.5*asset.getVelocityX()*deltaTime;
            //positionX += asset.getVelocityX()*deltaTime;
            double vY = asset.getVelocityY();
            vY += g*deltaTime;
            asset.setVelocityY(vY);
            double pY = asset.getShape().getLayoutY();
            pY += 0.5*vY*deltaTime;
            //position += 0.5*g*deltaTime;
            shape.setLayoutX(pX);
            shape.setLayoutY(pY);
            System.out.println("True");
        }


    }

    public void forceGravitation(Shape shape, AssetData asset, double deltaTime)
    {
        if(shape.getTranslateY()+shape.getBoundsInLocal().getMaxY()-0.5<=800)
        {
            // Inits the parameters like time
            if(start == true)
            {
               // Resets shape position
               //shape.setTranslateY(0);
               // Set a start position value
               //position = shape.getTranslateY();
               // Prints out the start position value
               System.out.println("Die Startposition ist: "+position);
               // Inits parameters
               //resetCalculation(asset);
               // Inits Time
               //lastTime = System.nanoTime()*1E-9;
               // Start Velocity
                //velocity = asset.getVelocity();
                start = false;
            }
            // Calculate dt
            // Converts the current time in seconds
            time = System.nanoTime()*1E-9;
            // Calculate the delta time form the time and the last saved time
            dt = time - lastTime;
            // Saves the current time as last time
            lastTime = time;
            // Counts the total time (Only for debug)
            totalTime = totalTime + deltaTime;
            // calculate the new velocity
            //velocity = velocity+g*dt;
            // calculate the new position

            //velocity += g*deltaTime;
            //asset.setVelocity(velocity);
            position = shape.getLayoutY();
            //position += 0.5*velocity*deltaTime;
            if(position >= 200)
            {
                //position = 200;
            }
            //position = position + velocity *dt;
            //position += 0.5*g*dt;

            // Sets the new position
            //shape.setLayoutY(position);
            //shape.setRotate(position);
            asset.setMoved(true);
            //asset.setVelocityY(velocity);
            asset.setCurrentPositionY(position);
            //Proofs if a velocity in X direction is not 0
            positionX = shape.getLayoutX();
            if(asset.getVelocityX() != 0)
            {
                positionX += asset.getVelocityX()*deltaTime;
                position += 0.5*g*deltaTime;
                shape.setLayoutX(positionX);
                shape.setLayoutY(position);
                System.out.println("True");
            }
            // Gives parameters out into the console
            System.out.println(deltaTime+"fallen " + position + "m velocity = " + null +  "m/s over " + totalTime);

            if(timeStart == false)
            {
                instantStart = Instant.now();
                timeStart = true;
            }

        }
        if(shape.getTranslateY()+shape.getBoundsInLocal().getMaxY()>=800 & timeStart == true)
        {
            instantEnd = Instant.now();
            timeStart = false;
        }
    }
    // Old Calculation
    public double calculateY (double position, long now)
    {
        double newPosition;
        s0 = position;
        System.out.println("Position: "+s+" Sekunden:"+t);
        t = now/100000000;
        s = v0*t;
        //s = s0 + v0 * t + 0.5 *a* (t*t);
        v0 = v0 + t;

        System.out.println("Position: "+s+" Sekunden:"+t);
        newPosition = s;
        return newPosition;
    }
    // Gives the current acceleration in m/s
    public double getVelocity()
    {
        //debugV0 = (int) velocity;
        //System.out.println(v0);
        return debugV0;
    }
    // Resets the parameter
    public void resetCalculation(AssetData assetData)
    {
        Y = 0;
        v = 0;
        t = 0;
        s = 0;
        v0 = 0;
        t0 = 0;
        s0 = 0;
        position = assetData.getShape().getLayoutY();
        //velocity = 0;
        time = 0;
        dt = 0;
        totalTime = 0;
        collision = false;
        assetData.setMoved(false);

    }

    public boolean isCollision()
    {
        return collision;
    }

    public void setCollision(boolean collision)
    {
        this.collision = collision;
    }

    public void setLastTime(double lastTime)
    {
        this.lastTime = lastTime;
    }


}
