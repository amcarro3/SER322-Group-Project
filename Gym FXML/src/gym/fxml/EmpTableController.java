package gym.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class EmpTableController extends Controller implements Initializable {
    
    public void setTable(String startTime, String EndTime, Boolean Supervisors, Boolean Trainers, DropDownItem className){
        
    }
    
    @FXML
    public void back(ActionEvent event){
        this.switchScene(event, "QueryPage.fxml");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
