/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.fxml;

import java.io.IOException;
import java.sql.DriverManager;
import java.util.List;
import java.util.Set;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Adam
 */
public class GymFXML extends Application {
    private boolean connected = false;
    public void startConnected(Stage stage, Database data) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("QueryPage.fxml"));
        try{loader.load();}catch(IOException ioe){ioe.printStackTrace();}
        Parent parent = loader.getRoot();

        //pass databse object to Controller
        Controller con = loader.getController();
        con.setDatabase(data);
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    
    public void startUnconnected(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("DatabaseDriver.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        List<String> args = getParameters().getRaw();
        if(args.size()>3){
            String _url = args.get(0);
            Database data = new Database();
            data.setDriver(args.get(3));
            data.login(_url, args.get(1), args.get(2));
            startConnected(stage, data);
        }else{
            startUnconnected(stage);
        }
            
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
