package gym.fxml;
/**
 *
 * @author Adam
 */
class DropDownItem {
    private int id;
    private String name;
    private String dateTime= " ";

    public String getDatetime() {
        return dateTime;
    }

    public void setDatetime(String datetime) {
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        String boxname = name;
        return boxname;
    }       
    
    public DropDownItem(String name){
        this.name = name;
    }
     
    public DropDownItem(int id, String name){
        this.id = id;
        this.name = name;
    }
    
    public DropDownItem(int id, String name, String dateTime){
        this.id = id;
        this.name = name;
        this.dateTime = this.dateTime.concat(dateTime);
    }
}
