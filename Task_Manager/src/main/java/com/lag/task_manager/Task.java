/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lag.task_manager;

import java.util.Objects;
import java.util.Random;

/**
 *
 * @author lundberf
 */
public class Task {
    
    /**
     * The range of which the id can be assigned.
     */
    public static final int ID_RANGE = 1000;
    
    /**
     * The id of the task.
     */
    private int id;
    
    /**
     * The description of the task.
     */
    private String description;
    
    /**
     * Boolean variable that specifies if the task is complete.
     */
    private boolean complete;
    
    public Task(String disc){
        id = RandomzieId();
        description = disc;
        complete = false;
    }
    
    public static int RandomzieId(){
        return (int)(new Random().nextInt(ID_RANGE));
    }
    
    /**
     * We override the equals method to implement our own, which simply checks the
     * id of the Tasks.
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        
        if (this.getClass() != obj.getClass())
            return false;
        
        final Task other = (Task) obj;
        if (!Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        return hash;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the complete
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * @param complete the complete to set
     */
    public void setComplete(boolean complete) {
        this.complete = complete;
    }
    
}
