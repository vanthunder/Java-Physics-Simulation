package PhysicSimulation.Objects.ObjectContainer.PhysicsObjects;

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


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
    double x, y;
    public Image image = new Image("Images/kugel_mark.png");

    public Sphere()
    {
        super();
        this.setId("sphere");
        this.setRadius(radius);
        this.setFill(new ImagePattern(image));
        this.setLayoutX(X);
        this.setLayoutY(Y);
        this.setRotate(0);
        this.setCursor(Cursor.HAND);
        this.setOnMousePressed((t) ->
        {
            x = t.getSceneX();
            y = t.getSceneY();

            Circle c = (Circle) (t.getSource());
            c.toFront();
        });
        this.setOnMouseDragged((t) ->
        {
            double offsetX = t.getSceneX() - x;
            double offsetY = t.getSceneY() - y;

            Circle c = (Circle) (t.getSource());

            c.setCenterX(c.getCenterX() + offsetX);
            c.setCenterY(c.getCenterY() + offsetY);

            x = t.getSceneX();
            y = t.getSceneY();


        });


    }


}


