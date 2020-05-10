package PhysicSimulation.Objects.Manager;

import PhysicSimulation.Objects.ObjectContainer.PhysicsObjects.DebugCircle;
import PhysicSimulation.Objects.ObjectContainer.PhysicsObjects.DebugPhysicsCircle;
import PhysicSimulation.Objects.ObjectContainer.StaticObjects.DebugStaticRectangle;
import PhysicSimulation.Objects.ObjectContainer.StaticObjects.Plane;
import PhysicSimulation.Objects.ObjectContainer.StaticObjects.SchiefeBahn;
import PhysicSimulation.Objects.ObjectContainer.StaticObjects.StaticPlatform;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
/**
 * @author Marvin Schubert
 * @version 0.1
 */
public class AssetManager
{
    public ArrayList<AssetData> assets = new ArrayList<AssetData>();
    public DebugStaticRectangle debugStaticRectangle = new DebugStaticRectangle(10, 0,0);
    public DebugPhysicsCircle debugPhysicsCircle = new DebugPhysicsCircle();
    public StaticPlatform staticPlatform = new StaticPlatform();
    public SchiefeBahn schiefeBahn = new SchiefeBahn();
    public Plane plane = new Plane();
    public DebugCircle debugCircle = new DebugCircle();
    public int assetCounter = 1;
    public AssetManager()
    {
        initManager();
    }

    // This Methods inits the Assets Manager.
    public void initManager()
    {
       // Converts the shape object into a asset object
       AssetData assetData = new AssetData("debungObject", debugStaticRectangle, debugStaticRectangle.getMass(), debugStaticRectangle.getVelocity(), debugStaticRectangle.getAcceleration(), 0, "physic");
       AssetData assetData1 = new AssetData("Rechteck", debugStaticRectangle, 10, 0,0, 0, "static");
       AssetData assetData2 = new AssetData("Circle", debugPhysicsCircle, 10, 0, 0, 0,"physic");
       AssetData assetData3 = new AssetData("static Platform", staticPlatform, 0, 0, 0, 0, "static");
       AssetData assetData4 = new AssetData("schiefe Bahn", schiefeBahn, 0, 0, 0, 0, "static");
       AssetData assetData5 = new AssetData("static Plane", plane, 0, 0, 0, 0, "static");
       AssetData assetData6 = new AssetData("Circle#2",debugCircle,10, 0, 0, 0, "physic");
       // Adds some special parameters to an asset
        //assetData2.setStartPositionX(debugPhysicsCircle.getX());
        //assetData2.setStartPositionY(debugPhysicsCircle.getY());
        assetData1.setNormalPlane(true);
        assetData3.setNormalPlane(true);
        assetData4.setStaticAngle(schiefeBahn.getAngle());
        assetData4.setInclinedPlane(true);
        assetData5.setNormalPlane(true);
       // Adds the asset object into the assets object arraylist
        //assets.add(assetData);
        assets.add(assetData1);
        assets.add(assetData2);
        assets.add(assetData3);
        assets.add(assetData4);
        assets.add(assetData5);
        assets.add(assetData6);
       // Prints the object arraylist
        for (int i = 0; i< assets.size(); i++)
        {
            System.out.println("Asset Nr. "+assetCounter+": Name: "+assets.get(i).getName()+", Masse: "+assets.get(i).getMass()+" kg "+"Richtung in Grad: "+assets.get(i).getDirection()+", Geschwindigkeit: "+assets.get(i).getAcceleration()+" m/s"+", Beschleunigung: "+assets.get(i).getAcceleration()+" m/s "+ ", Object: "+assets.get(i).getShape());
            assetCounter++;
        }
    }

    public void addShapeToList (Shape shape)
    {
        AssetData assetData = new AssetData("new shape", shape, 0, 0, 0, 0, "physic");
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

    public ArrayList<AssetData> getAssets()
    {
        return assets;
    }

}
