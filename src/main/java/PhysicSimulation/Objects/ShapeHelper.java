package PhysicSimulation.Objects;

import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

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
                if (oldRotation >= 360)
                {
                    angle = 0;
                } else if (oldRotation <= -360)
                {
                    angle = 0;
                }
                ((Rotate) transform).setAngle(angle);
            }
        }
    }
}
