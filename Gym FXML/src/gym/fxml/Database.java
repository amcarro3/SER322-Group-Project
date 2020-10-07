package gym.fxml;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 * Controls database connections and queries
 * @author Adam
 */
public class Database {
    //SQL variables
    private static java.sql.Connection conn = null;    
    private static PreparedStatement pstmt = null;
    private static Statement stmt = null;    
    private static ResultSet rs = null;
    
    private String url = null;
    
    //remeber to uncommnet
    public void setDriver(String driver) throws ClassNotFoundException{
        //comment for IDE Testing
        Class.forName(driver);        
    }
    
    public void login(String url, String user, String password)throws Exception{
        conn = DriverManager.getConnection(url, user, password);
        conn.setAutoCommit(false);
    }
    
    public void logout(){
        closeConnection();
    }
    
    private void closeConnection(){
        try {
            if (rs != null){
                    rs.close();
                    //System.out.println("rs closed");
            }
            if (stmt != null){
                    stmt.close();
                    //System.out.println("stmt closed");
            }
            if (pstmt != null){
                    pstmt.close();
                    //System.out.println("pstmt closed");
            }
            if (conn != null){
                    conn.rollback();
                    conn.close();
                    //System.out.println("conn closed");
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    public List<DropDownItem> getEquTypes() throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("select distinct equip_type "
                             + "from equipment");
        ArrayList<DropDownItem> classes = new ArrayList<DropDownItem>();                     
        while (rs.next()) {
           classes.add(new DropDownItem(rs.getString(1)));
        }
        rs.close();
        stmt.close();
        return classes;
    }
    
    public List<DropDownItem> getEquStatuses() throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("select distinct equip_status "
                             + "from equipment");
        ArrayList<DropDownItem> classes = new ArrayList<DropDownItem>();                     
        while (rs.next()) {
           classes.add(new DropDownItem(rs.getString(1)));
        }
        rs.close();
        stmt.close();
        return classes;
    }
    
    public List<DropDownItem> getClasses() throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("select distinct c.name "
                             + "from class as c");
        ArrayList<DropDownItem> classes = new ArrayList<DropDownItem>();                     
        while (rs.next()) {
           classes.add(new DropDownItem(rs.getString(1)));
        }
        rs.close();
        stmt.close();
        return classes;
    }
    
    public List<DropDownItem> getClassIds() throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("select distinct c.class_id, c.name "
                             + "from class as c");
        ArrayList<DropDownItem> classes = new ArrayList<DropDownItem>();                     
        while (rs.next()) {
           int id = rs.getInt(1);
           classes.add(new DropDownItem(id, Integer.toString(id)+" "+rs.getString(2)));
        }
        rs.close();
        stmt.close();
        return classes;
    }
    
    public List<DropDownItem> getTrainers()throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT e.emp_id, e.fname, e.lname "
                             + "FROM employee as e, trainer as t "
                             + "WHERE e.emp_id=t.emp_id");
        ArrayList<DropDownItem> trainers = new ArrayList<DropDownItem>();                     
        while (rs.next()) {
           trainers.add(new DropDownItem(rs.getInt(1), rs.getString(2) + " " + rs.getString(3)));
        }
        rs.close();
        stmt.close();
        return trainers;
    }
    
    public List<DropDownItem> getEmpByIDs()throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT e.emp_id, e.fname, e.lname "
                             + "FROM employee as e "
                             + "WHERE e.emp_id");
        ArrayList<DropDownItem> trainers = new ArrayList<DropDownItem>();                     
        while (rs.next()) {
           trainers.add(new DropDownItem(rs.getInt(1), Integer.toString(rs.getInt(1))+" "+rs.getString(2)+" "+rs.getString(3)));
        }
        rs.close();
        stmt.close();
        return trainers;
    }
    public List<DropDownItem> getEquByIDs()throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT e.equip_id, e.equip_type, e.room "
                             + "FROM equipment as e ");
        ArrayList<DropDownItem> equ = new ArrayList<DropDownItem>();                     
        while (rs.next()) {
           equ.add(new DropDownItem(rs.getInt(1), Integer.toString(rs.getInt(1))+" "+rs.getString(2)+" "+rs.getString(3)));
        }
        rs.close();
        stmt.close();
        return equ;
    }
    public List<DropDownItem> getClassByIDs()throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT c.class_id, c.name, c.time_slot "
                             + "FROM class as c ");
        ArrayList<DropDownItem> classes = new ArrayList<DropDownItem>();                     
        while (rs.next()) {
           classes.add(new DropDownItem(rs.getInt(1), Integer.toString(rs.getInt(1))+" "+rs.getString(2)+" "+rs.getString(3)));
        }
        rs.close();
        stmt.close();
        return classes;
    }
        
    public EmployeeRecord getEmpByID(int empNo)throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT e.emp_id, e.fname, e.lname, e.ssn, e.pnum, e.start_time, e.end_time "
                             + "FROM employee as e "
                             + "WHERE e.emp_id="+empNo);
        EmployeeRecord emp = new EmployeeRecord();                    
        while (rs.next()) {
           emp.setEmp_id(rs.getInt(1));
           emp.setFname(rs.getString(2));
           emp.setLname(rs.getString(3));
           emp.setSsn(rs.getInt(4));
           emp.setPnumb(rs.getString(5));
           emp.setStart_time(rs.getString(6));
           emp.setEnd_time(rs.getString(7));
        }
        rs.close();
        stmt.close();
        return emp;
    }
    public EquipmentRecord getEquByID(int equ_id)throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT e.equip_id, e.equip_type, e.room, e.date, e.equip_status "
                             + "FROM equipment as e "
                             + "WHERE e.equip_id = " + equ_id);
        rs.next();
        EquipmentRecord equ = new EquipmentRecord(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        rs.close();
        stmt.close();
        return equ;
    }
    public ClassRecord getClassByID(int class_id)throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT c.* "
                             + "FROM class as c "
                             + "WHERE c.class_id = " + class_id);
        rs.next();
        ClassRecord classes = new ClassRecord(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        rs.close();
        stmt.close();
        return classes;
    }
    
    
    
    public List<DropDownItem> getRooms()throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("select room_name "
                             + "from room");
        ArrayList<DropDownItem> classes = new ArrayList<DropDownItem>();
        while (rs.next()) {
           classes.add(new DropDownItem(rs.getString(1)));
        }
        rs.close();
        stmt.close();
        return classes;
    }
    
    /**
     * 
     * @param startTime might be empty if not selected
     * @param EndTime 
     * @param Supervisors
     * @param Trainers
     * @param className
     * @return 
     */
