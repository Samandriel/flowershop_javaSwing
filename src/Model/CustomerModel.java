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
public final class CustomerModel {
    private int id;
    private String name;
    private String email;
    private String phone;
    private int count;
    
    Database db = new Database();
    Connection c = db.Connect();
    Statement create;
    Statement fetch;
    Statement update;
    Statement delete;
    ResultSet dbData;
    
    private List<CustomerModel> items;
    private CustomerModel item;
    
    public CustomerModel() throws SQLException {
    }

    public CustomerModel(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
   
    
    public List<CustomerModel> fetch() throws SQLException {
        this.fetch = c.createStatement();
        this.dbData  = fetch.executeQuery("Select * from customers;");        
        
        while(!this.dbData.isLast()) {
            this.dbData.next();
            this.count++;
        }
        
        this.dbData.first();
        this.items  = new ArrayList<>();
        for (int i = 0; i < this.count; i++) {
            this.id = this.dbData.getInt("id");
            this.name = this.dbData.getString("name");
            this.email = this.dbData.getString("email");
            this.phone = this.dbData.getString("phone");
            
            items.add(new CustomerModel(this.id, this.name, this.email, this.phone));
            
            this.dbData.next();
        }
        return items;
    }
    
    public CustomerModel fetchById(int inputId) throws SQLException {
        this.fetch = c.createStatement();
        this.dbData  = fetch.executeQuery("Select * from customers where id=" + inputId + ";");        
        
        item  = new CustomerModel();
        item.id = this.dbData.getInt("id");
        item.name = this.dbData.getString("name");
        item.email = this.dbData.getString("email");
        item.phone = this.dbData.getString("phone");

        return item;
    }    
    
    public void create(int id, String name, String email, String phone) throws SQLException {
        
        update.executeQuery("Insert into customers (id, name, email, phone) value(" 
                + "," + id
                + "," + name
                + "," + email
                + "," + phone
                + ");"
        );
    }    
    
    public void update(int id, String name, String email, String phone) throws SQLException {
        
        update.executeUpdate("Update from customers set" 
                + "id=" + id
                + "name=" + name
                + "email=" + email
                + "phone=" + phone
                + ";"
        );
    }    

    public void delete(int id) throws SQLException {
        delete.executeUpdate("Delete from customers where id=" + id + ";");
    }


}
