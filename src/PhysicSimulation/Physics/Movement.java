package PhysicSimulation.Physics;

import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Objects.ObjectContainer.StaticObjects.SchiefeBahn;
import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;

import static java.lang.Math.cos;
import static java.lang.Math.pow;

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

    double dTime;
    double startTime;
    double route;

    double translation = 0;
    public void movement(Shape pObject, AssetData assetData) {
        double oldVelocity = assetData.getVelocity();

        double newVelocity;
        if(startpointX == 0){
            newVelocity = 0;
            startTime = System.nanoTime()*1E-9;
            startpointX = pObject.getBoundsInParent().getMinX();
        }
        dTime = (System.nanoTime() - startTime) * 1E-9;
        endpointX = pObject.getBoundsInParent().getMinX();
        route = endpointX-startpointX;
        newVelocity = (oldVelocity+accelerationX)*dTime;

        translation = endpointX + newVelocity * dTime + 0.5 * accelerationX * pow(dTime,2);


        if(assetData.getDirection()>=0){
            pObject.setTranslateX(translation);
        }
        else if(assetData.getDirection()<0){
            pObject.setTranslateX(-translation);

        }
    }

}

