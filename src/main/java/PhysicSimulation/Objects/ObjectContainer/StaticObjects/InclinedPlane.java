package PhysicSimulation.Objects.ObjectContainer.StaticObjects;

import javafx.scene.Cursor;

public class InclinedPlane extends Plane
{
    public InclinedPlane()
    {
        super();
        this.setId("inclinedPlane");
        this.setWidth(200);
        this.setHeight(10);
        this.setLayoutX(0);
        this.setLayoutY(260);
        this.getStyleClass().add("plane");
        this.setCursor(Cursor.HAND);
        this.setFocusTraversable(true);
    }
}
