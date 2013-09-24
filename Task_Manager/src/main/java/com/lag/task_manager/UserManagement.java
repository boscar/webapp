/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lag.task_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lundberf
 */
public class UserManagement {
    private static UserManagement me;
    
    public static UserManagement getInstance(){
        if(me == null)
            me = new UserManagement();
        return me;
    }
    
    public int doLogin(String username, String password){
        try {
            ResultSet result = DbMgmt.getInstance().query("SELECT * FROM APP.USERS WHERE username = '" + username + "'");
            result.next();
            if(result.getString("PASSWORD").equals(password))
                return Integer.parseInt(result.getString("ID"));
        } catch (SQLException ex) {
            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, "SQL FAILED CHECK THIS", ex);
        }
        return -1;
    }
    
    public boolean createUser(String username, String password){
            int insertResult = DbMgmt.getInstance().update("INSERT INTO APP.USERS(USERNAME,PASSWORD) VALUES('"+ username + "','" + password + "')");
            return insertResult>0?true:false;
    }
}
