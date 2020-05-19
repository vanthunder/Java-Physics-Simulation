package PhysicSimulation.Objects.ObjectContainer.StaticObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class SchiefeBahn extends Rectangle
{
    // This describes the angle of the rectangle
    public double angle = 30;
    Rotate rotatea = new Rotate();
    public SchiefeBahn()
    {
        this.setRotate(40);
        //rotate.setPivotX(100);
        //rotate.setPivotY(290);
        this.setId("inclinedPlane");
        this.setLayoutX(-10);
        this.setLayoutY(360);
        this.setWidth(500);
        this.setHeight(8);
        //this.getTransforms().add(rotate);
        this.setFill(Color.AQUA);
    }

    public double getAngle()
    {
        return angle;
    }
}
