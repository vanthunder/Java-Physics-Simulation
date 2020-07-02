package PhysicSimulation.Physics;

import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Objects.ObjectContainer.PhysicsObjects.SphereBall;
import PhysicSimulation.Objects.ShapeHelper;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;


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

   public ArrayList <AssetData> physicObject = new ArrayList<AssetData>();
   public ArrayList <AssetData> staticObject = new ArrayList<AssetData>();
   public ArrayList <Shape> shapes = new ArrayList<Shape>();
   // Inits the shape list
   public void collisions()
   {
       for(int a = 0; a < physicObject.size(); a++)
       {
           shapes.add(physicObject.get(a).getShape());
           for (int b = 0; b < staticObject.size(); b++)
           {
               shapes.add(staticObject.get(b).getShape());
           }
       }
   }

    // checks rectangle with circle collision.
   public void checkShapeIntersection(Shape block, AssetData asset)
   {
       boolean collisionDetected = false;
       String ID = "";
       for(Shape static_bloc : shapes)
       {
           if(static_bloc != block)
           {
               ShapeHelper helper = new ShapeHelper();
               if(helper.isInRadius(block,static_bloc))
               {
                   //static_bloc.setFill(Color.GREEN);
                   Shape intersect = Shape.intersect(block, static_bloc);
                   if(intersect.getBoundsInLocal().getWidth() != -1)
                   {
                       ID = static_bloc.getId();
                       System.out.println(static_bloc);
                       asset.setCurrentCollisionObject(static_bloc);
                       collisionDetected = true;
                   }
               }

           }
       }
       if(collisionDetected)
       {
           // Case String ID
           switch(ID)
           {
               case "plane":
                   if (asset.isWasFalling() == true & !asset.isWasRolling())
                   {
                       asset.setBouncing(true);
                   }
                   asset.setCollision(true);
                   asset.setPlaneCollision(true);
                   asset.setIncCollision(false);
                   break;
               case "inclinedPlane":
                   asset.setCollision(true);
                   asset.setPlaneCollision(false);
                   asset.setIncCollision(true);
                   if (asset.isFalling() == true)
                   {
                       asset.setFalling(false);
                   }
                   double p = block.getLayoutY();
                   p -= 0.1;
                   block.setLayoutY(p);
                   break;
               case "wall":
                   asset.setCollision(true);
                   asset.setPlaneCollision(false);
                   asset.setIncCollision(false);
                   break;
               case "rightWall":
                   double x = asset.getShape().getLayoutX();
                   x -= 0.1;
                   asset.setVelocityX(-1 * asset.getVelocityX());
                   asset.getShape().setLayoutX(x);
                   //block.setStroke(Color.ORANGE);
                   asset.setPlaneCollision(false);
                   asset.setCollision(true);
                   asset.setIncCollision(false);
                   asset.setPositive(false);
                   if (asset.isWasFalling() == true)
                   {
                       asset.setBouncing(true);
                   }
                   break;
               case "leftWall":
                   double xn = asset.getShape().getLayoutX();
                   xn += 0.1;
                   asset.getShape().setLayoutX(xn);
                   asset.setCollision(true);
                   asset.setIncCollision(false);
                   asset.setPositive(true);
                   if (asset.isWasFalling() == true)
                   {
                       asset.setBouncing(true);
                   }
                   break;
               case "ground":
                   asset.setCollision(true);
                   asset.setIncCollision(false);
                   asset.setPlaneCollision(true);
                   if (asset.isWasFalling() == true)
                   {
                       asset.setBouncing(true);
                   }
                   break;
           }

       }
       else
       {
           asset.setCollision(false);
           asset.setPlaneCollision(false);
           asset.setIncCollision(false);
           asset.setCurrentCollisionObject(null);
       }

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

    // Just for testing purposes
   public void circleCollision(AssetData aCircle1, AssetData aCircle2)
   {


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

    // Debug Collision Handling
    public void debugCollision()
    {
        // Broad phase
        // Basic bounding box detection for better collision handling


        // Narrow phase
        // Detailed Collision detection
    }

    public boolean colliding()
    {
        for (int i = 0; i < physicObject.size(); i++)
        {
            Rectangle r1 = physicObject.get(i).getSphereBall().getBoundingRect();

            for (int k = 0; k < staticObject.size(); k++)
            {


                Shape r2 = staticObject.get(k).getShape();

                Shape intersect = Shape.intersect(r1, r2);
                if (intersect.getBoundsInLocal().getWidth() != -1)
                {
                    System.out.println("The ball intersects with another Ball!");

                    return true;
                }

            }
        }
        return false;
        /*

        //circle1 = (Circle) shape1;
        //circle2 = (Circle) shape2;
        //double xd = circle1.getCenterX() - circle2.getCenterX();
        //double yd = circle1.getCenterY() - circle2.getCenterY();

        double xd = shape1.getBoundsInLocal().getCenterX() - shape2.getBoundsInLocal().getCenterX();
        double yd = shape1.getBoundsInLocal().getCenterY() - shape2.getBoundsInLocal().getCenterY();

        double r1 = shape1.getScaleX()/2;
        double r2 = shape2.getScaleX()/2;
        //double sumRadius = circle1.getRadius() + circle2.getRadius();
        double sumRadius = r1+r2;
        double sqrRadius = sumRadius * sumRadius;

        double distSqr = (xd * xd) + (yd * yd);

        if (distSqr <= sqrRadius)
        {
            System.out.println("Balls Collides"+shape1.getId()+" "+shape2.getId());
            return true;

        }

        return false;

         */
    }

    public void resolveCollision(AssetData data)
    {
        // Factor of Loss
        double loss = 0.2;
        // Masse m
        double m = data.getMass();
        // velocity
        double v0 = data.getVelocityY();
        // Converted Velocity v1
        v0 = (v0 * m) / m;
        v0 += loss;
        data.setVelocityY(v0);
        double y = data.getShape().getLayoutY();

        y -= 0.5 * 0.03 * v0;

        data.getShape().setLayoutY(y);




        /*
        // get the mtd
        Vector2d delta = (position.subtract(ball.position));
        float d = delta.getLength();
        // minimum translation distance to push balls apart after intersecting
        Vector2d mtd = delta.multiply(((getRadius() + ball.getRadius())-d)/d);


        // resolve intersection --
        // inverse mass quantities
        float im1 = 1 / getMass();
        float im2 = 1 / ball.getMass();

        // push-pull them apart based off their mass
        position = position.add(mtd.multiply(im1 / (im1 + im2)));
        ball.position = ball.position.subtract(mtd.multiply(im2 / (im1 + im2)));

        // impact speed
        Vector2d v = (this.velocity.subtract(ball.velocity));
        float vn = v.dot(mtd.normalize());

        // sphere intersecting but moving away from each other already
        if (vn > 0.0f) return;

        // collision impulse
        float i = (-(1.0f + Constants.restitution) * vn) / (im1 + im2);
        Vector2d impulse = mtd.normalize().multiply(i);

        // change in momentum
        this.velocity = this.velocity.add(impulse.multiply(im1));
        ball.velocity = ball.velocity.subtract(impulse.multiply(im2));

         */
    }

}
