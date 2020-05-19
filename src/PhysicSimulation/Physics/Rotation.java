package PhysicSimulation.Physics;

import PhysicSimulation.Objects.Manager.AssetData;

/*
 *   @author Erwin Kling, Marvin Schubert
 *   @version 0.2
 */
public class Rotation
{
    double w=0;
    double ab = 0;
    boolean start = true;
    double counter = 1;
    double c = 0;
    double newAngleA = 0;
    public void rotate (AssetData asset, double dt)
    {

        // Calculate angle velocity
        double aV = 2*Math.PI/dt;
        /*
        asset.setAngleVelocity(aV);
        // Calculate the angle
        double angle = asset.getShape().getRotate();
        angle += aV*dt;
        asset.getShape().setRotate(angle);

         */
        // Calculate the rotation angle
        double F = 10*1.5;
        double M = F*0.12;
        double J = (2/5)*10*Math.pow(10, 2);
        double aAngle = M/J;
        double r = 12;
        double a = asset.getAcceleration()/r;
        //double w = asset.getShape().getRotate();
        w += 2*dt;
        double alpha = asset.getShape().getRotate();
        alpha += (w*dt)/2;
        // Calculate the way
        //double px = asset.getShape().getLayoutX()+a*asset.getRadius();
        double ar =(4*Math.PI*r)/Math.pow(dt, 2);
        //ab -= 0.16;


        if(ab <= 0)
        {
            ab = 0;
        }
        double Fg = 10*9.81;
        double Fr = 0.006*Fg;
        double wn = asset.getAngleVelocity();
        if(wn == 0 && start == true)
        {
            wn += Math.sqrt(10*r*Fr);
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


        double rotation = asset.getShape().getRotate();
        double newRotation = (wn*dt)/2;
        double rotate = rotation;
        rotate += newRotation;
        rotation = newRotation;


        //asset.getShape().setLayoutX(px);
        //rotation = asset.getShape().getLayoutX()/24;


        double px = asset.getShape().getLayoutX();

                px += (rotation * r)/100;
                if (wn == 0)
                {
                    px = asset.getShape().getLayoutX();
                }
                else
                {
                  asset.getShape().setLayoutX(px);
                  asset.getShape().setRotate(rotate);
                }
        System.out.println("Rotation"+rotation+"_"+" Y:"+asset.getShape().getLayoutX()+"WN: "+wn+"   brems:"+bremsG);
    }

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
        }
        else
        if(rotation <= 360*2)
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
        double angle = 40;
        double rotation = asset.getShape().getRotate();
        double r = 0.012;
        double angleVelocity = asset.getAngleVelocity();
        double g = 9.81;

        // Winkelbeschleunigung
        double angleAccerleration = ((0.4*(g)*Math.sin(Math.toRadians(angle)))/r);
        //double angleAccerleration = 21.019154836749835970750439503968;
        // Tangentialbeschleunigung
        double at = (2/5)*g*Math.sin(angle);
        // Winkelgeschwindigkeit
        double angleVelocity1= asset.getAngleVelocity();
        angleVelocity1 += angleAccerleration*dt;
        asset.setAngleVelocity(angleVelocity1);
        // Drehwinkel
        double angleRotation = asset.getShape().getRotate();
        // Hier später if Bedingung für umkehr einfügen
        angleRotation += (angleVelocity*dt)/2;
        asset.getShape().setRotate(angleRotation);

        double x = asset.getShape().getLayoutX();
        double y = asset.getShape().getLayoutY();

        if(rotation >= 360*10)
        {
            int i = (int) (angleRotation/360)-1;
            int newAngle = (int) (angleRotation-(360*i));
            System.out.println(newAngle);

            double velocityY = asset.getVelocityY();
            velocityY += angleVelocity*r;
            x += (newAngle*r);
            y +=(7*Math.pow(velocityY, 2))/(10*g);
            asset.getShape().setLayoutX(x);
            asset.getShape().setLayoutY(y);
        }
        else
        {
            // Berechne Position X - s oder Weg

            x += (angleRotation * r);

            asset.getShape().setLayoutX(x);
            // Berechne Position y - h wie Höhe
            // Berechner Schwerpunktgeschwindigkeit
            double velocityY = asset.getVelocityY();
            velocityY = angleVelocity * r;
            //asset.setVelocityY(velocityY);

            y += (7 * Math.pow(velocityY, 2)) / (10 * g);
            asset.getShape().setLayoutY(y);
        }






        //System.out.println(angleRotation);



    }
}
