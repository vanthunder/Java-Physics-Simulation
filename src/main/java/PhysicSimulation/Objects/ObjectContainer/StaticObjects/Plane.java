package PhysicSimulation.Objects.ObjectContainer.StaticObjects;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Plane extends Rectangle
{
    double x, y;
    boolean dragDetected = false;
    Rectangle rectangle = new Rectangle();

    public Plane()
    {
        super();
        this.setId("plane");
        this.setWidth(260);
        this.setHeight(10);
        this.setLayoutX(0);
        this.setLayoutY(300);
        this.getStyleClass().add("plane");
        this.setCursor(Cursor.HAND);
    }

}
