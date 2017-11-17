/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.sql.ResultSet;

/**
 *
 * @author erik.erbsloeh
 */
public class DBController {
    
    private Database db;
    
    public DBController() {
        db = new Database("", "baum", "tree");
        db.connect();
    }
    
    public boolean login(String username, String password) {
        
        ResultSet rs = db.executeStatement("statement");
        
        return rs != null;
    }
    
}
