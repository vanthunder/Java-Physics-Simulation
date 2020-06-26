package PhysicSimulation.Physics;

import javafx.scene.shape.Shape;

public class CalcHelper
{
    private double ground = 800;

    // This method calculates the distance from a shape to the ground
    public double distanceToGround(Shape shape)
    {
        double currentPosition = shape.getBoundsInLocal().getCenterY();
        double distance = ground - currentPosition;
        return distance;
    }
}
