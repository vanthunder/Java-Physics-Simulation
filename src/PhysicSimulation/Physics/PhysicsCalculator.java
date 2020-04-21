package PhysicSimulation.Physics;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.shape.Circle;

import static java.lang.Math.*;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/*
 *   @author Erwin Kling
 *   @version 0.1.
 */


public class PhysicsCalculator {



    //All Physics Calculation-Methods should be instantiated here
   /* public PhysicsCalculator(Circle shape) {

        if (shape.getLayoutBounds().getMaxX() <= 800) {
            new Bewegung(shape, startpoint, endpoint, startSpeed, accelaration, mass, time);
            this.time +=getTime();
            mass = getMassfromRect(shape);
            shape.setCenterX(getStartpoint());
            System.out.println("abgelaufene Zeit: " + getTime());
        }
    }*/


    //regular values

    double dimensions = 0;
    double startSpeed = 5;
    double endSpeed = 0;
    int mass = 1;
    long time = 0;
    double path = 0;
    double aWork = 0;
    double accelaration = 0;


    //acceleration Values
    float accelerationDg = 0;

    //Thrust Values
    double lWork = 0;
    double lHeight = 0;

    //Gravitation Values
    double gConstant;
    double gAccelaration;
    double angle;
    double gForce;


    public static double getStartpoint() {
        return startpoint;
    }

    public void setStartpoint(double startpoint) {
        this.startpoint = startpoint;
    }

    long timeBefore =0;

    public static long getTime() {

        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;

        while (elapsedTime < 2*60*1000) {
            elapsedTime = (new Date()).getTime() - startTime;
        }
        return elapsedTime;
    }

    public void setTime(long time) {
        this.time = time;
    }

    static double startpoint = 1;
    double endpoint = 0;

    public double getSpeed() {
        return startSpeed;
    }

    public double getAccelaration() {
        return accelaration;
    }

    public void setAccelaration(double accelaration) {
        this.accelaration = accelaration;
    }

    public void setSpeed(double startSpeed) {
        this.startSpeed = startSpeed;
    }


    public static int getMassfromRect(Shape shape) {
        int mass = (int) (shape.getStrokeWidth() * pow(PI, 2));
        System.out.println("The Mass of the Rectangle: " + mass);
        return mass;
    }

    public static int getMassFromCircle(Circle circle) {
        int mass = (int) (PI * pow(circle.getRadius(), 2));
        System.out.println("The Mass of the Circle: " + mass);
        return mass;
    }


}
