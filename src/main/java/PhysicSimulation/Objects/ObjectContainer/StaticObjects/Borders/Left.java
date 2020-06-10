package PhysicSimulation.Objects.ObjectContainer.StaticObjects.Borders;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Left extends Rectangle
{
    public Left()
    {
        super();
        this.setId("leftWall");
        this.setWidth(10);
        this.setHeight(790);
        this.setLayoutX(-10);
        this.setLayoutY(10);
        this.setFill(Color.GRAY);
        this.getStyleClass().add("plane");
    }
}

