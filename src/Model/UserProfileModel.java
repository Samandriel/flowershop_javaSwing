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
public final class UserProfileModel {
    private int id;
    private int userId;
    private int customerId;
    private int count;
    
    Database db = new Database();
    Connection c = db.Connect();
    ResultSet dbData;
    Statement q;
    
    private List<UserProfileModel> items;
    private UserProfileModel item;
    
    public UserProfileModel() throws SQLException {
    }

    public UserProfileModel(int userId,int customerId) {
        this.userId = userId;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    
    public List<UserProfileModel> fetch() throws SQLException {
        this.q = c.createStatement();
        this.dbData  = q.executeQuery("Select * from user_profiles;");        
        
        while(!this.dbData.isLast()) {
            this.dbData.next();
            this.count++;
        }
        
        this.dbData.first();
        this.items  = new ArrayList<>();
        for (int i = 0; i < this.count; i++) {
            this.userId = this.dbData.getInt("user_id");
            this.customerId = this.dbData.getInt("customer_id");
            
            items.add(new UserProfileModel(this.userId, this.customerId));
            
            this.dbData.next();
        }
        c.close();
        return items;
    }
      
    
    public void create(int userId,int customerId) throws SQLException {
        this.q = c.createStatement();
        q.execute("Insert into user_profiles (user_id, customer_id) value(" 
                + userId
                + "," + customerId
                + ");"
        );
        c.close();
    }    
    
    public void update(int userId,int customerId) throws SQLException {
        this.q = c.createStatement();
        q.execute("Update from user_profiles set" 
                + "user_id=" + userId
                + "customer_id=" + customerId
                + ";"
        );
        c.close();
    }    

    public void delete(int id) throws SQLException {
        this.q = c.createStatement();
        q.executeUpdate("Delete from user_profiles where id=" + id + ";");
        c.close();
    }


}
