package com.lag.task_manager;

/**
 * Hello world!
 *
 */
public class App 
{
    
    public static void main( String[] args )
    {
        //LOGIN
        int userId;
        userId = UserManagement.getInstance().doLogin("admin", "admin");
        if(userId > 0){
            System.out.println("Succesfully logged in with id: " + userId);
            User currentUser = new User(userId);
        }else{
            System.out.println("Failed to login.");
        }
        
        //CREATE USER
//        if(UserManagement.getInstance().createUser("rov", "rov"))
//            System.out.println("Succesfully created a new user.");
//        else
//            System.out.println("Could not create user.");
    }
}
