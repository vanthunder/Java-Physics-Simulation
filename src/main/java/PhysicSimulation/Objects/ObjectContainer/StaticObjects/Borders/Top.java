package PhysicSimulation.Objects.ObjectContainer.StaticObjects.Borders;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Top extends Rectangle
{
    public Top()
    {
        super();
        this.setId("wall");
        this.setWidth(820);
        this.setHeight(10);
        this.setLayoutX(-10);
        this.setLayoutY(0);
        this.setFill(Color.GRAY);
        this.getStyleClass().add("plane");
    }
}
