package PhysicSimulation.Objects.ObjectContainer.PhysicsObjects;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


/*
 *   @author Erwin Kling
 *   @version 0.1.
 */
public class DebugPhysicsCircle extends Circle {
    // Physics Dimensions
    // Mass in kg
    int mass = 2;
    // Velocity in m/s
    long velocity = 0;
    // Acceleration in m/s
    long acceleration;
    // Objects dimensions
    int X = 40;
    int Y = 128;
    int radius = 10;
    Paint COLOR_FILL = Color.BEIGE;
    Paint COLOR_STROKE = Color.ORANGE;

    public DebugPhysicsCircle() {
        super();
        this.setRadius(radius);
        this.setVelocity(velocity);
        this.setAcceleration(acceleration);
        this.setId("DebugPhysicsCircle");
        this.setFill(COLOR_FILL);
        this.setStroke(COLOR_STROKE);
        this.setCenterX(X);
        this.setCenterY(Y);
    }


    public void setVelocity(long velocity)
    {
        this.velocity = velocity;
    }

    public long getVelocity()
    {
        return velocity;
    }

    public long getAcceleration()
    {
        return acceleration;
    }

    public void setAcceleration(long acceleration)
    {
        this.acceleration = acceleration;
    }

}


