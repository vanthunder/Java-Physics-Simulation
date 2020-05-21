package PhysicSimulation.Objects.ObjectContainer.PhysicsObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


/*
 *   @author Erwin Kling
 *   @version 0.1.
 */
public class Sphere extends Circle
{
    // Physics Dimensions
    // Mass in kg
    int mass = 10;
    // Velocity in m/s
    long velocity = 0;
    // Acceleration in m/s
    long acceleration;
    // Objects dimensions
    double X = 70;
    double Y = 70;
    int radius = 12;
    Paint COLOR_FILL = Color.BEIGE;
    Paint COLOR_STROKE = Color.ORANGE;
    public Image image = new Image("Images/kugel_mark.png");

    public Sphere() {
        super();
        this.setId("circle");
        this.setRadius(radius);
        //this.setHeight(20);
        //this.setWidth(20);
        //this.setRotate(30);
        this.setVelocity(velocity);
        this.setAcceleration(acceleration);
        this.setId("DebugPhysicsCircle");
        this.setFill(new ImagePattern(image));
        //this.setStroke(COLOR_STROKE);
        //this.setCenterX(X);
        //this.setCenterY(Y);
        this.setLayoutX(X);
        this.setLayoutY(Y);
        this.setRotate(0);
    }


    public void setVelocity(long velocity)
    {
        this.velocity = velocity;
    }

    public long getVelocity()
    {
        return velocity;
    }

    public long getAcceleration()
    {
        return acceleration;
    }

    public void setAcceleration(long acceleration)
    {
        this.acceleration = acceleration;
    }
 /*
    public double getX()
    {
        return X;
    }

    public void setX(double x)
    {
        X = x;
    }

    public double getY()
    {
        return Y;
    }

    public void setY(double y)
    {
        Y = y;
    }

  */

}


