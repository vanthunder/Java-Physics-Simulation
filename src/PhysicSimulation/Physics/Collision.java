package PhysicSimulation.Physics;

import PhysicSimulation.Objects.Manager.AssetData;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


/*
 *   @author Marvin Schubert
 *   @version 0.2
 */

public class Collision
{
   public Circle circle1 = new Circle();
   public Circle circle2 = new Circle();

   public Rectangle rectangle1 = new Rectangle();
   public Rectangle rectangle2 = new Rectangle();

   public Shape colShape1;
   public Shape colShape2;


   double restPosition = 0;
   // The difference between the two shapes
   double posDifX = 0;
   double posDifY = 0;
   // The nearest point between the two objects
   double nearPointX = 0;
   double nearPointY = 0;
   // Boolean to proof collision
   boolean isCollideX = false;
   boolean isCollideY = false;
   boolean isIncPlane = false;
   public boolean detectCollision(Shape physicShape, Shape staticShape)
   {
       // Converts the shapes into a circle or a rectangle
       convertShape(physicShape, staticShape);
       // Test circle with rectangle collision





       // Creates a new Shape based on the intersection between two shapes
       Shape intersect = Shape.intersect(physicShape, staticShape);
       // If the intersection width is not -1 than a collision is detected
       if(intersect.getBoundsInLocal().getWidth() != -1)
       {
           System.out.println("Collision detected!"+physicShape.getId()+" "+staticShape.getId());
           if(isIncPlane == true)
           {
               restPosition = physicShape.getLayoutY()-0.5;
               physicShape.setLayoutY(restPosition);
               setIncPlane(false);
           }
           System.out.println("OldPOs: "+ physicShape.getLayoutY()+" NewPos: "+restPosition);
           return true;
       }
       return false;
   }


   // Converts the two shapes into a specific shape form
   public void convertShape(Shape physicsShape, Shape staticShape)
   {
       if(physicsShape.getId().equals("circle"))
       {
           circle1 = (Circle) physicsShape;
       }
       else
           if (physicsShape.getId().equals("rectangle"))
           {
               rectangle2 = (Rectangle) physicsShape;
           }
       if(staticShape.getId().equals("rectangle"))
       {
           rectangle1 = (Rectangle) staticShape;
       }
       else
           if(staticShape.getId().equals("circle"))
           {
               circle2 = (Circle) staticShape;
           }
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

    public boolean isIncPlane()
    {
        return isIncPlane;
    }

    public void setIncPlane(boolean incPlane)
    {
        isIncPlane = incPlane;
    }

}
