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

/**
 *
 * @author lundberf
 */
public class DbMgmt {
    
    private static DbMgmt me;
    
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_HOST = "jdbc:derby://localhost:1527/";
    private static final String DB_NAME = "sample";
    private static final String DB_USERNAME = "app";
    private static final String DB_PASSWORD = "app";
    
    public static DbMgmt getInstance(){
        if(me == null)
            me = new DbMgmt();
        return me;
    }
    
    public ResultSet query(String statement){
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;
        System.out.println("statement: " + statement);
        try{ 
            Class.forName(DB_DRIVER);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }

        try{
            con = DriverManager.getConnection(DB_HOST + DB_NAME, DB_USERNAME, DB_PASSWORD);
            stmt = con.createStatement();

            rs = stmt.executeQuery(statement);
        }catch(SQLException e){
            System.err.println(e);
        }
        return rs;
    }
    
    public int update(String statement){
        Connection con = null;
        Statement stmt = null;
        System.out.println("statement: " + statement);
        try{ 
            Class.forName(DB_DRIVER);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }

        try{
            con = DriverManager.getConnection(DB_HOST + DB_NAME, DB_USERNAME, DB_PASSWORD);
            stmt = con.createStatement();

            return stmt.executeUpdate(statement);
        }catch(SQLException e){
            System.err.println(e);
        }
        return 0;
    }
}
