package gym.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class ClassTableController extends Controller implements Initializable {

    @FXML
    private TableColumn<ClassRecord, String> name,cTime, room;
    
    @FXML
    private TableColumn<ClassRecord, String> id;
    
    @FXML
    private TableView<ClassRecord> table;
    
    public void setTable(String cName, String time, String room_name, int emp_id){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        room.setCellValueFactory(new PropertyValueFactory<>("room"));
        
        table.getItems().setAll(data.getClasses(cName, time, room_name, emp_id));
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
