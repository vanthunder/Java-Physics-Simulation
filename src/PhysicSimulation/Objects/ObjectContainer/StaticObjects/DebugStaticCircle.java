package PhysicSimulation.Objects.ObjectContainer.StaticObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DebugStaticCircle extends Circle
{
    public DebugStaticCircle()
    {
        super();
        this.setCenterX(100);
        this.setCenterY(100);
        this.setRadius(40);
        this.setFill(Color.YELLOW);
    }
}
