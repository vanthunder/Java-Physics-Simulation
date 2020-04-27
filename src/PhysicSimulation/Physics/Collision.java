package PhysicSimulation.Physics;

import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;


/*
 *   @author Marvin Schubert
 *   @version 0.1.
 */
public class Collision
{
   public boolean detectCollision(Shape physicShape, Shape staticShape)
   {
       Bounds boundsPhysicObject = physicShape.getBoundsInParent();
       Bounds boundsStaticObject = staticShape.getBoundsInParent();
       if(boundsPhysicObject.intersects(boundsStaticObject))
       {
           System.out.println("Collision detected!"+physicShape.getId()+" "+staticShape.getId());
           return true;
       }
       return false;
   }
}
