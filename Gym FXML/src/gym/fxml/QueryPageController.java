package gym.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;

/**
 * FXML Controller class
 * Controls the query GUI
 * @author Adam
 */
public class QueryPageController extends Controller implements Initializable {
    
   @FXML
   private MenuBar menu;
    
    private void logoutAndSwitch(ActionEvent event, String fxml){
        //closes connections to the database
        this.getDatabase().logout();
        
        //switch to new scene
        switchScene(event, fxml);
    }
    
    @FXML
    private void logout(ActionEvent event){
       logoutAndSwitch(event, "Login.fxml");
    }
    
    @FXML
    private void gotoDriver(ActionEvent event){
        logoutAndSwitch(event, "DatabaseSettings.fxml");
    }   
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
