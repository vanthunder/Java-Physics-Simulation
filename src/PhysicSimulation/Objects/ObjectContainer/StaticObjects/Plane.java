package PhysicSimulation.Objects.ObjectContainer.StaticObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Plane extends Rectangle
{
    public Plane()
    {
        super();
        this.setId("plane");
        this.setWidth(260);
        this.setHeight(10);
        this.setLayoutX(0);
        this.setLayoutY(300);
        this.setFill(Color.ORANGE);
    }
}
