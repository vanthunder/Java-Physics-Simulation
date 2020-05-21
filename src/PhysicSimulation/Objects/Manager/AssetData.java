package PhysicSimulation.Objects.Manager;

import javafx.scene.shape.Shape;
/**
 * @author Marvin Schubert
 * @version 0.1
 */
public class AssetData
{
    // Defines the data constants
    String name;
    Shape shape;
    int mass;
    double velocity;
    double velocityX = 0;
    double velocityY = 0;
    double angleVelocity = 0;
    double acceleration;
    double currentPositionX = 0;
    double currentPositionY = 0;
    // Dynamic angle of an object
    double angle;
    // Static angle of an object
    double staticAngle;
    double startPositionX;
    double startPositionY;
    boolean collision = false;
    String physicType;
    boolean inclinedPlane = false;
    boolean isNormalPlane = false;
    boolean isMoved = false;
    double calcY = 0;
    double calcX = 0;
    // Defines the reset Values
    double restX = 0;
    double restY = 0;
    int direction = 0;
    boolean incCollision = false;
    boolean planeCollision = false;
    double radius = 0;
    double wa = 0;
    double counter = 0;
    double inclineTimesCounter = 0;
    boolean positive = true;
    boolean isFalling = false;
    double angleInclineVelocity = 0;
    Shape currentCollisionObject = null;
    // Constructor defines position, size, mass, direction, acceleration and velocity
    // position and size is defined by shape
    public AssetData(String name, Shape shape, int mass, double velocity, double acceleration, int direction, String physicsType)
    {
        this.name = name;
        this.shape = shape;
        this.mass = mass;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.restX = shape.getLayoutX();
        this.restY = shape.getLayoutY();
        this.direction = direction;
        this.physicType = physicsType;
        this.collision = false;
        //this.startPositionX = shape.getLayoutX();
        //this.startPositionY = shape.getLayoutY();
    }

    // Getter and Setter of the values

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Shape getShape()
    {
        return shape;
    }

    public void setShape(Shape shape)
    {
        this.shape = shape;
    }

    public int getMass()
    {
        return mass;
    }

    public void setMass(int mass)
    {
        this.mass = mass;
    }

    public double getVelocity()
    {
        return velocity;
    }

    public void setVelocity(double velocity)
    {
        this.velocity = velocity;
    }

    public double getAcceleration()
    {
        return acceleration;
    }

    public void setAcceleration(long acceleration)
    {
        this.acceleration = acceleration;
    }

    public double getRestX()
    {
        return restX;
    }

    public double getRestY()
    {
        return restY;
    }

    public int getDirection()
    {
        return direction;
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    public String getPhysicType()
    {
        return physicType;
    }

    public boolean getCollision()
    {
        return collision;
    }

    public void setCollision(boolean collision)
    {
        this.collision = collision;
    }

    public double getStartPositionX()
    {
        return startPositionX;
    }

    public double getStartPositionY()
    {
        return startPositionY;
    }

    public double getAngle()
    {
        return angle;
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    public double getStaticAngle()
    {
        return staticAngle;
    }

    public void setStaticAngle(double staticAngle)
    {
        this.staticAngle = staticAngle;
    }


    public boolean isInclinedPlane()
    {
        return inclinedPlane;
    }

    public void setInclinedPlane(boolean inclinedPlane)
    {
        this.inclinedPlane = inclinedPlane;
    }

    public boolean isMoved()
    {
        return isMoved;
    }

    public void setMoved(boolean moved)
    {
        isMoved = moved;
    }

    public double getVelocityX()
    {
        return velocityX;
    }

    public void setVelocityX(double velocityX)
    {
        this.velocityX = velocityX;
    }

    public double getVelocityY()
    {
        return velocityY;
    }

    public void setVelocityY(double velocityY)
    {
        this.velocityY = velocityY;
    }

    public double getCurrentPositionX()
    {
        return currentPositionX;
    }

    public void setCurrentPositionX(double currentPositionX)
    {
        this.currentPositionX = currentPositionX;
    }

    public double getCurrentPositionY()
    {
        return currentPositionY;
    }

    public void setCurrentPositionY(double currentPositionY)
    {
        this.currentPositionY = currentPositionY;
    }

    public void setStartPositionX(double startPositionX)
    {
        this.startPositionX = startPositionX;
    }

    public void setStartPositionY(double startPositionY)
    {
        this.startPositionY = startPositionY;
    }

    public boolean isNormalPlane()
    {
        return isNormalPlane;
    }

    public void setNormalPlane(boolean normalPlane)
    {
        isNormalPlane = normalPlane;
    }


    public double getCalcY()
    {
        return calcY;
    }

    public void setCalcY(double calcY)
    {
        this.calcY = calcY;
    }

    public double getCalcX()
    {
        return calcX;
    }

    public void setCalcX(double calcX)
    {
        this.calcX = calcX;
    }

    public boolean isIncCollision()
    {
        return incCollision;
    }

    public void setIncCollision(boolean incCollision)
    {
        this.incCollision = incCollision;
    }

    public boolean isPlaneCollision()
    {
        return planeCollision;
    }

    public void setPlaneCollision(boolean planeCollision)
    {
        this.planeCollision = planeCollision;
    }

    public double getAngleVelocity()
    {
        return angleVelocity;
    }

    public void setAngleVelocity(double angleVelocity)
    {
        this.angleVelocity = angleVelocity;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    public double getWa()
    {
        return wa;
    }

    public void setWa(double wa)
    {
        this.wa = wa;
    }

    public double getCounter()
    {
        return counter;
    }

    public void setCounter(double counter)
    {
        this.counter = counter;
    }

    public double getInclineTimesCounter()
    {
        return inclineTimesCounter;
    }

    public void setInclineTimesCounter(double inclineTimesCounter)
    {
        this.inclineTimesCounter = inclineTimesCounter;
    }

    public boolean isPositive()
    {
        return positive;
    }

    public void setPositive(boolean positive)
    {
        this.positive = positive;
    }


    public boolean isFalling()
    {
        return isFalling;
    }

    public void setFalling(boolean falling)
    {
        isFalling = falling;
    }
    public double getAngleInclineVelocity()
    {
        return angleInclineVelocity;
    }

    public void setAngleInclineVelocity(double angleInclineVelocity)
    {
        this.angleInclineVelocity = angleInclineVelocity;
    }

    public Shape getCurrentCollisionObject()
    {
        return currentCollisionObject;
    }

    public void setCurrentCollisionObject(Shape currentCollisionObject)
    {
        this.currentCollisionObject = currentCollisionObject;
    }





}
