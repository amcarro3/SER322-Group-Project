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
    
    @FXML private ComboBox<DropDownItem> cRoom, cTeachers;
    @FXML private Button addEquipt, addEmp;
    @FXML private CheckBox eTrainCheck, eSupCheck;
    @FXML private TextField efname, elname, cNameGet, cTimeSet;
    @FXML private ComboBox<DropDownItem> equStatus, equRoom;
    @FXML private DatePicker equMaint;
    @FXML private TextField cNameSet;
    @FXML private TextField cTimeGet;
    @FXML private Button addClass;
    @FXML private TextField eStartTimeGet;
    @FXML private TextField eEndTime;
    @FXML private ComboBox<DropDownItem> equType;
    @FXML private TextField essn;
    @FXML private ComboBox<DropDownItem> eTeachesAdd;
    @FXML private MenuBar menu;
    @FXML private TextField eStarTIme;
    @FXML private TextField ePnum;
    @FXML private Button viewEmp;
    @FXML private TextField eEndTimeGet;
    @FXML private MenuItem databaseDriver;
    @FXML private Button viewClass;
    @FXML private Button viewEquipt;
    @FXML private ComboBox<DropDownItem> eTeaches;    
    @FXML private ComboBox<DropDownItem> cRoomSet;    
    @FXML private ComboBox<DropDownItem> eID, equID;    
    @FXML private TextField efnameUpdate, elnameUpdate, essnUpdate, ePnumUpdate, eStarTImeUpdate, eEndTimeUpdate;    
    @FXML private TextField cNameUpdate, cTimeUpdate;    
    @FXML private ComboBox<DropDownItem> cRoomUpdate, cID, eSID;
    @FXML private ComboBox<String> skills;
    @FXML private ComboBox<DropDownItem> equTypeUpdate, equRoomUpdate, equStatusUpdate;
    @FXML private TextField equMaintDateUpdate;
    
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
            eID.getItems().clear();
            equID.getItems().clear();
            equTypeUpdate.getItems().clear();
            equRoomUpdate.getItems().clear();
            equStatusUpdate.getItems().clear();
            cID.getItems().clear();
            cRoomUpdate.getItems().clear();
            eSID.getItems().clear();
            skills.getItems().clear();
            
            eTeaches.setItems(classes);
            eTeachesAdd.setItems(FXCollections.observableList(data.getClassIds()));
            cRoom.setItems(rooms);
            cTeachers.setItems(trainers);
            cRoomSet.setItems(rooms);
            equType.setItems(FXCollections.observableList(data.getEquTypes()));
            equRoom.setItems(rooms);
            equStatus.setItems(FXCollections.observableList(data.getEquStatuses()));
            eID.setItems(FXCollections.observableList(data.getEmpByIDs()));
            equID.setItems(FXCollections.observableList(data.getEquByIDs()));
            equTypeUpdate.setItems(FXCollections.observableList(data.getEquTypes()));
            equRoomUpdate.setItems(rooms);
            equStatusUpdate.setItems(FXCollections.observableList(data.getEquStatuses()));
            cID.setItems(FXCollections.observableList(data.getClassByIDs()));
            cRoomUpdate.setItems(rooms);
            eSID.setItems(FXCollections.observableList(data.getEmpByIDs()));
            
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
    @FXML private void getEmpByID(){
        try{
            
            DropDownItem id = eID.getValue();
            if(id==null) {
                inform("Please Select Employee");
                return;
            }
            int emp_id = id.getId();
            EmployeeRecord emp = data.getEmpByID(emp_id);
            setDatabase(data);
            eID.setValue(id);
            efnameUpdate.setText(emp.getFname());
            elnameUpdate.setText(emp.getLname());
            essnUpdate.setText(Integer.toString(emp.getSsn()));
            ePnumUpdate.setText(emp.getPnumb());
            eStarTImeUpdate.setText(emp.getStart_time());
            eEndTimeUpdate.setText(emp.getEnd_time());
            
        }catch(Exception e){
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText(e.getMessage());
            a.setHeaderText(null);
            a.showAndWait();
            System.exit(0);
        }  
        
    }
    @FXML private void getClassByID(){
        try{
            
            DropDownItem id = cID.getValue();
            if(id==null) {
                inform("Please Select Employee");
                return;
            }
            int class_id = id.getId();
            ClassRecord cls = data.getClassByID(class_id);
            setDatabase(data);
            cID.setValue(id);
            cNameUpdate.setText(cls.getName());
            cTimeUpdate.setText(cls.getTime());
            setComboBox(cRoomUpdate, cls.getRoom());
            
        }catch(Exception e){
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText(e.getMessage());
            a.setHeaderText(null);
            a.showAndWait();
            System.exit(0);
        }  
        
    }
    @FXML private void getEquByID(){
        try{
            
            DropDownItem id = equID.getValue();
            if(id==null) {
                inform("Please Select Employee");
                return;
            }
            int emp_id = id.getId();
            EquipmentRecord equ = data.getEquByID(id.getId());
            setDatabase(data);
            equID.setValue(id);
            setComboBox(equTypeUpdate, equ.getType());
            setComboBox(equRoomUpdate, equ.getRoom());
            setComboBox(equStatusUpdate, equ.getStatus());
            equMaintDateUpdate.setText(equ.getDate());            
        }catch(Exception e){
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText(e.getMessage());
            a.setHeaderText(null);
            a.showAndWait();
            System.exit(0);
        }      
    }
    @FXML private void getSkillsByID(){
        try{
            
            DropDownItem id = eSID.getValue();
            if(id==null) {
                inform("Please Select Employee");
                return;
            }
            skills.setItems(FXCollections.observableList(data.getSkillsByIDs(id.getId())));                     
        }catch(Exception e){
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText(e.getMessage());
            a.setHeaderText(null);
            a.showAndWait();
            System.exit(0);
        }      
    }
     
    private void setComboBox(ComboBox<DropDownItem> box, int id){
        ObservableList<DropDownItem> items = box.getItems();
        for(int i=0; i<items.size();i++){
            if(items.get(i).getId()==id){
                box.setValue(items.get(i));
                return;
            }
        }
    }
    private void setComboBox(ComboBox<DropDownItem> box, String string){
        ObservableList<DropDownItem> items = box.getItems();
        for(int i=0; i<items.size();i++){
            if(items.get(i).getName().compareTo(string)==0){
                box.setValue(items.get(i));
                return;
            }
        }
    }
    private void inform(String message){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(null);
        a.setContentText(message);
        a.setHeaderText(null);
        a.showAndWait();
    }
    private void error(String message){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(null);
        a.setContentText(message);
        a.setHeaderText(null);
        a.showAndWait();
    }
    
    @FXML private void deleteEmployee(){
        try{
            data.deleteEmployee(eID.getValue().getId());
            setDatabase(data);
        }catch(Exception e){
            e.printStackTrace();
            error(e.getMessage());
        }
    }   
    @FXML private void deleteClass(){
        try{
            data.deleteClass(cID.getValue().getId());
            setDatabase(data);
        }catch(Exception e){
            e.printStackTrace();
            error(e.getMessage());
        }
    }
    @FXML private void deleteEquip(){
        try{
            data.deleteEquip(equID.getValue().getId());
            setDatabase(data);
        }catch(Exception e){
            e.printStackTrace();
            error(e.getMessage());
        }
    }
    @FXML private void deleteSkill(){
        try{
            if(eSID.getValue()==null||skills.getValue()==null) {
                error("Please select employee, skill combo");
                return;
            }
            data.deleteSkill(eSID.getValue().getId(), skills.getValue());
            getSkillsByID();
        }catch(Exception e){
            e.printStackTrace();
            error(e.getMessage());
        }
    }
    public void getEmployees(ActionEvent event){
       // data.getEmployees(eStartTimeGet.getText(), eEndTimeGet.getText(), eSupCheck.isSelected(),eTrainCheck.isSelected(), eTeaches.getValue());

        
        

        }
    
    @FXML
    public void getClasses(ActionEvent event){
        data.getClasses(cTimeGet.getText(), cTimeGet.getText(), cRoom.getValue().getName(), cTeachers.getValue().getId());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }    
    
}
