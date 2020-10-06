package gym.fxml;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
