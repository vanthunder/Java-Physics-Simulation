package PhysicSimulation.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * @author Marvin Schubert
 * @version 0.1
 */
public class GUIController implements Initializable
{
    public Button startBtn;
    public Button pauseBtn;
    public Button resetBtn;
    public Slider speedSlider;
    public TextField speedInput;
    public Slider runningTimeSlider;
    public TextField runningTimeInput;
    public Slider startingAngleSlider;
    public TextField startAngleInput;
    public Slider elasticitySlider;
    public TextField elasticityInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public void startBtnPress(ActionEvent actionEvent)
    {
    }

    public void pauseBtnPress(ActionEvent actionEvent)
    {
    }

    public void resetBtnPress(ActionEvent actionEvent)
    {
    }
}
