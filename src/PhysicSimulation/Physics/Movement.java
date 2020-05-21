package PhysicSimulation.Physics;

import PhysicSimulation.Objects.Manager.AssetData;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

import static java.lang.Math.*;

/*
 *   @author Erwin Kling
 *   @version 0.1.
 */

public class Movement {


    double startpointX;
    double endpointX;

    double accelerationX;
    double accelerationY;

    double positionX;
    double positionY;

    double oldVelocity;

    double mass;

    double dTime;
    double startTime;
    double route;
    double scope;
    double radius;
    double translation = 0;
    Rotate rotation = new Rotate();
    Circle pivot = new Circle();
    double angle;
    double rc;
    double rfc = 1.2;
    double weight;

    double kinE;
    double potE;

    public void movement(Shape pObject, AssetData assetData, double dt) {

        double newVelocity =0;
        if(translation == 0){

            newVelocity = assetData.getVelocityX();
            startTime = System.nanoTime()*1E-9;
            startpointX = pObject.getLayoutX();
            translation = assetData.getShape().getLayoutX();
            accelerationX = assetData.getAcceleration();
            radius = pObject.getStrokeWidth()/2;
            scope = 2*PI*radius;
            mass = 670*4/3*PI*pow(radius,3);
            assetData.setMass((int)mass);
            weight = pObject.getStrokeWidth();


        }
        //calculating rolling friction
        rc = dt/radius*9.81;

        newVelocity +=assetData.getVelocityX()+accelerationX*dt;

        //rotates the Circle
        angle += (newVelocity/360)/(newVelocity/scope);
        rotation.setAngle(angle);
        rotation.setPivotX(pObject.getLayoutBounds().getCenterX());
        rotation.setPivotY(pObject.getLayoutBounds().getCenterY());
        pObject.getTransforms().addAll(rotation);

        dTime = (System.nanoTime() - startTime) * 1E-9;
        endpointX = pObject.getLayoutX();
        route = endpointX-startpointX;
        System.out.println(" Position: "+pObject.getLayoutX()+" Angle:"+angle+" Rolling Friction: "+rc+" mass: "+mass+" radius: "+radius);
        translation += newVelocity;

        if(assetData.getDirection()<0){
            translation = -translation;
        }
        assetData.getShape().setLayoutX(translation);
        assetData.setVelocityX(newVelocity);

    }

    public void resetPhysics(){
        startpointX =0;
        endpointX= 0;
        accelerationX=0;
        accelerationY=0;
        positionX=0;
        positionY=0;
        oldVelocity=0;
        mass=0;
        dTime=0;
        startTime=0;
        route=0;
        scope=0;
        radius=0;
        translation = 0;
        angle=0;
        weight=0;
    }

}
