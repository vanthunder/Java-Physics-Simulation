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
    // Constructor
    public AssetData(String name, Shape shape, int mass)
    {
        this.name = name;
        this.shape = shape;
        this.mass = mass;
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
}
