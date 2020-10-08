package gym.fxml;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * FXML Controller class
 * Controls the query GUI
 * @author Adam
 */
public class QueryPageController extends Controller implements Initializable {
    
    @FXML private ComboBox<DropDownItem> cRoom, cTeachers;
    @FXML private Button addEquipt, addEmp;
    @FXML private CheckBox eTrainCheck, eSupCheck, eSupCheckAdd;
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
    @FXML private TextField eStartTime;
    @FXML private TextField ePnum;
    @FXML private Button viewEmp;
    @FXML private TextField eEndTimeGet;
    @FXML private MenuItem databaseDriver;
    @FXML private Button viewClass;
    @FXML private Button viewEquipt;
    @FXML private ComboBox<DropDownItem> eTeaches;    
    @FXML private ComboBox<DropDownItem> cRoomSet;    
    @FXML private ComboBox<DropDownItem> eID, equID;    
    @FXML private TextField efnameUpdate, elnameUpdate, essnUpdate, ePnumUpdate, eStartTimeUpdate, eEndTimeUpdate;    
    @FXML private TextField cNameUpdate, cTimeUpdate;    
    @FXML private ComboBox<DropDownItem> cRoomUpdate, cID, eSID;
    @FXML private ComboBox<String> skills;
    @FXML private ComboBox<DropDownItem> equRoomUpdate, eIDTeach, tID, tClass;
    @FXML private ComboBox<String> equTypeUpdate, equStatusUpdate;
    @FXML private TextField equMaintDateUpdate;
    @FXML private CheckBox supCheckUpdate;
    
