package PhysicSimulation.Objects.ObjectContainer.StaticObjects.Borders;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ground extends Rectangle
{
    public Ground()
    {
        super();
        this.setId("ground");
        this.setWidth(800);
        this.setHeight(10);
        this.setLayoutX(0);
        this.setLayoutY(800);
        this.setFill(Color.GRAY);
    }
}
