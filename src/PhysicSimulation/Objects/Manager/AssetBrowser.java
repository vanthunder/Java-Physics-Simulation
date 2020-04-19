package PhysicSimulation.Objects.Manager;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
/**
 * @author Marvin Schubert
 * @version 0.1
 */
public class AssetBrowser extends Pane
{
    int width = 1200;
    int height = 100;
    public Label debugLabel = new Label();

    public AssetBrowser()
    {
        super();
        this.setWidth(width);
        this.setHeight(height);
        this.setMinWidth(width);
        this.setMinHeight(height);
        this.setStyle("-fx-background-color: #00ffd3");
        debugLabel.setText("Asset Browser");
        this.getChildren().add(debugLabel);
        this.getChildren().set(0, debugLabel).setLayoutX(this.getWidth()/2);
    }
}