    @FXML public void setDatabase(){
        setDatabase(data);
        getEmpByID(true);
        getClassByID(true);
        getEquByID(true);
        getSkillsByID(true);
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
            eID.getItems().clear();
            equID.getItems().clear();
            equTypeUpdate.getItems().clear();
            equRoomUpdate.getItems().clear();
            equStatusUpdate.getItems().clear();
            cID.getItems().clear();
            cRoomUpdate.getItems().clear();
            eSID.getItems().clear();
            skills.getItems().clear();
            tID.getItems().clear();
            tClass.getItems().clear();
            
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
            equTypeUpdate.setItems(FXCollections.observableList(data.getEquTypes(true)));
            equRoomUpdate.setItems(rooms);
            equStatusUpdate.setItems(FXCollections.observableList(data.getEquStatuses(true)));
            cID.setItems(FXCollections.observableList(data.getClassByIDs()));
            cRoomUpdate.setItems(rooms);
            eSID.setItems(FXCollections.observableList(data.getEmpByIDs()));
            tID.setItems(trainers);
            tClass.setItems(FXCollections.observableList(data.getClassByIDs()));
            
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
            eStartTimeUpdate.setText(emp.getStart_time());
            eEndTimeUpdate.setText(emp.getEnd_time());
            supCheckUpdate.setSelected(data.isSuper(emp_id));
            
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
            setComboBox(equTypeUpdate, equ.getType(), true);
            setComboBox(equRoomUpdate, equ.getRoom());
            setComboBox(equStatusUpdate, equ.getStatus(), true);
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
    
    @FXML private void getEmpByID(Boolean t){
        try{
            
            DropDownItem id = eID.getValue();
            if(id==null) {
                eID.setValue(id);
                efnameUpdate.clear();
                elnameUpdate.clear();
                essnUpdate.clear();
                ePnumUpdate.clear();
                eStartTimeUpdate.clear();
                eEndTimeUpdate.clear();
                supCheckUpdate.setSelected(false);
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
            eStartTimeUpdate.setText(emp.getStart_time());
            eEndTimeUpdate.setText(emp.getEnd_time());
            supCheckUpdate.setSelected(data.isSuper(emp_id));
            
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
    @FXML private void getClassByID(Boolean t){
        try{
            
            DropDownItem id = cID.getValue();
            if(id==null) {
                cNameUpdate.clear();
                cTimeUpdate.clear();
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
    @FXML private void getEquByID(Boolean t){
        try{
            
            DropDownItem id = equID.getValue();
            if(id==null) {
                equMaintDateUpdate.clear();
                return;
            }
            EquipmentRecord equ = data.getEquByID(id.getId());
            setDatabase(data);
            equID.setValue(id);
            setComboBox(equTypeUpdate, equ.getType(), true);
            setComboBox(equRoomUpdate, equ.getRoom());
            setComboBox(equStatusUpdate, equ.getStatus(), true);
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
    @FXML private void getSkillsByID(Boolean t){
        try{
            
            DropDownItem id = eSID.getValue();
            if(id==null) {
                skills.getItems().clear();
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
    private void setComboBox(ComboBox<String> box, String string, Boolean t){
        ObservableList<String> items = box.getItems();
        for(int i=0; i<items.size();i++){
            if(items.get(i).compareTo(string)==0){
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
    private void popup(String message){
        Alert a = new Alert(Alert.AlertType.NONE);
        a.getDialogPane().getButtonTypes().add(ButtonType.OK);
        a.setTitle(null);
        TextArea textArea = new TextArea();
        textArea.setPrefWidth(1000);
        textArea.setPrefHeight(500);
        textArea.setFont(Font.font("Monospace"));
        textArea.setText(message);
        a.getDialogPane().setContent(textArea);
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
    
    @FXML public void getClasses(ActionEvent event){
        try{
            String name = null;
            String time = null;
            String room = null;
            String teachID = null;
            if(!cNameGet.getText().trim().isEmpty()) name = cNameGet.getText().trim();
            if(!cTimeGet.getText().isEmpty()&&cTimeGet.getText().matches("\\d{2}:\\d{2}:\\d{2}")) time = cTimeGet.getText();
            if(cRoom.getValue()!=null) room = cRoom.getValue().getName();
            if(cTeachers.getValue()!=null) teachID = Integer.toString(cTeachers.getValue().getId());
            List<ClassRecord> classes = data.getClasses(name, time, room, teachID);
            String result = String.format("%5s %15s %15s %15s", "ID", "Name", "Time", "Room");
            for (ClassRecord classe : classes) {
                result = result.concat(classe.toString());
            }
            popup(result);
        }catch(Exception e){
            e.printStackTrace();
            error(e.getMessage());
        }
    }
    @FXML public void addClass(){
        try{
            String name = cNameSet.getText().trim();
            String time = cTimeSet.getText().trim();
            if(name.isEmpty()) {
                error("Please enter a Class name");
                return;
            }
            if(!time.matches("\\d{2}:\\d{2}:\\d{2}")) {
                error("Please enter the Time as HH:MM:SS");
                return;
            }
            if(cRoomSet.getValue()==null) {
                error("Please select a Room");
                return;
            }
            String room = cRoomSet.getValue().getName();
            String id = Integer.toString(1+data.getMaxIdClass());
            data.addClass(id, name, time, room);
            inform("Class added Successfully");
            setDatabase(data);
        }catch(Exception e){
            e.printStackTrace();
            error(e.getMessage());
        }
        
    }
    @FXML public void updateClass(){
        try{
            if(cID.getValue()==null){
                error("Select Class ID");
                return; 
            }
            String id = Integer.toString(cID.getValue().getId());
            String name = null;
            String time = null;
            String room = null;
            String teachID = null;
            if(cNameUpdate.getText().trim().isEmpty()){
                error("Enter Name");
                return;
            } 
            if(cTimeUpdate.getText().trim().isEmpty()){
                error("Enter Time");
                return; 
            } 
            if(cRoomUpdate.getValue()==null) {
                error("SelectRoom");
                return; 
            }
            name = cNameUpdate.getText().trim();
            time = cTimeUpdate.getText().trim();
            room = cRoomUpdate.getValue().getName();
            data.updateClass(id, name, time, room);
            inform("Class Updated Successfully");
            setDatabase();
        }catch(Exception e){
            e.printStackTrace();
            error(e.getMessage());
        }
    }
    @FXML public void addClassTrainer(){
        try{
            if(tID.getValue()==null){
                error("Please select Trainer");
                return;
            }
            if(tClass.getValue()==null){
                error("Please Select Class");
                return;
            }
            data.updateConducts(Integer.toString(tID.getValue().getId()), Integer.toString(tClass.getValue().getId()), true);
            inform("Trainer Successfully added to class");
            setDatabase();
        }catch(Exception e){
           e.printStackTrace();
            error(e.getMessage()); 
        }
    }
    @FXML public void deleteClassTrainer(){
        try{
            if(tID.getValue()==null){
                error("Please select Trainer");
                return;
            }
            if(tClass.getValue()==null){
                error("Please Select Class");
                return;
            }
            data.updateConducts(Integer.toString(tID.getValue().getId()), Integer.toString(tClass.getValue().getId()), false);
            inform("Trainer successfully removed from class");
            setDatabase();
        }catch(Exception e){
           e.printStackTrace();
            error(e.getMessage()); 
        }
    }
    
    @FXML public void updateEquipment(){
        try{
            String type;
            String status;
            if(equID.getValue()==null){
                error("Select equipmentId");
                return;
            }
            if(equTypeUpdate.getValue()==null){
                    error("Please select or enter Equipment type");
                    return;
            }else{
                type=equTypeUpdate.getValue();
            }
            if(equRoomUpdate.getValue()==null){
                error("Please select a room");
                return;
            }
            if(equStatusUpdate.getValue()==null){
                    error("Please select or enter Equipment status");
                    return;
            }else{
                status=equStatusUpdate.getValue();
            }
            String mDate = equMaintDateUpdate.getText().trim();
            if(mDate.isEmpty()){
                error("Please enter the next Maintanance Date");
                return;
            }
            if(!mDate.matches("\\d{4}-\\d{2}-\\d{2}")){
                error("Please enter the date in the format YYYY-MM-DD");
                return;
            }
            String id = Integer.toString(equID.getValue().getId());
            String room = equRoomUpdate.getValue().getName();
            data.updateEquip(id, type, room, mDate, status);
            inform("Updated Successfully"); 
            setDatabase();
        }catch(Exception e){
            e.printStackTrace();
            error(e.getMessage());
        }
    }
    @FXML public void updateEmployee(){
        try{
            String id, fname, lname, ssn, pnum, start, end;
            if(eID.getValue()==null){
                error("Select equipmentId");
                return;
            }else{
                id=Integer.toString(eID.getValue().getId());
            }
            if((fname=efnameUpdate.getText().trim()).isEmpty()){
                error("Enter a First Name");
                return;
            }
            if((lname=elnameUpdate.getText().trim()).isEmpty()){
                error("Enter a Last Name");
                return;
            }if((ssn=essnUpdate.getText().trim()).isEmpty()){
                error("Enter a SSN");
                return;
            }
            if((pnum=ePnumUpdate.getText().trim()).isEmpty()){
                error("Enter a phone number");
                return;
            }
            if((start=eStartTimeUpdate.getText().trim()).isEmpty()){
                error("Enter");
                return;
            }
            if((end=eEndTimeUpdate.getText().trim()).isEmpty()){
                error("Enter");
                return;
            }
            if(!start.matches("\\d{2}:\\d{2}:\\d{2}")||!end.matches("\\d{2}:\\d{2}:\\d{2}")){
                error("Enter the time in the format HH:MM:SS");
                return;
            }
            Boolean sup = supCheckUpdate.isSelected();
            data.updateEmployee(id, fname, lname, ssn, pnum, start, end, sup);
            inform("Updated Successfully"); 
            setDatabase();
        }catch(Exception e){
            e.printStackTrace();
            error(e.getMessage());
        }
    }
    @FXML public void addEmployee(){
        try{
            String id = Integer.toString(data.getMaxIdEmp()+1);
            String fname = efname.getText().trim();
            String lname = elname.getText().trim();
            String ssn = essn.getText().trim();
            String phone = ePnum.getText().trim();
            String start = eStartTime.getText().trim();
            String end = eEndTime.getText().trim();
            String classe = null;
            if(fname.isEmpty()||lname.isEmpty()||ssn.isEmpty()||phone.isEmpty()||start.isEmpty()||end.isEmpty()){
                error("Please enter all feilds to add an employee");
                return;
            }
            if(!ssn.matches("\\d{9}")){
                error("Please enter a 9 digit number for the SSN");
                return;
            }
            if(!phone.matches("\\d{10}")){
                error("Please enter a 10 digit number for the Phone number");
                return;
            }
            if((start.matches("\\d{2}:\\d{2}:\\d{2}")&&end.matches("\\d{2}:\\d{2}:\\d{2}"))){
                error("Please enter the start and end times as HH:MM:SS");
                return;
            }
            if(eTeachesAdd.getValue()!=null){
                classe = Integer.toString(eTeachesAdd.getValue().getId());
            }
            Boolean sup = eSupCheckAdd.isSelected();
            data.addEmployee(id, fname, lname, ssn, phone, start, end, classe, sup);
            inform("Employee added Successfully");
            setDatabase();
        }catch(Exception e){
            e.printStackTrace();
            error(e.getMessage());
        }
    }
    @FXML public void getEmployees(){
        try{
            String start=null;
            String end=null;
            String class_name=null;
            Boolean superv;
            superv=eSupCheck.isSelected();
            if((start=eStartTimeGet.getText().trim()).isEmpty()||!start.matches("\\d{2}:\\d{2}:\\d{2}")) start=null;
            if((end=eEndTimeGet.getText().trim()).isEmpty()||!end.matches("\\d{2}:\\d{2}:\\d{2}")) end=null;
            if(eTeaches.getValue()!=null) class_name=eTeaches.getValue().getName();
            System.out.println(class_name);
            List<EmployeeRecord> list = data.getEmployees(start, end, class_name, superv);
            String result = String.format("%5s %15s %15s %10s %11s %11s %11s", "Emp_ID", "FirstName", "LastName", "SSN", "Phone", "Start Time", "End Time");
            for(EmployeeRecord emp: list){
                result = result.concat(emp.toString());
            }
            popup(result);
            setDatabase();
        }catch(Exception e){
            e.printStackTrace();
            error(e.getMessage());
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }    
    
}
