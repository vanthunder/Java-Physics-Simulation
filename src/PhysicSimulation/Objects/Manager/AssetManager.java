package PhysicSimulation.Objects.Manager;

import PhysicSimulation.Objects.ObjectContainer.StaticObjects.*;
import PhysicSimulation.Objects.SimulationTypes.StaticObjects;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
/**
 * @author Marvin Schubert
 * @version 0.1
 */
public class AssetManager
{
    public StaticObjects staticObjects = new StaticObjects();
    public ArrayList<AssetData> assets = new ArrayList<AssetData>();
    public DebugStaticRectangle debugStaticRectangle = new DebugStaticRectangle(0);
    public int assetCounter = 1;
    public AssetManager()
    {
        initManager();
    }

    // This Methods inits the Assets Manager.
    public void initManager()
    {
       // Converts the shape object into a asset object
       AssetData assetData = new AssetData("debungObject", debugStaticRectangle, debugStaticRectangle.getMass());
       AssetData assetData1 = new AssetData("a", debugStaticRectangle, 12);
       // Adds the asset object into the assets object arraylist
        assets.add(assetData);
        assets.add(assetData1);
       // Prints the object arraylist
        for (int i = 0; i< assets.size(); i++)
        {
            System.out.println("Asset Nr. "+assetCounter+": Name: "+assets.get(i).getName()+", Masse: "+assets.get(i).getMass()+ ", Object: "+assets.get(i).getShape());
            assetCounter++;
        }
    }

    public void addShapeToList (Shape shape)
    {
        AssetData assetData = new AssetData("new shape", shape, 0);
        assets.add(assetData);
    }

    public AssetData getShapeFromList(int index)
    {
        return assets.get(index);
    }

    public void getMass(int index)
    {
       assets.get(index).getMass();
    }

}
