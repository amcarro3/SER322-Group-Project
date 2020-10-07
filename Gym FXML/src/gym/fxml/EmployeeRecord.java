package gym.fxml;

/**
 * EmployeeRecord Class
 * @author bonde
 */
public class EmployeeRecord {
    
    private int emp_id;
    private String fname;
    private String lname;
    private int ssn;
    private String pnumb;
    private String start_time;
    private String end_time;
    
    //Returns the Employee's employee id number.
    public int getEmp_id(){
        return emp_id;
    }
    //Takes the Empolyee's id number from the database and assigns it to emp_id.
    public void setEmp_id(int emp_id){
        this.emp_id = emp_id;
    }
    //Returns the Employee's first name.
    public String getFname(){
        return fname;
    }
    //Takes the Employee's first name from the database and assigns it to fname.
    public void setFname(String fname){
        this.fname = fname;
    }
    //Returns the Employee's last name.
    public String getLname(){
        return lname;
    }
    //Takes the Employee's last name from the database and assigns it to lname.
    public void setLname(String lname){
        this.lname = lname;
    }
    //Returns the Employee's social security number.
    public int getSsn(){
        return ssn;
    }
    //Takes the Employee's social security number from the database and assigns
    // it to ssn. Expected ssn format with no dashes, i.e. 111111111
    public void setSsn(int ssn){
        this.ssn = ssn;
    }
    //Returns the Employee's phone number.
    public String getPnumb(){
        return pnumb;
    }
    //Takes the Employee's phone number from the database and assigns it 
    // to pnumb. Expected phone number format with no dashes, i.e. 5555555555
    public void setPnumb(String pnumb){
        this.pnumb = pnumb;
    }
    //Returns the Employee's shift start time. HH:MM:SS format, i.e. 08:00:00
    public String getStart_time(){
        return start_time;
    }
    //Takes the Employee's shift start time from the database and assigns it 
    // to start_time. HH:MM:SS format, i.e. 08:00:00
    public void setStart_time(String start_time){
        this.start_time = start_time;
    }
    //Returns the Employee's shift end time. HH:MM:SS format, i.e. 08:00:00
    public String getEnd_time(){
        return end_time;
    }
    //Takes the Employee's shift end time from the database and assigns it 
    // to end_time. HH:MM:SS format, i.e. 08:00:00
    public void setEnd_time(String end_time){
        this.end_time = end_time;
    }    
}
