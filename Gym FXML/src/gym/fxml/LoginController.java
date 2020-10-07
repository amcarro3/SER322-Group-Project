package gym.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Adam
 */
public class LoginController extends Controller implements Initializable {
    @FXML
    private MenuBar menu;
    @FXML
    private TextField databaseURL;
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    
    
    @FXML
    private void login(ActionEvent event){
        try{
            String URL = databaseURL.getText().trim();
            String userName = user.getText().trim();
            String userPassword = password.getText().trim();
            if(userName.compareTo("")!=0 && userPassword.compareTo("")!=0 && URL.compareTo("")!=0){
                this.getDatabase().login(URL, userName, userPassword);
                switchScene(event, "QueryPage.fxml");
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Please enter a URL, user and password");
                a.setHeaderText(null);
                a.showAndWait();
            }           
            
        }catch(Exception e){
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText(e.toString());
            a.setHeaderText(null);
            a.showAndWait();
        }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
