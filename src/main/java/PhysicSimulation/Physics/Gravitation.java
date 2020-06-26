package PhysicSimulation.Physics;

import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Objects.ShapeHelper;
import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;

import java.time.Instant;
/*
 *   @author Marvin Schubert, Erwin Kling
 *   @version 0.4
 */
public class Gravitation
{
    private double Y = 0;
    private double v = 0;
    private float t = 0;
    private double s = 0;
    private double v0 = 1;
    private double t0 = 0;
    private double s0 = 0;
    private double a = 9.81;
    // Gravitation constant force g in m/s^2
    double g = 9.81;

    public void gravitationForce(Shape shape, AssetData asset, double deltaTime, double t)
    {
        // If the velocity in x direction is 0, the normal gravitation is applied to object
        if (asset.getVelocityX() == 0)
        {
            double velocity = 0;
            velocity = asset.getVelocityY();
            velocity += g * deltaTime;
            asset.setVelocityY(velocity);
            double position = 0;
            position = shape.getLayoutY();
            position += 0.5*velocity*deltaTime;
            asset.setMoved(true);
            shape.setLayoutY(position);
            double fallen = position - 70;
            System.out.println(fallen+" m"+" V: "+velocity+" m/s"+" position: "+position+" Time: "+t);
            asset.setFalling(true);
        }
        else
        if(asset.getVelocityX() != 0)
        {
            double counter = asset.getCounter();
            // Resets some values for falling transition if the counter is 25
            if(counter == 25)
            {
                asset.setAngleInclineVelocity(0);
                asset.getShape().setRotate(0);
                counter = 0;
            }
                double pX = asset.getShape().getLayoutX();
                double velocity = asset.getVelocityX();
                if (asset.getAngleVelocity() >= 0)
                {
                    pX += 0.5 * velocity * deltaTime;
                }

                    if(asset.getAngleVelocity() <= 0)
                    {
                        pX -= 0.5 * velocity * deltaTime;
                    }

                double vY = asset.getVelocityY();
                vY += g*deltaTime;
                asset.setVelocityY(vY);
                double pY = asset.getShape().getLayoutY();
                pY += 0.5*vY*deltaTime;
                shape.setLayoutX(pX);
            shape.setLayoutY(pY);
            System.out.println("True" + asset.getVelocityX() + " px " + pX + "_" + asset.getAngleVelocity());
            counter++;
                asset.setCounter(counter);
                asset.setFalling(true);
        }
    }
}
