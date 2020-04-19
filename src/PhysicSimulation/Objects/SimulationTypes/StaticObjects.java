package PhysicSimulation.Objects.SimulationTypes;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * @author Marvin Schubert
 * @version 0.1
 */
public class StaticObjects extends Shape
{
    Rectangle rectangle = new Rectangle(10, 10, 10, 10);
    public StaticObjects()
  {
      super();
      this.setType(rectangle);
  }



    public Shape setType(Shape shape)
  {
      Rectangle rectangle = new Rectangle(10, 10, 10, 10);
      return rectangle;
      //StaticObjects staticObjects = new StaticObjects(shape);
  }


}
