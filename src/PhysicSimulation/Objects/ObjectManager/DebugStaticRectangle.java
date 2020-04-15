package PhysicSimulation.Objects.ObjectManager;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import PhysicSimulation.Objects.SimulationTypes.StaticObjects;

/**
 * @author Marvin Schubert
 * @version 0.1
 */
public class DebugStaticRectangle extends StaticObjects
{
    Shape shape;

    public DebugStaticRectangle()
    {
        super();
        //shape = this;
        shape = new Rectangle(100,400,100,100);
    }
}
