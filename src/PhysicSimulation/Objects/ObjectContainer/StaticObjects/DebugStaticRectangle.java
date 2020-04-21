package PhysicSimulation.Objects.ObjectContainer.StaticObjects;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;



/**
 * @author Marvin Schubert
 * @version 0.1
 */
public class DebugStaticRectangle extends Rectangle
{
    Label label = new Label();
    // Physics Dimensions
    // Mass in kg
    int mass = 2;
    // Velocity in m/s
    long velocity = 0;
    // Acceleration in m/s
    long acceleration;
    // Objects dimensions
    int X = 400;
    int Y = 0;
    int Height = 10;
    int Width = 10;
    Paint COLOR_FILL = Color.YELLOW;
    Paint COLOR_STROKE = Color.ORANGE;

    public DebugStaticRectangle(int mass, long velocity, long acceleration)
    {
        super();
        this.setVelocity(velocity);
        this.setAcceleration(acceleration);
        this.setMass(mass);
        this.setId("DebugStaticRectangle");
        this.setX(X);
        this.setY(Y);
        this.setHeight(Height);
        this.setWidth(Width);
        this.setFill(COLOR_FILL);
        this.setStroke(COLOR_STROKE);
    }

    public int getMass()
    {
        return mass;
    }

    public void setMass(int mass)
    {

        this.mass = mass;
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
