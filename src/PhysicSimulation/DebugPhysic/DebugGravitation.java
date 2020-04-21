package PhysicSimulation.DebugPhysic;

import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;

import java.time.Instant;
/**
 * @author Marvin Schubert
 * @version 0.1
 */
public class DebugGravitation
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


    // New calculation

    // Gravitation constant force g in m/s^2
    double g = 9.81;
    // The velocity
    double velocity = 10000;
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

    public void forceGravitation(Shape shape)
    {
        if(shape.getTranslateY()+shape.getBoundsInLocal().getMaxY()-0.5<=800)
        {
            // Inits the parameters like time
            if(position == 0)
            {
               // Resets shape position
               shape.setTranslateY(0);
               // Set a start position value
               position = shape.getTranslateY();
               // Prints out the start position value
               System.out.println("Die Startposition ist: "+position);
               // Inits parameters
               resetCalculation();
               // Inits Time
               lastTime = System.nanoTime()*1E-9;
            }
            // Calculate dt
            // Converts the current time in seconds
            time = System.nanoTime()*1E-9;
            // Calculate the delta time form the time and the last saved time
            dt = time - lastTime;
            // Saves the current time as last time
            lastTime = time;
            // Counts the total time (Only for debug)
            totalTime = totalTime + dt;

           //  calculate the new velocity
            velocity = velocity+g*dt;
           //  calculate the new position

            velocity += g*dt;
            position += 0.5*velocity*dt;
            //position = position + velocity *dt;

            //position += 0.5*g*dt;

            // Sets the new position
            shape.setTranslateY(position);
            // Gives parameters out into the console
            System.out.println("fallen " + position + "m velocity = " + velocity +  "m/s over " + totalTime);

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
    public double getAcceleration()
    {
        debugV0 = (int) velocity;
        //System.out.println(v0);
        return debugV0;
    }
    // Resets the parameter
    public void resetCalculation()
    {
        Y = 0;
        v = 0;
        t = 0;
        s = 0;
        v0 = 0;
        t0 = 0;
        s0 = 0;
        position = 0;
        velocity = 0;
        time = 0;
        dt = 100;
        totalTime = 0;

    }

}
