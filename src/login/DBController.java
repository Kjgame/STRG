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
    
    public DBController() {
        db = new Database("jdbc:mysql://127.0.0.1", "root", "");
        db.connect();
    }
    
    public boolean login(String username, String password) throws SQLException {
        
        ResultSet rs = db.executeStatement("select id from login.users where name = \"" + username + "\" and pass = \"" + password + "\"");
        
        
        return rs.next();
    }
    
    public void disconnect() {
        db.disconnect();
    }
    
}
