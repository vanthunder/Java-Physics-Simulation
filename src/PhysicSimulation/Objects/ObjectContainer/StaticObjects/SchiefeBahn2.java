package PhysicSimulation.Objects.ObjectContainer.StaticObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class SchiefeBahn2 extends Rectangle {

    public SchiefeBahn2(){
        this.setRotate(-30);
        this.setId("rectangle");
        this.setLayoutX(400);
        this.setLayoutY(600);
        this.setWidth(400);
        this.setHeight(8);
        this.setFill(Color.RED);
    }

}
