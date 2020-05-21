package PhysicSimulation.Objects.ObjectContainer.StaticObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * @author Marvin Schubert
 * @version 0.1
 */
public class StaticPlatform extends Rectangle
{
    public StaticPlatform()
    {
        super();
        this.setHeight(10);
        this.setWidth(400);
        this.setLayoutX(200);
        this.setLayoutY(600);
        this.setFill(Color.YELLOW);
        this.setStroke(Color.BEIGE);
        this.setId("plane");
    }
}
