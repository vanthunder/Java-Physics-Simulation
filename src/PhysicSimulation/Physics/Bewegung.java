package PhysicSimulation.Physics;

import com.sun.javafx.geom.Rectangle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.concurrent.TimeUnit;

import static PhysicSimulation.Physics.PhysicsCalculator.getMassfromRect;
import static java.lang.Math.*;

/*
 *   @author Erwin Kling
 *   @version 0.1.
 */

public class Bewegung {

    private double constantSpeed;

    // first case: movement with constant acceleration
    public double constantSpeed(double startSpeed, double acceleration, double time) {
        double speed = startSpeed + acceleration * time;
        System.out.println("folgende Geschwindigkeit wurde in der Methode constantSpeed errechnet: " + speed);
        return speed;
    }

    //second case: movement with acceleration
    public double speed() {
        double speed = 0;
        System.out.println("folgende Geschwindigkeit wurde in der Methode speed errechnet: " + speed);
        return speed;
    }

    // the route to a position where physical conditions can be changed
    public double route(double startpoint, double startSpeed, double time, double endpoint) {
        double path = endpoint - startpoint;
        double route = startpoint + startSpeed * time + 0.5 * path * pow(time, 2);
        System.out.println("folgende Strecke wurde in der Methode speed errechnet: " + route);
        return route;
    }

    double speed;
    Bounds bound;
    double startpoint;
    double endpoint;
    double acceleration;
    double route;
    int mass;
    long time;
    long starttime;
    Rectangle rectangle;
    Circle circle;

    public Bewegung(Shape shape, double startpoint, double endpoint, double speed, double acceleration, int mass, long time) {
        // first case
        speed = constantSpeed(speed, acceleration, time);
        double route = route(startpoint, speed, time, endpoint);
        this.speed = speed;
        this.startpoint = shape.getBoundsInParent().getCenterX();
        this.endpoint = startpoint + speed * acceleration * pow(time, 2);
        this.acceleration = acceleration;
        this.route = route;
        this.mass = mass;
        this.time = time;
        this.time +=PhysicsCalculator.getTime();
        shape.setTranslateX(PhysicsCalculator.getStartpoint());
        System.out.println("Kreis Position :" + this.startpoint + " neue Position: " + this.endpoint+" Zeit: "+time);
        this.startpoint = this.endpoint;

    }


    // second case:

}
