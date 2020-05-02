package PhysicSimulation.Objects.ObjectContainer.StaticObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SchiefeBahn extends Rectangle
{
    // This describes the angle of the rectangle
    public double angle = 20;
    public SchiefeBahn()
    {
        this.setId("schiefe Bahn");
        this.setLayoutX(0);
        this.setLayoutY(220);
        this.setWidth(500);
        this.setHeight(10);
        this.setRotate(angle);
        this.setFill(Color.AQUA);
    }

    public double getAngle()
    {
        return angle;
    }
}
