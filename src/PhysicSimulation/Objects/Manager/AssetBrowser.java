package PhysicSimulation.Objects.Manager;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
    public GridPane gridPane = new GridPane();
    public Image image = new Image("PhysicSimulation\\Ressources\\Images\\kugel.png");
    public Button createCircleBtn = new Button("",new ImageView(image));
    public boolean selected = false;

    public AssetBrowser()
    {
        super();
        this.setWidth(width);
        this.setHeight(height);
        this.setMinWidth(width);
        this.setMinHeight(height);
        debugLabel.setText("Asset Browser");
        this.getChildren().add(debugLabel);
        this.getChildren().set(0, debugLabel).setLayoutX(this.getWidth()/2);
        gridPane.add(createCircleBtn, 1, 1);
        this.getChildren().add(gridPane);
        gridPane.getStyleClass().add("gridpane");
        this.getStyleClass().add("AssetBrowser");
    }

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



}
