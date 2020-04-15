package PhysicSimulation.Objects.ObjectManager;

import javafx.scene.shape.Shape;

import java.util.ArrayList;
/**
 * @author Marvin Schubert
 * @version 0.1
 */
public class AssetManager
{
    public ArrayList<Shape> assets = new ArrayList<Shape>();
    public DebugStaticRectangle debugStaticRectangle;
    public AssetManager()
    {
        //assets.add(debugStaticRectangle);
    }

    public void addShapeToList (Shape shape)
    {
        assets.add(shape);
    }

    public Shape getShapeFromList()
    {
        return assets.get(0);
    }

}
