package PhysicSimulation.Physics;

import PhysicSimulation.Objects.Manager.AssetData;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;


/*
 *   @author Marvin Schubert
 *   @version 0.1.
 */
public class Collision
{
   public boolean detectCollision(Shape physicShape, Shape staticShape)
   {
       // Creates a new Shape based on the intersection between two shapes
       Shape intersect = Shape.intersect(physicShape, staticShape);
       // If the intersection width is not -1 than a collision is detected
       if(intersect.getBoundsInLocal().getWidth() != -1)
       {
           System.out.println("Collision detected!"+physicShape.getId()+" "+staticShape.getId());
           return true;
       }
       return false;
   }

   public void circleCollision(AssetData aCircle1, AssetData aCircle2)
   {
       // Just for testing purposes

       Shape circle1 = aCircle1.getShape();
       Shape circle2 = aCircle2.getShape();
       circle1.getBoundsInLocal().getCenterX();
       Circle circle = new Circle(1, 1, 1);
       circle.getRadius();
       System.out.println();

       // Normalvektor zwichen den Kugeln
       double nVX = circle2.getLayoutX()-circle1.getLayoutX();
       double nVY = circle2.getLayoutY()-circle1.getLayoutY();

       // Abstand der der beiden Kugeln
       double distance = Math.sqrt(nVX*nVX+nVY*nVY);

       //Abstand gleich kleiner als die Summer der Radien
       if(distance <= circle1.getBoundsInLocal().getCenterX()+circle2.getBoundsInLocal().getCenterX())
       {
           // Normaliesierung des Normalvektors
           nVX /= distance;
           nVY /= distance;

           // Tangentialvektor (senkrecht zu Normalvektor, zwischen beiden Kugeln)
           double tVX = nVY;
           double tVY = -nVX;

           // Summe der Massen beider Kugeln
           double sMasse = aCircle1.getMass()+aCircle2.getMass();

           // Überlappung der beiden Kugeln
           double overlap = (circle1.getBoundsInLocal().getCenterX()+circle2.getBoundsInLocal().getCenterX()) - distance;

           //Verschiebung der Kugeln entlang der Normalen
           overlap += 2;
           overlap *= 1.001;
           circle1.setTranslateX(circle1.getLayoutX()-nVX*overlap*(aCircle2.getMass()/sMasse));
           circle1.setTranslateY(circle1.getLayoutY()-nVY*overlap*(aCircle2.getMass()/sMasse));

           circle2.setTranslateX(circle2.getLayoutX()-nVX*overlap*(aCircle1.getMass()/sMasse));
           circle2.setTranslateY(circle2.getLayoutY()-nVY*overlap*(aCircle1.getMass()/sMasse));

           System.out.println("Circle Collision Detected!");

       }

   }
    public double distance (Shape shape1, Shape shape2)
    {
        double d;
        double calc1 = (shape2.getTranslateX()+shape1.getTranslateX());
        double calc2 = (shape2.getTranslateY()+shape1.getTranslateY());
        d = Math.sqrt(calc1*calc1+calc2*calc2);
        return d;
    }
}
