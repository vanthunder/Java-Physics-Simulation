package PhysicSimulation.Objects.ObjectContainer.StaticObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Plane extends Rectangle
{
    public Plane()
    {
        super();
        this.setId("Plane");
        this.setWidth(700);
        this.setHeight(4);
        this.setLayoutX(0);
        this.setLayoutY(400);
        this.setFill(Color.ORANGE);
    }
}
