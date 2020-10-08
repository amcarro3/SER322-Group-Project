package gym.fxml;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
    public List<String> getEquTypes(Boolean t) throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("select distinct equip_type "
                             + "from equipment");
        ArrayList<String> classes = new ArrayList<>();                     
        while (rs.next()) {
           classes.add(rs.getString(1));
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
    public List<String> getEquStatuses(Boolean t) throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("select distinct equip_status "
                             + "from equipment");
        ArrayList<String> classes = new ArrayList<String>();                     
        while (rs.next()) {
           classes.add(rs.getString(1));
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
        rs = stmt.executeQuery("select distinct c.class_id, c.name, c.time_slot "
                             + "from class as c");
        ArrayList<DropDownItem> classes = new ArrayList<DropDownItem>();                     
        while (rs.next()) {
           int id = rs.getInt(1);
           classes.add(new DropDownItem(id, Integer.toString(id)+" "+rs.getString(2)+" "+rs.getString(3)));
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
    public List<String> getSkillsByIDs(int emp_id) throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT s.skill "
                             + "FROM skills as s "
                             + "WHERE s.emp_id="+emp_id);
        ArrayList<String> skills = new ArrayList<>();                     
        while (rs.next()) {
           skills.add(rs.getString(1));
        }
        rs.close();
        stmt.close();
        return skills;
    }
    
    public Boolean isSuper(int empNo) throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * "
                             + "FROM supervisor as s "
                             + "WHERE s.emp_id="+empNo);
        return rs.next();
    }
    public void updateSuper(String id, Boolean supervisor)throws Exception{
        if(supervisor){
            if(isSuper(Integer.parseInt(id))) return;
            else{
                stmt.executeUpdate("INSERT INTO supervisor values ("+id+")");
                conn.commit();
            }
        }else{
            if(!isSuper(Integer.parseInt(id))) return;
            else{
                stmt.executeUpdate("Delete From supervisor where emp_id="+id);
                conn.commit();
            }
        }
    }
    public Boolean conductsClass(String emp_id, String class_id)throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * "
                             + "FROM conducts as c "
                             + "WHERE c.emp_id="+emp_id+" and c.class_id="+class_id);
        return rs.next();
    }
    public void updateConducts(String emp_id, String class_id, Boolean add)throws Exception{
        if(add){
            if(conductsClass(emp_id, class_id)) return;
            else{
                stmt.executeUpdate("INSERT INTO conducts values ("+emp_id+", "+class_id+")");
                conn.commit();
            }
        }else{
            if(!conductsClass(emp_id, class_id)) return;
            else{
                stmt.executeUpdate("Delete From conducts where emp_id="+emp_id+" and class_id="+class_id);
                conn.commit();
            }
        }
    }
    public Boolean isTrainer(int empNo)throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * "
                             + "FROM trainer as t "
                             + "WHERE t.emp_id="+empNo);
        return rs.next();
    }
    public void updateTrainer(String id, Boolean add)throws Exception{
        if(add){
            if(isTrainer(Integer.parseInt(id))) return;
            else{
                stmt.executeUpdate("INSERT INTO trainer values ("+id+")");
                conn.commit();
            }
        }else{
            if(!isTrainer(Integer.parseInt(id))) return;
            else{
                stmt.executeUpdate("Delete From trainer where emp_id="+id);
                conn.commit();
            }
        }
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
    
    /**
     * @author Anthony
     */   
    public void deleteEmployee(int emp_id) throws Exception{
        String query = "DELETE FROM skills "
                + "WHERE emp_id=?";
	pstmt = conn.prepareStatement(query);
	pstmt.setInt(1, emp_id);
        pstmt.execute();
        query = "DELETE FROM conducts "
                + "WHERE emp_id=?";
	pstmt = conn.prepareStatement(query);
	pstmt.setInt(1, emp_id);
        pstmt.execute();
        query = "DELETE FROM supervisor "
                + "WHERE emp_id=?";
	pstmt = conn.prepareStatement(query);
	pstmt.setInt(1, emp_id);
        pstmt.execute();
        query = "DELETE FROM trainer "
                + "WHERE emp_id=?";
	pstmt = conn.prepareStatement(query);
	pstmt.setInt(1, emp_id);
        pstmt.execute();
        query = "DELETE FROM employee "
                + "WHERE emp_id=?";
	pstmt = conn.prepareStatement(query);
	pstmt.setInt(1, emp_id);
        pstmt.execute();
        
	Alert notice = new Alert (AlertType.INFORMATION);
	notice.setTitle("Delete Status");
	notice.setHeaderText(null);
	notice.setContentText("Employee was deleted!");
	notice.showAndWait();
	
        conn.commit();
	pstmt.close();
    }
    /**
     * @author Anthony
     */   
    public void deleteEquip(int equip_id) throws Exception{
        String query = "DELETE from equipment where equip_id=?";
	pstmt = conn.prepareStatement(query);
	pstmt.setInt(1, equip_id);
        pstmt.execute();
        conn.commit();
	Alert notice = new Alert (AlertType.INFORMATION);
	notice.setTitle("Delete Status");
	notice.setHeaderText(null);
	notice.setContentText("Equiptment was deleted!");
	notice.showAndWait();	
	pstmt.close();
    }
    /**
     * @author Anthony
     */   
    public void deleteClass(int class_id) throws Exception{
        String query = "DELETE from conducts Where class_id=?";
	pstmt = conn.prepareStatement(query);
	pstmt.setInt(1, class_id);
        pstmt.execute();
        query = "DELETE from class Where class_id=?";
	pstmt = conn.prepareStatement(query);
	pstmt.setInt(1, class_id);
        pstmt.execute();
        conn.commit();
	Alert notice = new Alert (AlertType.INFORMATION);
	notice.setTitle("Delete Status");
	notice.setHeaderText(null);
	notice.setContentText("Class was deleted!");
	notice.showAndWait();	
	pstmt.close();
    }
    public void deleteSkill(int emp_id, String skill) throws Exception{
        String query = "DELETE from skills Where emp_id=? AND skill=?";
	pstmt = conn.prepareStatement(query);
	pstmt.setInt(1, emp_id);
        pstmt.setString(2, skill);
        pstmt.execute();
        conn.commit();
        Alert notice = new Alert (AlertType.INFORMATION);
	notice.setTitle("Delete Status");
	notice.setHeaderText(null);
	notice.setContentText("skill was deleted!");
	notice.showAndWait();	
	pstmt.close();
    }    
    /**
     * 
     * @param id
     * @param type
     * @param room
     * @param date
     * @param status
     * @throws Exception 
     * @author Anthony
     */
    public void updateEquip(String id, String type, String room, String date, String status) throws Exception{
        PreparedStatement upd;
        String query = "UPDATE equipment set equip_type=?, room=?, date=?, equip_status=? Where equip_id="+id;
        upd = conn.prepareStatement (query);
        upd.setString(1, type);
        upd.setString(2, room);
        upd.setString(3, date);
        upd.setString(4, status);
        upd.execute();
        conn.commit();
        upd.close();
    }
    
    
    public void updateEmployee(String id, String fname, String lname, String ssn, String pnum, String start, String end, Boolean supervisor)throws Exception{
        this.updateEmployee(id, fname, lname, ssn, pnum, start, end);
        stmt = conn.createStatement();
        updateSuper(id, supervisor);       
    }
   /**
     * 
     * @param id
     * @param fname
     * @param lname
     * @param ssn
     * @param pnum
     * @param start
     * @param end
     * @throws Exception 
     * @author Anthony
     */   
    public void updateEmployee(String id, String fname, String lname, String ssn, String pnum, String start, String end)throws Exception{
        String query = "UPDATE employee SET fname=?, lname=?, ssn=?, pnum=?, start_time=?, end_time=? Where emp_id="+id;
        PreparedStatement upd;
	upd = conn.prepareStatement (query);
	upd.setString(1, fname);
	upd.setString(2, lname);
	upd.setString(3, ssn);
	upd.setString(4, pnum);
	upd.setString(5, start);
	upd.setString(6, end);	
	upd.execute();
        conn.commit();
	upd.close();
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
     * @author John
     */
    public List<ClassRecord> getClasses(String cname, String timeslot, String roomName, String trainer)
    throws SQLException {
        //Query to find all classes
        if(cname == null && timeslot == null && roomName == null && trainer == null) {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM class");
            return findClasses();
        }
         //Query classes by name
        else if(cname != null && timeslot == null && roomName == null && trainer == null){
            pstmt = conn.prepareStatement("SELECT * FROM class WHERE name = ?");
            pstmt.setString(1, cname);
            rs = pstmt.executeQuery();
            return findClasses();
        }
        //Query to find all classes taught by a given trainer
        else if(cname == null && timeslot == null && roomName == null && trainer != null){
            pstmt = conn.prepareStatement("SELECT class.class_id, name, time_slot, held_in FROM class, conducts WHERE " +
                    "class.class_id = conducts.class_id AND conducts.emp_id = ?");
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
            pstmt = conn.prepareStatement("SELECT * FROM class WHERE name = ? AND time_slot="+timeslot);
            pstmt.setString(1, cname);
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
            pstmt = conn.prepareStatement("SELECT class.class_id, name, time_slot, held_in FROM class, conducts WHERE " +
                    "class.class_id = conducts.class_id AND name = ? AND emp_id = ?");
            pstmt.setString(1, cname);
            pstmt.setInt(2, Integer.parseInt(trainer));
            rs = pstmt.executeQuery();
            return findClasses();
        }
        //Query classes by room and trainer
        else if(cname == null && timeslot == null && roomName != null && trainer != null){
            pstmt = conn.prepareStatement("SELECT class.class_id, name, time_slot, held_in FROM class, conducts WHERE " +
                    "class.class_id = conducts.class_id AND held_in = ? AND emp_id = ?");
            pstmt.setString(1, roomName);
            pstmt.setInt(2, Integer.parseInt(trainer));
            rs = pstmt.executeQuery();
            return findClasses();
        }
        //Query classes by timeslot and trainer
        else if(cname == null && timeslot != null && roomName == null && trainer != null){
            pstmt = conn.prepareStatement("SELECT class.class_id, name, time_slot, held_in FROM class, conducts WHERE " +
                    "class.class_id = conducts.class_id AND time_slot="+timeslot+" AND emp_id = ?");
            pstmt.setInt(1, Integer.parseInt(trainer));
            rs = pstmt.executeQuery();
            return findClasses();
        }
        //Query classes by name, timeslot and trainer
        else if(cname != null && timeslot != null && roomName == null && trainer != null){
            pstmt = conn.prepareStatement("SELECT class.class_id, name, time_slot, held_in FROM class, conducts WHERE" +
                    "class.class_id = conducts.class_id AND name = ? AND class.time_slot="+timeslot+" AND conducts.emp_id = ?");
            pstmt.setString(1, cname);
            pstmt.setInt(2, Integer.parseInt(trainer));
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
            pstmt = conn.prepareStatement("SELECT class.class_id, name, time_slot, held_in FROM class, conducts WHERE " +
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
        //Query classes by timeslot, room, and trainer
        else if(cname == null && timeslot != null && roomName != null && trainer == null){
            pstmt = conn.prepareStatement("SELECT class.class_id, name, time_slot, held_in FROM class WHERE " +
                    "time_slot = ? AND held_in = ?");
            pstmt.setString(1, timeslot);
            pstmt.setString(2, roomName);
            rs = pstmt.executeQuery();
            return findClasses();
        }
        else return new LinkedList<ClassRecord>();

    }
    
    public List<EmployeeRecord> getEmployees(String start, String end, String class_name, Boolean supers)throws Exception{
        String queryfrom = "Select e.emp_id, e.fname, e.lname, e.ssn, e.pnum, e.start_time, e.end_time from employee as e";
        String queryCon = " where e.emp_id=e.emp_id";
        if(start!=null) queryCon = queryCon.concat(" and start_time="+start);
        if(end!=null) queryCon = queryCon.concat(" and end_time="+end);
        if(class_name!=null){
            queryfrom = queryfrom.concat(", conducts as c, class as cs");
            queryCon = queryCon.concat(" and e.emp_id=c.emp_id and cs.class_id = c.class_id and cs.name='"+class_name+"'");
        }
        if(supers){
            queryfrom = queryfrom.concat(", supervisor as s");
            queryCon = queryCon.concat(" and e.emp_id= s.emp_id");
        }
        String query = queryfrom+queryCon;
        System.out.println(query);
        stmt= conn.createStatement();
        rs = stmt.executeQuery(query);
        ArrayList<EmployeeRecord> list = new ArrayList<>();
        while(rs.next()){
            list.add(new EmployeeRecord(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),rs.getString(6),rs.getString(7)));
        }
        return list;
    }


    /**
     * Adds a class with the given values
     * @param classId
     * @param cname
     * @param time
     * @param room
     * @throws SQLException
     * @author John
     */
    public void addClass(String classId, String cname, String time, String room) throws SQLException{
        pstmt = conn.prepareStatement("INSERT INTO class VALUES (?,?,?,?)");
        pstmt.setInt(1, Integer.parseInt(classId));
        pstmt.setString(2, cname);
        pstmt.setString(3, time);
        pstmt.setString(4, room);
        pstmt.executeUpdate();
        conn.commit();
    }
    /**
     * 
     * @param id
     * @param fname
     * @param lname
     * @param ssn
     * @param phone
     * @param start
     * @param end
     * @param Classe
     * @param sup
     * @throws Exception 
     * @author Anthony
     */
    public void addEmployee(String id, String fname, String lname, String ssn, String phone, String start, String end, String classe, Boolean sup)throws Exception{
        String query = "INSERT INTO employee VALUES (?,?,?,?,?,?,?)";
        PreparedStatement upd;
	upd = conn.prepareStatement (query);
        upd.setString(1, id);
	upd.setString(2, fname);
	upd.setString(3, lname);
	upd.setString(4, ssn);
	upd.setString(5, phone);
	upd.setString(6, start);
	upd.setString(7, end);	
	upd.execute();
        conn.commit();
	upd.close();
        updateSuper(id, sup);        
        updateTrainer(id, true);
        if(classe!=null) updateConducts(id, classe, true);
    }

    /**
     * Updates a class in the Gym database based on class_id
     * @param classId
     * @param cname
     * @param timeslot
     * @param room
     * @throws SQLException
     * @author John
     */
    public void updateClass(String classId, String cname, String timeslot, String room) throws SQLException{
        pstmt = conn.prepareStatement("UPDATE class SET name = ?, time_slot = ?, held_in = ? WHERE class_id = ?");
        pstmt.setString(1, cname);
        pstmt.setString(2, timeslot);
        pstmt.setString(3, room);
        pstmt.setInt(4, Integer.parseInt(classId));
        pstmt.executeUpdate();
        conn.commit();
    }

    /**
     * Helper method to create a linked list of classes from the data currently stored in the ResultSet variable.
     * @return
     * @throws SQLException
     * @author John
     */
    public LinkedList<ClassRecord> findClasses() throws SQLException{
        LinkedList<ClassRecord> classes = new LinkedList<>();
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
            classes.add(new ClassRecord(class_id, name, time, room));
        }
        return classes;
    }
    
    public int getMaxIdEmp() throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT MAX(emp_id) FROM Employee");
        rs.next();
        return rs.getInt(1);
    }
    
    public int getMaxIdClass() throws Exception{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT MAX(class_id) FROM class");
        rs.next();
        return rs.getInt(1);
    }
    
    @Override
    public void finalize(){
        closeConnection();
    }
}
