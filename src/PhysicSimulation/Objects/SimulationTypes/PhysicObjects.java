package PhysicSimulation.Objects.SimulationTypes;

import PhysicSimulation.Objects.Manager.AssetData;
import PhysicSimulation.Objects.Manager.AssetManager;
import PhysicSimulation.SimualtionPipeline.SimulationLoop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;


/**
 * @author Marvin Schubert
 * @author Erwin Kling
 * @version 0.1
 */
public class PhysicObjects extends Shape {

    Circle circle1 = new Circle(1);
    Circle circle2 = new Circle(2);
    Shape shape;



    int mass;

    public PhysicObjects(int mass,double xPosition, double yPosition) {
        super();
        mass = (int) (this.getLayoutX() * this.getLayoutY());
        this.mass = mass;

    }

    public int getMass() {
        return mass;
    }

}
