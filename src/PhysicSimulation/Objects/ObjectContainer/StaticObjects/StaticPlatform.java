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
        this.setHeight(20);
        this.setWidth(200);
        this.setLayoutX(0);
        this.setLayoutY(300);
        this.setFill(Color.YELLOW);
        this.setStroke(Color.BEIGE);
        this.setId("Platform");
    }
}
