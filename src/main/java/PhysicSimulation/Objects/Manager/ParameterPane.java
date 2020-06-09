package PhysicSimulation.Objects.Manager;

/*
 *   @author Marvin Schubert
 *   @version 0.2
 */

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

// This class inits the the parameter pane
public class ParameterPane extends GridPane
{
    public Font HEADLINE_FONT = new Font("System", 18);
    public Font DESCRIPTION_FONT = new Font("System", 14);
    public GridPane gridPane = new GridPane();
    public Label headline = new Label("Kreis");
    public Label xPositionLabel = new Label("X Position:");
    public TextField xPositionTextField = new TextField();
    public Label yPositionLabel = new Label("Y Position:");
    public TextField yPositionTextField = new TextField();
    public Label radiusLabel = new Label("Radius:");
    public TextField radiusTextField = new TextField();
    public Label massLabel = new Label("Gewicht: ");
    public TextField massTextField = new TextField();
    public Label directionLabel = new Label("Richtung: ");
    public TextField directionTextField = new TextField();
    public Label velocityLabel = new Label("Geschwindigkeit: ");
    public TextField velocityTextField = new TextField();
    public Button createBtn = new Button("Create");

    public ParameterPane ()
    {
        super();
        initGridPane();
    }

    public void initGridPane()
    {

        headline.setFont(HEADLINE_FONT);

        xPositionLabel.setFont(DESCRIPTION_FONT);
        xPositionTextField.setPromptText("X");
        xPositionTextField.setMaxSize(62, 8);

        yPositionLabel.setFont(DESCRIPTION_FONT);
        yPositionTextField.setPromptText("Y");
        yPositionTextField.setMaxSize(62, 8);

        radiusLabel.setFont(DESCRIPTION_FONT);
        radiusTextField.setPromptText("Radius");
        radiusTextField.setMaxSize(62, 8);

        massLabel.setFont(DESCRIPTION_FONT);
        massTextField.setPromptText("Gewicht");
        massTextField.setMaxSize(62, 8);

        directionLabel.setFont(DESCRIPTION_FONT);
        directionTextField.setPromptText("Richtung");
        directionTextField.setMaxSize(62, 8);

        velocityLabel.setFont(DESCRIPTION_FONT);
        velocityTextField.setPromptText("Geschwindigkeit");
        velocityTextField.setMaxSize(62, 8);

        this.setMinWidth(400);
        this.setHgap(25);
        this.setVgap(15);
        this.add(headline, 1, 0);
        this.add(xPositionLabel, 0, 1);
        this.add(xPositionTextField, 1,1);
        this.add(yPositionLabel, 0, 2);
        this.add(yPositionTextField, 1, 2);
        this.add(radiusLabel, 0, 3);
        this.add(radiusTextField, 1, 3);
        this.add(massLabel, 0, 4);
        this.add(massTextField, 1, 4);
        this.add(directionLabel, 0, 5);
        this.add(directionTextField, 1, 5);
        this.add(velocityLabel, 0, 6);
        this.add(velocityTextField, 1, 6);
        this.add(createBtn, 0, 7);
        this.setStyle("-fx-background-color: #afd6e3");
    }

    public TextField getxPositionTextField()
    {
        return xPositionTextField;
    }

    public TextField getyPositionTextField()
    {
        return yPositionTextField;
    }

    public TextField getRadiusTextField()
    {
        return radiusTextField;
    }

    public TextField getMassTextField()
    {
        return massTextField;
    }

    public TextField getDirectionTextField()
    {
        return directionTextField;
    }

    public TextField getVelocityTextField()
    {
        return velocityTextField;
    }

    public Button getCreateBtn()
    {
        return createBtn;
    }

    public void chooseParameterPane(String selection)
    {
        switch (selection)
        {
            case "sphere":
                headline.setText("Kugel");
                getRadiusTextField().setPromptText("Radius");
                radiusLabel.setText("Radius:");
                getMassTextField().setVisible(true);
                getDirectionTextField().setVisible(true);
                getVelocityTextField().setVisible(true);
                getMassTextField().setPromptText("Gewicht");
                massLabel.setText("Gewicht:");
                massLabel.setVisible(true);
                directionLabel.setVisible(true);
                velocityLabel.setVisible(true);
                break;
            case "plane":
                headline.setText("Gerade Bahn");
                getRadiusTextField().setPromptText("Breite");
                radiusLabel.setText("Breite:");
                getMassTextField().setVisible(false);
                getDirectionTextField().setVisible(false);
                getVelocityTextField().setVisible(false);
                massLabel.setVisible(false);
                directionLabel.setVisible(false);
                velocityLabel.setVisible(false);
                break;
            case "inclined_Plane":
                headline.setText("Schiefe Bahn");
                getRadiusTextField().setPromptText("Breite");
                radiusLabel.setText("Breite:");
                getMassTextField().setVisible(true);
                getMassTextField().setPromptText("Winkel");
                massLabel.setText("Winkel:");
                massLabel.setVisible(true);
                directionLabel.setVisible(false);
                velocityLabel.setVisible(false);
                getDirectionTextField().setVisible(false);
                getVelocityTextField().setVisible(false);
                break;
        }

    }
}
