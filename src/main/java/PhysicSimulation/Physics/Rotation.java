package PhysicSimulation.Physics;

import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Objects.ShapeHelper;

/*
 *   @author Erwin Kling, Marvin Schubert
 *   @version 0.6
 */
public class Rotation
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    ShapeHelper helper = new ShapeHelper();
    boolean start = true;
    double counter = 1;
    double c = 0;
    double newAngleA = 0;

    public void rotate(AssetData asset, double dt)
    {
        // the radius
        double r = 0.12;
        double Fg = 10 * 9.81;
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
        else if (wn < 0)
        {
            if(wn > -10)
            {
                wn = 0;
            }

        }


        asset.setAngleVelocity(wn);
        // For gravitation purposes
        double velocityX = asset.getVelocityX();
        velocityX = (wn * r);
        asset.setVelocityX(velocityX);

        //double rotation = asset.getShape().getRotate();
        double rotation = helper.getAngle(asset.getShape());
        double newRotation = (wn * dt) / 2;
        double rotate = rotation;
        rotation = newRotation;
        double px = asset.getShape().getLayoutX();

        if (asset.isPositive())
        {
            px += (rotation * r);
            rotate += newRotation;
        } else if (!asset.isPositive())
        {
            px -= (rotation * r);
            rotate -= newRotation;
        }
        if (wn == 0)
        {
            px = asset.getShape().getLayoutX();
        } else
        {
            asset.getShape().setLayoutX(px);
            helper.setAngle(asset.getShape(), rotate);
            /*
            asset.getShape().setRotate(rotate);

             */
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
        double r = 0.012;
        double angleVelocity = asset.getAngleInclineVelocity();
        double g = 9.81;

        // Winkelbeschleunigung

        double angleAccerleration = (((g) * Math.sin(angle))) / r;
        //double angleAccerleration = 21.019154836749835970750439503968;

        // Tangentialbeschleunigung
        double at = (2/5)*g*Math.sin(angle);

        // Winkelgeschwindigkeit
        double angleVelocity1 = asset.getAngleInclineVelocity();
        angleVelocity1 += angleAccerleration*dt;
        asset.setAngleVelocity(angleVelocity1);
        asset.setAngleInclineVelocity(angleVelocity1);

        // Drehwinkel
        double angleRotation = helper.getAngle(asset.getShape());
        angleRotation += (angleVelocity1 * dt) / 2;



        // Hier später if Bedingung für umkehr einfügen
        if (asset.isPositive())
        {
            //angleRotation += (angleVelocity*dt)/2;
        } else if (!asset.isPositive())
        {
            //angleRotation -= (angleVelocity*dt)/2;
        }
        // asset.getShape().setRotate(angleRotation);

        double wn = asset.getAngleVelocity();
        wn += Math.sqrt(10 * r) * dt;
        double newRotation = (wn * dt) / 2;
        angleRotation += newRotation;
        asset.setAngleVelocity(wn);


        double x = asset.getShape().getLayoutX();
        double y = asset.getShape().getLayoutY();
        // Berechne Position X - s oder Weg
        double velocityY = asset.getVelocityY();
        velocityY = angleVelocity1 * r;


        //x += (angleRotation * r);
        //y += (7 * Math.pow(velocityY, 2)) / (10*g);
        //System.out.println(ANSI_GREEN+" Positive "+ ANSI_RESET);

        if (asset.getAngleVelocity() >= 0)
        {
            //x += (angleRotation * r);
            //y += (7 * Math.pow(velocityY, 2)) / (10 * g);

            System.out.println(ANSI_GREEN + " Positive " + angleRotation + ANSI_RESET);
        } else if
        (asset.getAngleVelocity() <= 0)
        {
            // Roll Up

                /*
                x -= (angleRotation * r);
                y -= (7 * Math.pow(velocityY, 2)) / (10 * g);
                System.out.println(ANSI_RED+" Negativ "+ ANSI_RESET);

                 */
        }


        helper.setAngle(asset.getShape(), angleRotation);
        System.out.println(ANSI_GREEN + " Positive " + angleRotation +asset.getShape().getId()+ "Winkel!!!!!!!!!!!!: "+angle+ANSI_RESET);
        x += (angleRotation * r);
        y += (6.4 * Math.pow(velocityY, 2)) / (10 * g);


        // Berechne VX
        double velocityX = asset.getVelocityX();
        velocityX += angleRotation * r;
        asset.setVelocityX(velocityX);

        asset.getShape().setLayoutX(x);
        asset.getShape().setLayoutY(y);
        System.out.println(angleRotation);
        asset.setWasRolling(true);

    }
}
