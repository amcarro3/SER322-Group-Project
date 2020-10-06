package gym.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 * Controls the database settings page.
 * @author Adam
 */
public class DatabaseDriverController extends Controller implements Initializable {
    @FXML 
    private MenuBar menu;
    
    @FXML
    private TextField driver;
   
//    @FXML
//    private void setFocusDatabase(){
//        //doesnt work
//        //move to next text field on action
//        Platform.runLater(()->database.requestFocus());
//    }
    
    
    @FXML
    private void goToLoginButton(ActionEvent event) throws IOException{
        //check that driver feild is not empty
        if(!(driver.getText().trim().isEmpty())){
            //load new database object
            if(this.getDatabase()==null) this.setDatabase(new Database());
            try{
                //try to set driver 
                this.getDatabase().setDriver(driver.getText().trim());
                //check if database url is filled
                                 
                //go to login scene
                switchScene(event, "Login.fxml");              

            }
            //catch exceptions and show as a popup error
            catch(Exception e){
                e.printStackTrace();
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText(e.toString());
                a.setHeaderText(null);
                a.showAndWait();
            }
        }else{
            //alert the user if the driver field is empty
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("No Driver entered. Please enter the driver");
            a.setHeaderText(null);
            a.showAndWait();
        }        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       }    
    
}