//    public List<EmployeeRecord> getEmployees(String startTime, String EndTime, Boolean Supervisors, Boolean Trainers, DropDownItem className){
//        
//    }
    public List<ClassRecord> getClasses(String cName, String time, String room_name, int emp_id){
        ArrayList<ClassRecord> list = new ArrayList<>();
        list.add(new ClassRecord(001, "Crossfit", "08:00:00", "Jupiter"));
        list.add(new ClassRecord(002, "Crossfit", "16:00:00", "Jupiter"));
        list.add(new ClassRecord(003, "Crossfit", "00:00:00", "Jupiter"));
        return list;
    }
    
      /**
     * Takes non-prime attributes of the class table as input and returns a list of classes based on the values of the
     * non-null inputs.
     * @param cname
     * @param timeslot
     * @param roomName
     * @param trainer
     * @return
     * @throws SQLException
     */
    public LinkedList<Classes> getClasses(String cname, String timeslot, String roomName, String trainer)
    throws SQLException {
        //Query to find all classes
        if(cname == null && timeslot == null && roomName == null && trainer == null) {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM class");
            return findClasses();
        }
        //Query to find all classes taught by a given trainer
        else if(cname == null && timeslot == null && roomName == null && trainer != null){
            pstmt = conn.prepareStatement("SELECT class.class_id, name, time_slot, held_in FROM class, conducts WHERE" +
                    "class.class_id = conducts.class_id AND emp_id = ?");
            pstmt.setInt(1, Integer.parseInt(trainer));
            rs = pstmt.executeQuery();
            return findClasses();
        }
        //Query classes by room
        else if(cname == null && timeslot == null && roomName != null && trainer == null){
            pstmt = conn.prepareStatement("SELECT * FROM class WHERE held_in = ?");
            pstmt.setString(1, roomName);
            rs = pstmt.executeQuery();
            return findClasses();
        }
        //Query classes by timeslot
        else if(cname == null && timeslot != null && roomName == null && trainer == null){
            pstmt = conn.prepareStatement("SELECT * FROM class WHERE time_slot = ?");
            pstmt.setString(1, timeslot);
            rs = pstmt.executeQuery();
            return findClasses();
        }
        //Query classes by name and timeslot
        else if(cname != null && timeslot != null && roomName == null && trainer == null){
            pstmt = conn.prepareStatement("SELECT * FROM class WHERE name = ? AND time_slot = ?");
            pstmt.setString(1, cname);
            pstmt.setString(2, timeslot);
            rs = pstmt.executeQuery();
            return findClasses();
        }
        //Query classes by name and room
        else if(cname != null && timeslot == null && roomName != null && trainer == null){
            pstmt = conn.prepareStatement("SELECT * FROM class WHERE name = ? AND held_in = ?");
            pstmt.setString(1, cname);
            pstmt.setString(2, roomName);
            rs = pstmt.executeQuery();
            return findClasses();
        }
        //Query classes by name and trainer
        else if(cname != null && timeslot == null && roomName == null && trainer != null){
            pstmt = conn.prepareStatement("SELECT class.class_id, name, time_slot, held_in FROM class, conducts WHERE" +
                    "class.class_id = conducts.class_id AND name = ? AND emp_id = ?");
            pstmt.setString(1, cname);
            pstmt.setInt(2, Integer.parseInt(trainer));
            rs = pstmt.executeQuery();
            return findClasses();
        }
        //Query classes by room and trainer
        else if(cname == null && timeslot == null && roomName != null && trainer != null){
            pstmt = conn.prepareStatement("SELECT class.class_id, name, time_slot, held_in FROM class, conducts WHERE" +
                    "class.class_id = conducts.class_id AND held_in = ? AND emp_id = ?");
            pstmt.setString(1, roomName);
            pstmt.setInt(2, Integer.parseInt(trainer));
            rs = pstmt.executeQuery();
            return findClasses();
        }
        //Query classes by timeslot and trainer
        else if(cname == null && timeslot != null && roomName == null && trainer != null){
            pstmt = conn.prepareStatement("SELECT class.class_id, name, time_slot, held_in FROM class, conducts WHERE" +
                    "class.class_id = conducts.class_id AND time_slot = ? AND emp_id = ?");
            pstmt.setString(1, timeslot);
            pstmt.setInt(2, Integer.parseInt(trainer));
            rs = pstmt.executeQuery();
            return findClasses();
        }
        //Query classes by name, timeslot and trainer
        else if(cname != null && timeslot != null && roomName == null && trainer != null){
            pstmt = conn.prepareStatement("SELECT class.class_id, name, time_slot, held_in FROM class, conducts WHERE" +
                    "class.class_id = conducts.class_id AND name = ? AND time_slot = ? AND emp_id = ?");
            pstmt.setString(1, cname);
            pstmt.setString(2, timeslot);
            pstmt.setInt(3, Integer.parseInt(trainer));
            rs = pstmt.executeQuery();
            return findClasses();
        }
        //Query classes by name, room, and trainer
        else if(cname != null && timeslot == null && roomName != null && trainer != null){
            pstmt = conn.prepareStatement("SELECT class.class_id, name, time_slot, held_in FROM class, conducts WHERE" +
                    "class.class_id = conducts.class_id AND name = ? AND held_in = ? AND emp_id = ?");
            pstmt.setString(1, cname);
            pstmt.setString(2, roomName);
            pstmt.setInt(3, Integer.parseInt(trainer));
            rs = pstmt.executeQuery();
            return findClasses();
        }
        //Query classes by timeslot, room, and trainer
        else if(cname == null && timeslot != null && roomName != null && trainer != null){
            pstmt = conn.prepareStatement("SELECT class.class_id, name, time_slot, held_in FROM class, conducts WHERE" +
                    "class.class_id = conducts.class_id AND time_slot = ? AND held_in = ? AND emp_id = ?");
            pstmt.setString(1, timeslot);
            pstmt.setString(2, roomName);
            pstmt.setInt(3, Integer.parseInt(trainer));
            rs = pstmt.executeQuery();
            return findClasses();
        }
        //Query classes by name, room and time
        else if(cname != null && timeslot != null && roomName != null && trainer == null){
            pstmt = conn.prepareStatement("SELECT * FROM class WHERE name = ? AND held_in = ? AND time_slot = ?");
            pstmt.setString(1, cname);
            pstmt.setString(2, roomName);
            pstmt.setString(3, timeslot);
            rs = pstmt.executeQuery();
            return findClasses();
        }
        else return new LinkedList<Classes>();

    }


    /**
     * Adds a class with the given values
     * @param classId
     * @param cname
     * @param time
     * @param room
     * @throws SQLException
     */
    public void addClass(String classId, String cname, String time, String room) throws SQLException{
        pstmt = conn.prepareStatement("INSERT INTO class VALUES (?,?,?,?)");
        pstmt.setInt(1, Integer.parseInt(classId));
        pstmt.setString(2, cname);
        pstmt.setString(3, time);
        pstmt.setString(4, room);
        rs = pstmt.executeQuery();
    }

    /**
     * Updates a class in the Gym database based on class_id
     * @param classId
     * @param cname
     * @param timeslot
     * @param room
     * @throws SQLException
     */
    public void updateClass(String classId, String cname, String timeslot, String room) throws SQLException{
        pstmt = conn.prepareStatement("UPDATE class SET name = ?, time_slot = ?, held_in = ? WHERE class_id = ?");
        pstmt.setString(1, cname);
        pstmt.setString(2, timeslot);
        pstmt.setString(3, room);
        pstmt.setInt(4, Integer.parseInt(classId));
        rs = pstmt.executeQuery();
    }

    /**
     * Helper method to create a linked list of classes from the data currently stored in the ResultSet variable.
     * @return
     * @throws SQLException
     */
    public LinkedList<Classes> findClasses() throws SQLException{
        LinkedList<Classes> classes = new LinkedList<>();
        int class_id = -1;
        String name = "";
        String time = "";
        String room = "";
        while(rs.next()){
            Object obj = rs.getObject(1);
            if (!rs.wasNull())
                class_id = Integer.parseInt(obj.toString());
            obj = rs.getObject(2);
            if(!rs.wasNull())
                name = obj.toString();
            obj = rs.getObject(3);
            if(!rs.wasNull())
                time = obj.toString();
            obj = rs.getObject(4);
            if(!rs.wasNull())
                room = obj.toString();
            classes.add(new Classes(class_id, name, time, room));
        }
        return classes;
    }
//    public List<EquipmentRecord> getEquipment(String type, String room, String status, String maintDate){
//        
//    }    
    
    @Override
    public void finalize(){
        closeConnection();
    }
}
