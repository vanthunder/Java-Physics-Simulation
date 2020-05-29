package PhysicSimulation.Objects.ObjectContainer.StaticObjects;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Plane extends Rectangle
{
    double x, y;
    boolean dragDetected = false;
    Rectangle rectangle = new Rectangle();

    public Plane()
    {
        super();
        this.setId("plane");
        this.setWidth(260);
        this.setHeight(10);
        this.setLayoutX(0);
        this.setLayoutY(300);
        this.getStyleClass().add("plane");
        this.setCursor(Cursor.HAND);
        this.setOnMousePressed((t) ->
        {
            x = t.getSceneX();
            y = t.getSceneY();

            Rectangle r = (Rectangle) (t.getSource());
            r.toFront();
            if (dragDetected == true)
            {
                dragDetected = false;
                r.setStroke(Color.TRANSPARENT);
                this.setStroke(Color.TRANSPARENT);
            } else if (dragDetected == false)
            {
                dragDetected = true;
                r.setStroke(Color.RED);
                this.setStroke(Color.RED);
            }
            System.out.println(dragDetected);
        });

        this.setOnMouseDragged((t) ->
        {
            double offsetX = t.getSceneX() - x;
            double offsetY = t.getSceneY() - y;

            Rectangle r = (Rectangle) (t.getSource());
            rectangle = r;

            r.setX(r.getX() + offsetX);
            r.setY(r.getY() + offsetY);

            x = t.getSceneX();
            y = t.getSceneY();

        });
        this.setOnScroll(new EventHandler<ScrollEvent>()
        {
            @Override
            public void handle(ScrollEvent scrollEvent)
            {
                if (dragDetected == true)
                {
                    rectangle.setRotate(rectangle.getRotate() + scrollEvent.getTextDeltaY());
                }
            }
        });
    }

}
