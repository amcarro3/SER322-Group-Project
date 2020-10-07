package gym.fxml;
/**
 *
 * @author Adam
 */
public class EquipmentRecord {
    private int id;
    private String type;
    private String  room;
    private String date;
    private String status;

    public EquipmentRecord(int id, String type, String room, String date, String status) {
        this.id = id;
        this.type = type;
        this.room = room;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
