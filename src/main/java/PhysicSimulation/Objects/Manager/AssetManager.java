package PhysicSimulation.Objects.Manager;

import PhysicSimulation.Objects.ObjectContainer.PhysicsObjects.SphereBall;
import PhysicSimulation.Objects.ObjectContainer.StaticObjects.Borders.Ground;
import PhysicSimulation.Objects.ObjectContainer.StaticObjects.Borders.Left;
import PhysicSimulation.Objects.ObjectContainer.StaticObjects.Borders.Right;
import PhysicSimulation.Objects.ObjectContainer.StaticObjects.Borders.Top;
import PhysicSimulation.Objects.ObjectContainer.StaticObjects.InclinedPlane;
import PhysicSimulation.Objects.ObjectContainer.StaticObjects.Plane;
import PhysicSimulation.Objects.ShapeHelper;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
/**
 * @author Erwin Kling, Marvin Schubert
 * @version 0.4
 */
public class AssetManager
{
    public ArrayList<AssetData> assets = new ArrayList<AssetData>();
    public SphereBall sphere = new SphereBall();
    InclinedPlane plane1 = new InclinedPlane();
    Plane plane2 = new Plane();
    InclinedPlane plane3 = new InclinedPlane();
    Plane plane4 = new Plane();
    InclinedPlane plane5 = new InclinedPlane();
    Plane plane6 = new Plane();
    public Ground ground = new Ground();
    public Left left = new Left();
    public Right right = new Right();
    public Top top = new Top();
    public int assetCounter = 1;
    ShapeHelper helper = new ShapeHelper();
    public AssetManager()
    {
        initManager();
    }

    // This Methods inits the Assets Manager.
    public void initManager()

    {


        // Rotations
        // For normal Planes
        Rotate rotateN = new Rotate();
        rotateN.setAngle(0);
        Rotate rotateN1 = new Rotate();
        rotateN1.setAngle(0);
        Rotate rotateN2 = new Rotate();
        rotateN2.setAngle(0);
        // For Inclined Planes
        Rotate rotate1 = new Rotate();
        rotate1.setAngle(45);
        Rotate rotate2 = new Rotate();
        rotate2.setAngle(-40);
        Rotate rotate3 = new Rotate();
        rotate3.setAngle(40);


        //Shape
        plane1.setLayoutY(110);
        plane1.setLayoutX(37);
        plane1.getTransforms().add(rotate1);


        plane2.setLayoutX(170);
        plane2.setLayoutY(250);
        plane2.setWidth(170);
        plane2.getTransforms().add(rotateN);


        plane3.getTransforms().add(rotate2);
        plane3.setLayoutX(402);
        plane3.setLayoutY(425);
        plane3.setWidth(300);

        plane4.setLayoutX(300);
        plane4.setLayoutY(500);
        plane4.getTransforms().add(rotateN1);

        plane5.getTransforms().add(rotate3);
        plane5.setLayoutY(400);
        plane5.setLayoutX(48);
        plane5.setWidth(470);

        plane6.setLayoutY(700);
        plane6.setLayoutX(400);
        plane6.setWidth(100);
        plane6.getTransforms().add(rotateN2);

        // Converts the shape object into a asset object
        AssetData assetData1 = new AssetData("Circle", sphere, 10, 0, 2, 0, "physic");
        AssetData assetData2 = new AssetData("plane1", plane1, 0, 0, 0, 0, "static");
        AssetData assetData3 = new AssetData("plane2", plane2, 0, 0, 0, 0, "static");
        AssetData assetData4 = new AssetData("plane3", plane3, 0, 0, 0, 0, "static");
        AssetData assetData5 = new AssetData("plane4", plane4, 0, 0, 0, 0, "static");
        AssetData assetData6 = new AssetData("plane5", plane5, 0, 0, 0, 0, "static");
        AssetData assetData7 = new AssetData("Ground", ground, 0, 0, 0, 0, "static");
        AssetData assetData8 = new AssetData("Top", top, 0, 0, 0, 0, "static");
        AssetData assetData9 = new AssetData("Left", left, 0, 0, 0, 0, "static");
        AssetData assetData10 = new AssetData("Right", right, 0, 0, 0, 0, "static");
        AssetData assetData11 = new AssetData("plane6", plane6, 0, 0, 0, 0, "static");
        // Adds some special parameters to an asset
        assetData1.setStartPositionX(sphere.getX());
        assetData1.setStartPositionY(sphere.getY());
        assetData1.setRadius(sphere.getRadius());
        assetData1.setSphereBall(sphere);
        // Adds the asset object into the assets object arraylist
        //assets.add(assetData);
        //assets.add(assetData1);
        assets.add(assetData2);
        assets.add(assetData3);
        assets.add(assetData4);
        assets.add(assetData6);
        assets.add(assetData7);
        assets.add(assetData8);
        assets.add(assetData9);
        assets.add(assetData10);
        assets.add(assetData11);
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
