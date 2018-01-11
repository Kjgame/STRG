/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author erik.erbsloeh
 */
public class DBController {
    
    private Database db;
    
    private boolean connected;
    
    public DBController() {
        connected = false;
    }
    
    public void connect(String adress, String user, String pass) {
        db = new Database("jdbc:mysql://" + adress, user, pass);
        connected = db.connect();
    }
    
    public boolean isConnected() {
        return connected && db.isConnected();
    }
    
    public boolean login(String username, String password) throws SQLException {
        
        ResultSet rs = db.executeStatement("select id from login.users where name = \"" + username + "\" and pass = \"" + password + "\"");
        
        
        return rs.next();
    }
    
    public void disconnect() {
        db.disconnect();
    }
    
}
