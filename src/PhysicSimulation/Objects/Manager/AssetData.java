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
    double acceleration;
    boolean collison = false;
    String physicType;
    // Defines the reset Values
    double restX = 0;
    double restY = 0;
    int direction = 0;
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
        this.collison = false;
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

    public void setVelocity(long velocity)
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

    public boolean getCollison()
    {
        return collison;
    }

    public void setCollison(boolean collison)
    {
        this.collison = collison;
    }
}
