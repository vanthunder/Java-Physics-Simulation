package PhysicSimulation.Objects.ObjectContainer.PhysicsObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


/*
 *   @author Erwin Kling, Marvin Schubert
 *   @version 0.4
 */
// Inits the rolling sphere
public class Sphere extends Circle
{
    // Objects dimensions
    double X = 70;
    double Y = 70;
    int radius = 12;
    public Image image = new Image("Images/kugel_mark.png");

    public Sphere() {
        super();
        this.setId("sphere");
        this.setRadius(radius);
        this.setFill(new ImagePattern(image));
        this.setLayoutX(X);
        this.setLayoutY(Y);
        this.setRotate(0);
    }

}


