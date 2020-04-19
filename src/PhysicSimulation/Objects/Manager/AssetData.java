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
    long velocity;
    long acceleration;
    // Constructor
    public AssetData(String name, Shape shape, int mass, long velocity, long acceleration)
    {
        this.name = name;
        this.shape = shape;
        this.mass = mass;
        this.velocity = velocity;
        this.acceleration = acceleration;
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

    public long getVelocity()
    {
        return velocity;
    }

    public void setVelocity(long velocity)
    {
        this.velocity = velocity;
    }

    public long getAcceleration()
    {
        return acceleration;
    }

    public void setAcceleration(long acceleration)
    {
        this.acceleration = acceleration;
    }
}
