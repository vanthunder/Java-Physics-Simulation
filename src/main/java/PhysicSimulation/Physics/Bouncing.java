package PhysicSimulation.Physics;

import PhysicSimulation.Objects.Manager.AssetData;
import javafx.scene.shape.Shape;

public class Bouncing
{
    double p = 0.2;
    double pa = 4;
    int counter = 0;

    public void bounce(AssetData assetData, Shape shape, double deltaTime)
    {
        assetData.setBouncing(true);
        double parameter = 0.5;

        // Calculation for the velocity of the object
        double v0X = assetData.getVelocityX();
        double v0Y = assetData.getVelocityY();
        double angle = assetData.getAngle();
        double velocityX = v0X * Math.cos(angle);
        double velocityY = v0Y * Math.sin(angle);
        assetData.setVelocityX(velocityX);
        assetData.setVelocityY(velocityY);
        // Init parameters for the location of the object
        double x = shape.getLayoutX();
        double y = shape.getLayoutY();
        // Calculate position
        x = x + deltaTime * velocityX;
        y = y * deltaTime * velocityY;
        //velocityY = velocityY - deltaTime *9.81;
        assetData.setVelocityY(velocityY);
        //x = -parameter*x;
        //y = -parameter*y;

        shape.setLayoutX(x);
        shape.setLayoutY(y);
        System.out.println(velocityX + "" + velocityY + " " + y);


    }

    public void bounceDebug(AssetData assetData, Shape shape, double deltaTime)
    {
        double y = assetData.getShape().getLayoutY();
        double x = assetData.getShape().getLayoutX();
        double vx = 0.5;
        if (counter < 12)
        {
            pa -= p;
            y -= pa;
            x += 0.5 * vx;
        }
        assetData.setCollision(false);
        shape.setLayoutX(x);
        shape.setLayoutY(y);
        assetData.setBouncing(true);
        if (assetData.isPlaneCollision())
        {
            if (counter < 12)
            {
                pa = 4;
            } else if (counter > 11)
            {
                assetData.setBouncing(false);
                assetData.setWasFalling(false);
                counter = 0;
                pa = 4;
            }


            System.out.println(pa + " True" + " Counter: " + counter);
            counter++;


        }

    }
}
