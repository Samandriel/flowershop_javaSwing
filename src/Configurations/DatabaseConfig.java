/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configurations;

/**
 *
 * @author PC
 */
public class DatabaseConfig {
    String myhost="jdbc:mysql://localhost/";
    String myDatabase="flower_shop";
    String user="root";
    String password="";

    public String getHost() {
        return myhost;
    }

    public String getDB() {
        return myDatabase;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
    
    
}
