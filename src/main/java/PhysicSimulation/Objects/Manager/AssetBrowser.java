package PhysicSimulation.Objects.Manager;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @author Marvin Schubert
 * @version 0.4
 */
public class AssetBrowser extends Pane
{
    int width = 1200;
    int height = 100;
    public Label debugLabel = new Label();
    public GridPane gridPane = new GridPane();
    public Image image = new Image("/Images/kugel.png");
    public Image planeImage = new Image("/Images/Plane.png");
    public Image inclinedPlaneImage = new Image("/Images/Inclined_Plane.png");
    public Button createCircleBtn = new Button("", new ImageView(image));
    public Button createPlaneBtn = new Button("", new ImageView(planeImage));
    public Button createInclinedPlaneBtn = new Button("", new ImageView(inclinedPlaneImage));
    public boolean selected = false;

    public AssetBrowser()
    {
        super();
        this.setWidth(width);
        this.setHeight(height);
        this.setMinWidth(width);
        this.setMinHeight(height);
        //debugLabel.setText("Asset Browser");
        this.getChildren().add(debugLabel);
        this.getChildren().set(0, debugLabel).setLayoutX(this.getWidth() / 2);
        gridPane.add(createCircleBtn, 1, 1);
        gridPane.add(createPlaneBtn, 2, 1);
        gridPane.add(createInclinedPlaneBtn, 3, 1);
        this.getChildren().add(gridPane);
        gridPane.getStyleClass().add("gridpane");
        this.getStyleClass().add("AssetBrowser");
    }

    // Boolean that saves the state of an selected item

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public Button getCreateCircleBtn()
    {
        return createCircleBtn;
    }

    public Button getCreatePlaneBtn()
    {
        return createPlaneBtn;
    }

    public Button getCreateInclinedPlaneBtn()
    {
        return createInclinedPlaneBtn;
    }


}
