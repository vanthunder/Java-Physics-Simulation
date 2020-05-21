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
        this.setLayoutX(0);
        this.setLayoutY(10);
        this.setFill(Color.BROWN);
        this.getStyleClass().add("wall");
    }
}

