/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Configurations.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author harri
 */
public final class UserModel {
    private int id;
    private String email;
    private String password;
    
    Database db = new Database();
    Connection c = db.Connect();
    
    private List<UserModel> items;
    private UserModel item;
    private Statement q;
    private int count;
    ResultSet dbData;
    
    public UserModel() throws SQLException {
    }

    public UserModel(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPassword() {
        return password;
    }
   
    
    public List<UserModel> fetch() throws SQLException {
        this.q = c.createStatement();
        this.dbData  = q.executeQuery("Select * from users;");        
        
        while(!this.dbData.isLast()) {
            this.dbData.next();
            this.count++;
        }
        
        this.dbData.first();
        this.items  = new ArrayList<>();
        for (int i = 0; i < this.count; i++) {
            this.id = this.dbData.getInt("id");
            this.email = this.dbData.getString("email");
            this.password = this.dbData.getString("password");
            
            items.add(new UserModel(this.id, this.email, this.password));
            
            this.dbData.next();
        }
        c.close();
        return items;
    }
    
    public UserModel fetchById(int inputId) throws SQLException {
        this.q = c.createStatement();
        this.dbData  = q.executeQuery("Select * from users where id=" + inputId + ";");        
        
        item  = new UserModel();
        item.id = this.dbData.getInt("id");
        item.email = this.dbData.getString("email");
        item.password = this.dbData.getString("password");

        return item;
    }    
    
    public void create(String email, String password) throws SQLException {
        this.q = c.createStatement();
        String qString = "Insert into users (email, password) value('" 
                + email
                + "','" + password
                + "');";
        q.execute(qString);
        System.out.println(qString);
        System.out.println("Created successfully");
        c.close();
    }    
    
    public void update(int id, String email, String password) throws SQLException {
        this.q = c.createStatement();
        String qString =  "Update users set" 
                + "', email='" + email
                + "', password='" + password
                + "' where id=" + id + ";";
        this.q.executeUpdate(qString);
        System.out.println(qString);
        System.out.println("Updated successfully");
        c.close();
    }    

    public void delete(int id) throws SQLException {
        this.q = c.createStatement();
        String qString = "Delete from users where id=" + id + ";";
        q.execute(qString);
        System.out.println("Deleted successfully");
        c.close();
    }


}
