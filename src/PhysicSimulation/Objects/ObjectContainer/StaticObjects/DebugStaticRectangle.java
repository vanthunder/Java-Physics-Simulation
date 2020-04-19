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
    int velocity = 0;
    // Objects dimensions
    int X = 100;
    int Y = 100;
    int Height = 100;
    int Width = 100;
    Paint COLOR_FILL = Color.YELLOW;
    Paint COLOR_STROKE = Color.ORANGE;

    public DebugStaticRectangle(int mass)
    {
        super();
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

    public int getVelocity()
    {
        return velocity;
    }

    public void setVelocity(int velocity)
    {
        this.velocity = velocity;
    }

}
