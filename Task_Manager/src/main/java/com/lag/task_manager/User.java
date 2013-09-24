/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lag.task_manager;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lundberf
 */
public class User {
    
    /**
     * The id of the user.
     */
    protected int userId;
    
    /**
     * A list of all the users tasks.
     */
    protected List<Task> tasks = new ArrayList<Task>();

    public User(int uId){
        this.userId = uId;
        fetchTasksFromDb();
        
        //TEST
        for(Task t : tasks)
            System.out.println("Task " + t.getId() + " : " + t.getDescription());
    }
    
    protected void fetchTasksFromDb(){
        ResultSet tasksFromDb = null;
        try {
            tasksFromDb = DbMgmt.getInstance().query("SELECT * FROM TASKS WHERE USERID = " + this.userId);
            while(tasksFromDb.next()){
                tasks.add(new Task(tasksFromDb.getString("DESCRIPTION")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, "SQL FAILED CHECK THIS", ex);
        }
    }
    
    /**
     * Adds a taks to the users list of tasks.
     * 
     * @param desc 
     */
    public void addTask(String desc){
        if (desc == null)
            throw new IllegalArgumentException("Nulls not allowed");
        tasks.add(new Task(desc));
    }
    
    /**
     * Removes a task from the users list of tasks.
     * 
     * @param id 
     */
    public void removeTask(int id){
        Task taskToRemove = null;
        
        for(Task t : tasks)
            if(t.getId() == id)
                taskToRemove = t;
        
        if(taskToRemove != null)
            tasks.remove(taskToRemove);
    }

    /**
     * Assigns the new description to the users task that has the specified id.
     * 
     * @param id
     * @param desc 
     */
    public void updateTask(int id, String desc){
        for(Task t : tasks)
            if(t.getId() == id)
               t.setDescription(desc);
    }
    
    /**
     * Returns the user task with the specified id, If not task with that id can
     * be found, return null.
     * 
     * @param id
     * @return 
     */
    public Task find(int id){
        for(Task t : tasks)
            if(t.getId() == id)
              return t;
        return null;
    }
    
    /**
     * Returns the amount of tasks the user has.
     * 
     * @return 
     */
    public int getCount(){
        return tasks.size();
    }
    
    /**
     * Returns a sublist of the users tasks, starting from first and with nItems 
     * tasks.
     * 
     * @param first
     * @param nItems
     * @return 
     */
    public List<Task> getRange(int first, int nItems) {
        if (first < 0)
            throw new IllegalArgumentException("The first value can not be negative.");
        if (first+nItems >= tasks.size())
            throw new IndexOutOfBoundsException("Index (first + nItems) out of bounds.");
        return tasks.subList(first, nItems);
    }
}