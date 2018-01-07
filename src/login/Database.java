/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erik.erbsloeh
 */
public class Database {
    
    private String URL, user, password;
    private Connection conn;
    private Statement statement;
    
    public Database(String URL, String user, String password) {
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            this.URL = URL;
            this.user = user;
            this.password = password;
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean connect() {
        boolean ret = false;
        try {
            if (conn != null) {
                if (!conn.isClosed()) {
                    conn = (Connection) DriverManager.getConnection(URL, user, password);
                    ret = true;
                }
            
            }
            else {
                conn = (Connection) DriverManager.getConnection(URL, user, password);
                ret = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public ResultSet executeStatement(String statement) {
        try {
            this.statement = (Statement) conn.createStatement();
            return this.statement.executeQuery(statement);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void disconnect() {
        try {   statement.close();  } catch (Exception ex) {}
        try {   conn.close();  } catch (Exception ex) {}
    }
    
}
