package PhysicSimulation.Physics;

import PhysicSimulation.Objects.ObjectContainer.StaticObjects.SchiefeBahn;
import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;

import static java.lang.Math.cos;
import static java.lang.Math.pow;

/*
 *   @author Erwin Kling
 *   @version 0.1.
 */

public class Bewegung {

    private double constantSpeed;


    // first case: movement with constant acceleration
    public double constantSpeed(double path, double time) {
        double speed = path/time;
        return speed;
    }

    //second case: movement with  constant acceleration
    public double acceleration(double velocity, double time) {
        double acceleration = velocity/time;
        return acceleration;
    }

    // the route to a position where physical conditions can be changed
    public double route(double startpoint, double startSpeed, double time, double endpoint) {
        double path = endpoint - startpoint;
        double route = startpoint + startSpeed * time + 0.5 * path * pow(time, 2);
        System.out.println("folgende Strecke wurde in der Methode speed errechnet: " + route);
        return route;
    }

    double velocityX;
    double velocityY;

    Bounds bound;
    double startpointX;
    double startpointY;
    double endpointX;
    double endpointY;

    double accelerationX;
    double accelerationY;

    double route;
    double mass;
    double startTime;

    double positionX;
    double positionY;

    double timeElapsed = 0;
    double time = 0;
    double translation;
    double angle = 0;

    public void movement(Shape pObject, Shape sObject, double velocity, double angle) {

        if(positionX == 0){
            this.startTime = System.nanoTime()*1E-9;
            startpointX = pObject.getBoundsInParent().getCenterX();
            startpointY = pObject.getBoundsInLocal().getCenterY();
            endpointX = pObject.getBoundsInParent().getCenterX();
            endpointY = pObject.getBoundsInLocal().getCenterY();
            SchiefeBahn collisionObject = new SchiefeBahn();
            angle = collisionObject.getAngle();
            velocityX = velocity;
            velocityY = velocity;
            accelerationY = 0;
            accelerationX = 0;
        }

        positionX = pObject.getBoundsInLocal().getCenterX();
        positionY = pObject.getBoundsInLocal().getCenterY();
        System.out.println("Position X: "+positionX+" Position: "+positionY);

        double distanceX = positionX - startpointX;
        double distanceY = (positionY - startpointY)*cos(angle);

        timeElapsed = (double) System.nanoTime()*1E-9;
        time = timeElapsed - startTime;

        System.out.println(" Angle: "+angle);

        // calculate speed for movement
        System.out.println("Strecke: "+distanceX+" , "+distanceY);


        endpointX =positionX + velocityX + accelerationX;
        endpointY = positionY + velocityY + accelerationY+cos(angle);

        pObject.setTranslateX(endpointX);

        if (angle > 0) {
            pObject.setTranslateY(endpointY);
        }

        System.out.println("Position :" + this.startpointX+" , "+this.startpointY + " neue Position: " + this.endpointX+" , "+endpointY+" Zeit: "+time+" Geschwindigkeit: "+velocityX+" , "+velocityY);

    }
    public void moveAfterFall(Shape shape, double velocity){


        }

    }


