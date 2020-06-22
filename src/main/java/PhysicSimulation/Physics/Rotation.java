package PhysicSimulation.Physics;

import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Objects.ShapeHelper;

/*
 *   @author Erwin Kling, Marvin Schubert
 *   @version 0.6
 */
public class Rotation
{
    ShapeHelper helper = new ShapeHelper();
    boolean start = true;
    double counter = 1;
    double c = 0;
    double newAngleA = 0;
    public void rotate (AssetData asset, double dt)
    {
        // the radius
        double r = 12;
        double Fg = 10*9.81;
        double Fr = 0.006*Fg;
        double wn = asset.getAngleVelocity();
        if (start == true)
        {
            wn += Math.sqrt(10 * r * Fr) * dt;
        }
        start = false;
        //double bremsG = 0.05*9.81;
        double bremsG = Fr;
        if(wn > 0)
        {
            wn -= bremsG;
        }
        else
        if(wn < 0)
        {
            wn = 0;
        }
        asset.setAngleVelocity(wn);
        // For gravitation purposes
        double velocityX = asset.getVelocityX();
        velocityX = (wn * r) / 100;
        asset.setVelocityX(velocityX);

        double rotation = asset.getShape().getRotate();
        double newRotation = (wn*dt)/2;
        double rotate = rotation;
        rotation = newRotation;
        double px = asset.getShape().getLayoutX();

        if(asset.isPositive())
        {
            px += (rotation * r) / 100;
            rotate += newRotation;
        } else if (!asset.isPositive())
        {
            px -= (rotation * r) / 100;
            rotate -= newRotation;
        }
        if (wn == 0)
        {
            px = asset.getShape().getLayoutX();
        } else
        {
            asset.getShape().setLayoutX(px);
            asset.getShape().setRotate(rotate);
        }
        System.out.println("Rotation"+rotation+"_"+" Y:"+asset.getShape().getLayoutX()+"WN: "+wn+"   brems:"+bremsG);
    }

    // Testing purposes
    public void rollDown(AssetData asset, AssetData inclinedPlane, double dt)
    {
        double friction = 0.006;
        double angle = 40;
        double rotation = asset.getShape().getRotate();
        double r = 0.12;
        double angleVelocity = asset.getAngleVelocity();
        double g = 9.81;
        double an = g*Math.cos(angle);
        double ar = friction*g*Math.cos(angle);
        double ah = g*Math.sin(Math.toRadians(angle));

        // calculate const. acceleration
        double a = ah+ar;
        // calculate angle acceleration
        double angleA = a/r;
        // calculate angle acceleration
        angleVelocity = angleA*dt+angleVelocity;
        //angleVelocity += 0.017790004955025276;
        asset.setAngleVelocity(angleVelocity);

        rotation += (angleVelocity*dt)/2;
        asset.getShape().setRotate(rotation);
        double px = asset.getShape().getLayoutX();
        double py = asset.getShape().getLayoutY();
        if(rotation >= 360*2)
        {
            int i = (int) (rotation/360);
            newAngleA += rotation-(360*i);

            px += (newAngleA*r)/100;
            py += (newAngleA*r*Math.sin(Math.toRadians(angle)))/100;
            asset.getShape().setLayoutX(px);
            asset.getShape().setLayoutY(py);
        } else if(rotation <= 360*2)
        {


            px += (rotation*r)/100;

            py += (rotation*r*Math.sin(Math.toRadians(angle)))/100;

            asset.getShape().setLayoutX(px);
            asset.getShape().setLayoutY(py);

        }

        double test = angleA;

        System.out.println(a+"test"+" ah: "+ah+"ar: "+ar+"_"+rotation+" av:"+angleVelocity+" angleA: "+angleA+"dt"+dt);
        counter++;


    }

    public void rollDownDebug(AssetData asset, AssetData inclinedPlane, double dt)
    {
        double friction = 0.006;
        double angle = helper.getAngle(asset.getCurrentCollisionObject());
        double rotation = asset.getShape().getRotate();
        double r = 0.012;
        double angleVelocity = asset.getAngleVelocity();
        double g = 9.81;

        // Winkelbeschleunigung
        double angleAccerleration = ((0.4*(g)*Math.sin(angle))/r);
        //double angleAccerleration = 21.019154836749835970750439503968;
        // Tangentialbeschleunigung
        double at = (2/5)*g*Math.sin(angle);
        // Winkelgeschwindigkeit
        double angleVelocity1 = asset.getAngleInclineVelocity();
        angleVelocity1 += angleAccerleration*dt;
        asset.setAngleVelocity(angleVelocity1);
        asset.setAngleInclineVelocity(angleVelocity1);
        // Drehwinkel
        double angleRotation = asset.getShape().getRotate();
        angleRotation += (angleVelocity * dt) / 2;
        // Hier später if Bedingung für umkehr einfügen
        if(asset.isPositive())
        {
            //angleRotation += (angleVelocity*dt)/2;
        }
        else
        if(!asset.isPositive())
        {
            //angleRotation -= (angleVelocity*dt)/2;
        }
        asset.getShape().setRotate(angleRotation);
        double x = asset.getShape().getLayoutX();
        double y = asset.getShape().getLayoutY();
        // Berechne Position X - s oder Weg
            double velocityY = asset.getVelocityY();
            velocityY = angleVelocity * r;
            if(asset.isPositive())
            {
                x += (angleRotation * r);
                y += (7 * Math.pow(velocityY, 2)) / (10 * g);
            }
            else
                if(!asset.isPositive())
                {
                    x -= (angleRotation * r);
                    y -= (7 * Math.pow(velocityY, 2)) / (10 * g);
                }
            asset.getShape().setLayoutX(x);
        asset.getShape().setLayoutY(y);
        System.out.println(angleRotation);
        asset.setWasRolling(true);

    }
}
