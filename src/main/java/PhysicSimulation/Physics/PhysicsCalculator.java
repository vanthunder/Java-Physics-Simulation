package PhysicSimulation.Physics;

import PhysicSimulation.Controller.GUIController;
import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Physics.Debug.Movement;
import PhysicSimulation.Physics.Debug.MovementWithAngle;
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
    public ArrayList<AssetData>physicAssets = new ArrayList<>();
    public ArrayList<AssetData>staticAssets = new ArrayList<>();
    public ArrayList<Circle>pointsOfMovementList = new ArrayList<>();
    // Instants the Physics classes
    public Gravitation gravitation = new Gravitation();
    public Collision collision = new Collision();
    public MovementWithAngle angleMove = new MovementWithAngle();
    public Movement planeMove = new Movement();
    //regular values
    double startSpeed = 5;
    long time = 0;
    double acceleration = 0;
    double velocity = 0;
    private double deltaTime = 0.03;

    public final double getDeltaTime() {
        if (deltaTime != 0){
            return GUIController.dtFactor.get()*0.03;
        }
        return 0;
    }

public void initCalculation(ArrayList<AssetData> arrayList)
{
    for (int i = 0; i < arrayList.size(); i++)
    {
        if (arrayList.get(i).getPhysicType().equals("physic"))
        {
            physicAssets.add(arrayList.get(i));
            System.out.println(arrayList.get(i).getName() + arrayList.get(i).getPhysicType());
            //proofCollision();
        }
        if (arrayList.get(i).getPhysicType().equals("static"))
            {
                staticAssets.add(arrayList.get(i));
            }
        }
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


    public double getVelocity()
    {
        return velocity;
    }

    public void setVelocity(double velocity)
    {
        this.velocity = velocity;
    }

    // This method creates points of movement
    public void createPointOfMovement()
    {
        Circle circle = new Circle(300, 100, 10);
        Circle circle1 = new Circle(100, 100, 100);
        for(int i = 0; i < angleMove.getmPoints().size(); i++)
        {
            pointsOfMovementList.add(angleMove.getmPoints().get(i));
        }
    }

    public ArrayList<Circle> getPointsOfMovementList()
    {
        return pointsOfMovementList;
    }

    public void setPointsOfMovementList(ArrayList<Circle> pointsOfMovementList)
    {
        this.pointsOfMovementList = pointsOfMovementList;
    }


}
