package PhysicSimulation.Physics;

import PhysicSimulation.Objects.Manager.AssetData;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Date;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

 /*
 *   @author Erwin Kling, Marvin Schubert
 *   @version 0.1.
 */


public class PhysicsCalculator
{
    //All Physics Calculation-Methods should be instantiated here
   /* public PhysicsCalculator(Circle shape) {

        if (shape.getLayoutBounds().getMaxX() <= 800) {
            new Bewegung(shape, startpoint, endpoint, startSpeed, accelaration, mass, time);
            this.time +=getTime();
            mass = getMassfromRect(shape);
            shape.setCenterX(getStartpoint());
            System.out.println("abgelaufene Zeit: " + getTime());
        }
    }*/

    public ArrayList<AssetData>physicAssets = new ArrayList<>();
    public ArrayList<AssetData>staticAssets = new ArrayList<>();

    // Instants the Physics classes
    public Gravitation gravitation = new Gravitation();
    public Collision collision = new Collision();
    public MovementWithAngle angleMove = new MovementWithAngle();

    //regular values
    double dimensions = 0;
    double startSpeed = 5;
    double endSpeed = 0;
    int mass = 1;
    long time = 0;
    double path = 0;
    double aWork = 0;
    double acceleration = 0;


    double velocity = 0;

    //acceleration Values
    float accelerationDg = 0;

    //Thrust Values
    double lWork = 0;
    double lHeight = 0;

    //Gravitation Values
    double gConstant;
    double gAccelaration;
    double angle;
    double gForce;
    int counter = 0;

