package gym.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * Controls the query GUI
 * @author Adam
 */
public class QueryPageController extends Controller implements Initializable {
    
    @FXML
    private ComboBox<DropDownItem> cRoom;

    @FXML
    private ComboBox<DropDownItem> equRoom;

    @FXML
    private Button addEquipt;

    @FXML
    private CheckBox eTrainCheck;

    @FXML
    private TextField efname;

    @FXML
    private TextField cNameGet;

    @FXML
    private TextField cTimeSet;

    @FXML
    private ComboBox<DropDownItem> equStatus;

    @FXML
    private TextField elname;

    @FXML
    private ComboBox<DropDownItem> cTeachers;

    @FXML
    private Button addEmp;

    @FXML
    private DatePicker equMaint;

    @FXML
    private TextField cNameSet;

    @FXML
    private TextField cTimeGet;

    @FXML
    private Button addClass;

    @FXML
    private TextField eStartTimeGet;

    @FXML
    private TextField eEndTime;

    @FXML
    private ComboBox<DropDownItem> equType;

    @FXML
    private CheckBox eSupCheck;

    @FXML
    private TextField essn;

    @FXML
    private ComboBox<DropDownItem> eTeachesAdd;

    @FXML
    private MenuBar menu;

    @FXML
    private TextField eStarTIme;

    @FXML
    private TextField ePnum;

    @FXML
    private Button viewEmp;

    @FXML
    private TextField eEndTimeGet;

    @FXML
    private MenuItem databaseDriver;

    @FXML
    private Button viewClass;

    @FXML
    private Button viewEquipt;

    @FXML
    private ComboBox<DropDownItem> eTeaches;
    
    @FXML
    private ComboBox<DropDownItem> cRoomSet;
    
     
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
    
    @Override
    public void setDatabase(Database data){
        List<DropDownItem> classList;
        List<DropDownItem> trainerList;
        List<DropDownItem> roomList;
        ObservableList<DropDownItem> classes;
        ObservableList<DropDownItem> trainers;
        ObservableList<DropDownItem> rooms;
        this.data = data;
        try{
            classList = data.getClasses();
            trainerList = data.getTrainers();
            roomList = data.getRooms();
            
            classes = FXCollections.observableList(classList);
            trainers = FXCollections.observableList(trainerList);
            rooms = FXCollections.observableList(roomList);
            
            
            eTeaches.getItems().clear();
            eTeachesAdd.getItems().clear();
            cRoom.getItems().clear();
            cTeachers.getItems().clear();
            cRoomSet.getItems().clear();
            equType.getItems().clear();
            equRoom.getItems().clear();
            equStatus.getItems().clear();
            
            
            eTeaches.setItems(classes);
            eTeachesAdd.setItems(FXCollections.observableList(data.getClassIds()));
            cRoom.setItems(rooms);
            cTeachers.setItems(trainers);
            cRoomSet.setItems(rooms);
            equType.setItems(FXCollections.observableList(data.getEquTypes()));
            equRoom.setItems(rooms);
            equStatus.setItems(FXCollections.observableList(data.getEquStatuses()));
        }
        catch (Exception e){
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText(e.getMessage());
            a.setHeaderText(null);
            a.showAndWait();
            System.exit(0);
        }
    } 
    
    
    @FXML
    public void getEmployees(ActionEvent event){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EmpTable.fxml"));
        try{loader.load();}catch(IOException ioe){ioe.printStackTrace();}
        Parent parent = loader.getRoot();

        //pass databse object to Controller
        EmpTableController con = loader.getController();
        con.setDatabase(data);
        con.setTable(eStartTimeGet.getText(), eEndTimeGet.getText(), eSupCheck.isSelected(),eTrainCheck.isSelected(), eTeaches.getValue());

        //get main stage
        Stage window;
        if(event.getTarget() instanceof MenuItem){
            window = (Stage) ((MenuItem) event.getTarget()).getParentPopup().getOwnerWindow();
        }else{
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }
        

        //set stage to login
        Scene scene = new Scene(parent);
        window.setScene(scene);
        window.show();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }    
    
}
