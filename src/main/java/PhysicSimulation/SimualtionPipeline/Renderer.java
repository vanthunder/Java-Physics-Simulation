package PhysicSimulation.SimualtionPipeline;

import PhysicSimulation.Objects.Manager.AssetData;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

/**
 * @author Marvin Schubert
 * @version 0.4
 */
public class Renderer extends Pane
{
    // Instants the Simulation Loop
    // Variables for the width and height
    int width = 800;
    int height = 800;
    // Constructor of the renderer class
    public Renderer ()
    {
        super();
        this.setWidth(width);
        this.setHeight(height);
        this.getStyleClass().add("render-window");
        this.setMaxHeight(height);
        this.setMaxWidth(width);
        this.setMinHeight(height);
        this.setMinWidth(width);
        this.setFocusTraversable(true);
    }
}
