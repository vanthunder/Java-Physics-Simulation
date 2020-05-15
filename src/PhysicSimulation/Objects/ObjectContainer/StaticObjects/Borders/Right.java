package PhysicSimulation.Objects.ObjectContainer.StaticObjects.Borders;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Right extends Rectangle
{
    public Right()
    {
        super();
        this.setId("wall");
        this.setWidth(10);
        this.setHeight(790);
        this.setLayoutX(790);
        this.setLayoutY(10);
        this.setFill(Color.BROWN);
    }
}
