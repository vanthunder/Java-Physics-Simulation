package PhysicSimulation.Physics;

import PhysicSimulation.Objects.Manager.AssetData;
import javafx.scene.shape.Shape;

public class Bouncing
{
    double p = 0.2;
    double pa = 4;
    double g = 9.81;
    int counter = 0;
    public CalcHelper cHelper = new CalcHelper();

    public void bounce(AssetData assetData, Shape shape, double deltaTime)
    {

        double m = assetData.getMass();
        // calculate the height h
        double h = cHelper.distanceToGround(shape);
        // Calculate Epot(Lageenergie)
        double Epot = m * g * h;

      /*
      x(t) = x0 + vx*t + 0.5*axt^2
      y(t) = y0 + vyt + 0.5*ayt^2
      vx(t) = vx0 + axt
      vy(t) = vy0 + ay*t
       */
        // Inits the initial values
        double lossFactor = 0.99;
        double x0 = shape.getLayoutX();
        double y0 = shape.getLayoutY();
        double vx0 = assetData.getVelocityX();
        double vy0 = assetData.getVelocityY();
        double ax = 1;
        double ay = g * Math.sqrt(2);
        //Apply Loss Factor
        //vx0 -= lossFactor;
        //vx0 -= lossFactor;


        //Update velocity
        double vx = vx0 + ax;
        assetData.setVelocityX(vx);
        double vy = vy0 + ay;
        assetData.setVelocityX(vy);


        double x = -(x0 + vx0 * deltaTime + 0.5 * ax * deltaTime * Math.sqrt(2));
        double y = -(y0 + 10 + vy0 * deltaTime);


        shape.setLayoutX(x);
        shape.setLayoutY(12);








/*
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

 */
        System.out.println(vx + "" + vx + " " + y);


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
