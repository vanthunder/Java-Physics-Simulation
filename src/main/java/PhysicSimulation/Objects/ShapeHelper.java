package PhysicSimulation.Objects;

import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

public class ShapeHelper
{
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
}
