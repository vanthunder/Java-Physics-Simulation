package PhysicSimulation.Objects;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

public class ShapeHelper
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    // Get the Angle of a shape
    public double getAngle(Shape shape)
    {
        for (Transform transform : shape.getTransforms())
        {
            if (transform instanceof Rotate)
            {
                System.out.println(((Rotate) transform).getAngle());
                return ((Rotate) transform).getAngle();
            }
        }
        return 0;
    }

    // Set the Rotate Angle
    public void setAngle(Shape shape, double angle)
    {
        Rotate rotate = new Rotate();
        rotate.setAngle(angle);
        for (Transform transform : shape.getTransforms())
        {
            if (transform instanceof Rotate)
            {
                double oldRotation = ((Rotate) transform).getAngle();
                System.out.println(((Rotate) transform).getAngle());
                /*
                if (oldRotation >= 360)
                {
                    angle = 0;
                } else if (oldRotation <= -360)
                {
                    angle = 0;
                }

                 */
                ((Rotate) transform).setAngle(angle);
            }
        }
    }

    // Set the Rotate Angle
    public void translateY(Shape shape, double y)
    {
        Translate translate = new Translate();
        translate.setY(y);
        for (Transform transform : shape.getTransforms())
        {
            if (transform instanceof Translate)
            {
                double oldTranslate = ((Translate) transform).getY();

                ((Translate) transform).setY(y);
            }
        }
    }

    // Get the Angle of a shape
    public double getY(Shape shape)
    {
        for (Transform transform : shape.getTransforms())
        {
            if (transform instanceof Translate)
            {
                System.out.println(((Translate) transform).getY());
                return ((Translate) transform).getY();
            }
        }
        return 0;
    }

    // Set the Rotate Angle
    public void translateX(Shape shape, double x)
    {
        Translate translate = new Translate();
        translate.setY(x);
        for (Transform transform : shape.getTransforms())
        {
            if (transform instanceof Translate)
            {
                double oldTranslate = ((Translate) transform).getX();

                ((Translate) transform).setX(x);
            }
        }
    }

    // Get the Angle of a shape
    public double getX(Shape shape)
    {
        for (Transform transform : shape.getTransforms())
        {
            if (transform instanceof Translate)
            {
                System.out.println(((Translate) transform).getX());
                return ((Translate) transform).getX();
            }
        }
        return 0;
    }


    //Get Left Corner
    public double getLeftCornerX(Shape shape)
    {
        double topMinX = shape.getLayoutX();
        double topMinY = shape.getLayoutY();
        double width = shape.getBoundsInLocal().getWidth();
        double height = shape.getBoundsInLocal().getHeight();
        double leftCornerY = topMinY-(height/2);
        double leftCornerX = topMinX;
        return leftCornerX;
    }
    //Get Left Corner
    public double getLeftCornerY(Shape shape)
    {
        double topMinX = shape.getLayoutX();
        double topMinY = shape.getLayoutY();
        double width = shape.getBoundsInLocal().getWidth();
        double height = shape.getBoundsInLocal().getHeight();
        double leftCornerY = topMinY+(height/2);
        double leftCornerX = topMinX;
        return leftCornerY;
    }

    // get the Middle
    public double getMidX(Shape shape)
    {
        double topMinX = shape.getLayoutX();
        double topMinY = shape.getLayoutY();
        double width = shape.getBoundsInLocal().getWidth();
        double height = shape.getBoundsInLocal().getHeight();
        double leftCornerY = topMinY-(height/2);
        double leftCornerX = topMinX;
        double MidX = leftCornerX+(leftCornerX/2);
        return MidX;
    }
    // get the Middle
    public double getMidY(Shape shape)
    {
        double topMinX = shape.getLayoutX();
        double topMinY = shape.getLayoutY();
        double width = shape.getBoundsInLocal().getWidth();
        double height = shape.getBoundsInLocal().getHeight();
        double leftCornerY = topMinY-(height/2);
        double leftCornerX = topMinX;
        double MidX = leftCornerX+(leftCornerX/2);
        double MidY = leftCornerY;
        return MidY;
    }

    // get right corner
    public double getRightCornerX(Shape shape)
    {
        double topMinX = shape.getLayoutX();
        double topMinY = shape.getLayoutY();
        double width = shape.getBoundsInLocal().getWidth();
        double height = shape.getBoundsInLocal().getHeight();
        double leftCornerY = topMinY-(height/2);
        double leftCornerX = topMinX;
        double MidX = leftCornerX+(leftCornerX/2);
        double MidY = leftCornerY;
        double rightCornerX = topMinX+width;
        return  rightCornerX;
    }
    // get right corner
    public double getRightCornerY(Shape shape)
    {
        double topMinX = shape.getLayoutX();
        double topMinY = shape.getLayoutY();
        double width = shape.getBoundsInLocal().getWidth();
        double height = shape.getBoundsInLocal().getHeight();
        double leftCornerY = topMinY-(height/2);
        double leftCornerX = topMinX;
        double MidX = leftCornerX+(leftCornerX/2);
        double MidY = leftCornerY;
        double rightCornerX = topMinX+width;
        double rightCornerY = MidY;
        return  rightCornerY;
    }
    public double calculateDistanceToLeftCorner(Shape object1, Shape object2)
    {
        // Funktioniert
        double centerXObject1 = object1.getBoundsInParent().getCenterX();
        double centerYObject1 = object1.getBoundsInParent().getCenterY();
        //Funktioniert
        double centerLeftXObject2 = getLeftCornerX(object2);
        double centerLeftYObject2 = getLeftCornerY(object2);
        //Funktioniert
        Point2D point1 = new Point2D(centerXObject1,centerYObject1);
        Point2D point2 = new Point2D(centerLeftXObject2, centerLeftYObject2);
        double distance = point1.distance(point2);
        System.out.println(ANSI_CYAN+"Object "+object2.getId()+"Center X: "+centerLeftXObject2+" Center Y: "+centerLeftYObject2+" Distance: "+distance+ANSI_RESET);
        return 0;
    }
}