    // Calculates the physics of the give object
    public void calculatePhysics()
    {
        // Loop for Gravitation
        if(!collision.detectCollision(physicAssets.get(0).getShape(), staticAssets.get(0).getShape()) &
                !collision.detectCollision(physicAssets.get(0).getShape(), staticAssets.get(1).getShape()) &
                !collision.detectCollision(physicAssets.get(0).getShape(), staticAssets.get(2).getShape())&
                !collision.detectCollision(physicAssets.get(0).getShape(), staticAssets.get(3).getShape()))
        {
            gravitation.forceGravitation(physicAssets.get(0).getShape(), physicAssets.get(0));
        }
        else
        if(collision.detectCollision(physicAssets.get(0).getShape(), staticAssets.get(0).getShape()) & staticAssets.get(0).isInclinedPlane() == true ||
                collision.detectCollision(physicAssets.get(0).getShape(), staticAssets.get(1).getShape()) & staticAssets.get(1).isInclinedPlane() == true ||
                collision.detectCollision(physicAssets.get(0).getShape(), staticAssets.get(2).getShape()) & staticAssets.get(2).isInclinedPlane() == true||
                collision.detectCollision(physicAssets.get(0).getShape(), staticAssets.get(3).getShape()) & staticAssets.get(3).isInclinedPlane() == true)
        {
            angleMove.calculateMotion(physicAssets.get(0), staticAssets.get(2));
            //calculateMovement(physicAssets.get(0).getShape(), staticAssets.get(2).getShape(),physicAssets.get(0).getVelocity(),physicAssets.get(0).getAngle());
            //gravitation.resetCalculation(physicAssets.get(0));
        }



        /*
        for (int i = 0; i < physicAssets.size(); i++)
            {
                for (int a = 0; a < staticAssets.size(); a++)
                {
                    AssetData physicAsset = physicAssets.get(i);
                    AssetData staticAsset = staticAssets.get(a);
                    if (collision.detectCollision(physicAsset.getShape(), staticAsset.getShape()) == false)
                    {
                        collision.detectCollision(physicAssets.get(i).getShape(), staticAssets.get(a).getShape());
                        calculateGravitation(physicAssets.get(i).getShape(), physicAssets.get(i));
                        System.out.println("Abstand zwischen: " + physicAssets.get(i).getShape().getId() + " und " + staticAssets.get(0).getShape().getId() + " = " + collision.distance(physicAssets.get(i).getShape(), staticAssets.get(0).getShape()));
                        break;
                    }
                }
                if(collision.detectCollision(physicAssets.get(i).getShape(), staticAssets.get(1).getShape()) == true & staticAssets.get(i).isInclinedPlane() == true)
                    {

                        angleMove.calculateMotion(physicAssets.get(i), staticAssets.get(1));
                        System.out.println(physicAssets.get(0).getCollision());

                        //calculateMovement(physicAssets.get(i).getShape(), staticAssets.get(0).getShape(),physicAssets.get(i).getVelocity(),physicAssets.get(i).getAngle());
                    }



            }

         */
    }
    // Just a debug method for testing purposes
    public void calc()
    {
        for (int i = 0; i < staticAssets.size(); i++)
        {
            if(collision.detectCollision(physicAssets.get(0).getShape(), staticAssets.get(i).getShape()))
            {
                gravitation.setCollision(true);
                if(staticAssets.get(i).isInclinedPlane() == true)
                {
                    System.out.println("WAHR!"+0+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    angleMove.calculateMotion(physicAssets.get(0), staticAssets.get(i));
                }
            }
            else
            if(!collision.detectCollision(physicAssets.get(0).getShape(), staticAssets.get(i).getShape()) & !gravitation.isCollision())
            {
                System.out.println("falsch!"+i+" Size"+staticAssets.size());
                if(!gravitation.isCollision())
                {
                    calculateGravitation(physicAssets.get(0).getShape(), physicAssets.get(0));
                }
                gravitation.setCollision(false);
            }


        }
    }

    // Calculates Gravitation force // More forces will be add over time
    public void calculateGravitation(Shape shape, AssetData assetData)
    {
        // Calculates the gravitation, with a given start velocity
        gravitation.forceGravitation(shape, assetData);
        // calculates the velocity
        setVelocity(gravitation.getVelocity());
    }

    //calculates the Motion Physics
    public void calculateMovement(Shape pObject,Shape sObject,double velocity, double angle){
        //
        Bewegung move = new Bewegung();
        move.movement(pObject,sObject,velocity,angle);
    }

    public boolean proofCollision()
    {
        for(int i = 0; i < physicAssets.size(); i++)
        {
            for(int a = 0; a < staticAssets.size(); a++)
            {
                if(collision.detectCollision(physicAssets.get(i).getShape(), staticAssets.get(a).getShape()) == true)
                {
                    physicAssets.get(i).setCollision(true);
                    return true;
                }
                else
                if(collision.detectCollision(physicAssets.get(i).getShape(), staticAssets.get(a).getShape()) == true)
                {
                    physicAssets.get(i).setCollision(false);
                    return false;
                }
            }
        }
        return false;
    }

    public void proofCircleCollision()
    {
        for(int i = 0; i < physicAssets.size(); i++)
        {
            for(int a = 0; a < physicAssets.size(); a++)
            {
                // Proofs that the detector does not detects the same object
                if(physicAssets.get(i) != physicAssets.get(a))
                {
                    collision.circleCollision(physicAssets.get(i), physicAssets.get(a));
                }
            }
        }
    }

    public void initCalculation(ArrayList<AssetData> arrayList)
    {
        for (int i = 0; i < arrayList.size(); i++)
        {
            if(arrayList.get(i).getPhysicType().equals("physic"))
            {
                physicAssets.add(arrayList.get(i));
                System.out.println(arrayList.get(i).getName()+arrayList.get(i).getPhysicType());
                proofCollision();
            }
            if (arrayList.get(i).getPhysicType().equals("static"))
            {
                staticAssets.add(arrayList.get(i));
            }
        }
    }

    // Global Reset method to reset the physic classes
    public void resetPhysic()
    {
        //Resets the physic of the gravitation class
        for(int i = 0; i<physicAssets.size(); i++)
        {
            physicAssets.get(i).getShape().setTranslateX(physicAssets.get(i).getStartPositionX());
            physicAssets.get(i).getShape().setTranslateY(physicAssets.get(i).getStartPositionY());
            physicAssets.get(i).setCollision(false);
            gravitation.resetCalculation(physicAssets.get(i));
            angleMove.resetAngleMovement();
        }
    }



    public static double getStartpoint() {
        return startpoint;
    }

    public void setStartpoint(double startpoint) {
        this.startpoint = startpoint;
    }

    long timeBefore =0;

    public static long getTime() {

        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;

        while (elapsedTime < 2*60*1000) {
            elapsedTime = (new Date()).getTime() - startTime;
        }
        return elapsedTime;
    }

    public void setTime(long time) {
        this.time = time;
    }

    static double startpoint = 1;
    double endpoint = 0;

    public double getSpeed() {
        return startSpeed;
    }

    public double getAccelaration() {
        return acceleration;
    }

    public void setAccelaration(double acceleration) {
        this.acceleration = acceleration;
    }

    public void setSpeed(double startSpeed) {
        this.startSpeed = startSpeed;
    }


    public static int getMassfromRect(Shape shape) {
        int mass = (int) (shape.getStrokeWidth() * pow(PI, 2));
        System.out.println("The Mass of the Rectangle: " + mass);
        return mass;
    }

    public static int getMassFromCircle(Circle circle) {
        int mass = (int) (PI * pow(circle.getRadius(), 2));
        System.out.println("The Mass of the Circle: " + mass);
        return mass;
    }

    public double getVelocity()
    {
        return velocity;
    }

    public void setVelocity(double velocity)
    {
        this.velocity = velocity;
    }




}
