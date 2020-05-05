package PhysicSimulation.Objects.ObjectContainer.StaticObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class SchiefeBahn extends Rectangle
{
    // This describes the angle of the rectangle
    public double angle = 30;
    Rotate rotate = new Rotate();
    public SchiefeBahn()
    {
        rotate.setAngle(angle);
        rotate.setPivotX(100);
        rotate.setPivotY(290);
        this.setId("schiefe Bahn");
        this.setLayoutX(-150);
        this.setLayoutY(150);
        this.setWidth(400);
        this.setHeight(4);
        this.getTransforms().add(rotate);
        this.setFill(Color.AQUA);
    }

    public double getAngle()
    {
        return angle;
    }
}
