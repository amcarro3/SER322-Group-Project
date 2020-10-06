/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 *
 * @author Adam
 */
public class Controller implements Initializable{
       
    private Database data;
    
   
    public void setDatabase(Database data){
        this.data = data;
    }
    
    protected Database getDatabase(){
        return data;
    }
    
    protected void switchScene(ActionEvent event, String fxml){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        try{loader.load();}catch(IOException ioe){ioe.printStackTrace();}
        Parent parent = loader.getRoot();

        //pass databse object to Controller
        Controller con = loader.getController();
        con.setDatabase(data);

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
