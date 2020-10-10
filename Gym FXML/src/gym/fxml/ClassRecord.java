package gym.fxml;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Adam
 */
public class ClassRecord {
    private int id;
    private String name;
    private String time;
    private String room;
    
    public ClassRecord(){
        
    }

    public ClassRecord(int id, String name, String time, String room) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.room = room;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    
    @Override
    public String toString(){
        return String.format("%n%5d %15s %15s %15s", id, name.trim(), time.trim(), room.trim());
    }
}
