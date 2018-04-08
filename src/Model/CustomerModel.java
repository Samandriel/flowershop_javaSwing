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
    private Statement q;
    
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
        c.close();
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
    
    public void create(String name, String email, String phone) throws SQLException {
        this.q = c.createStatement();
        String qString = "Insert into customers (name, email, phone) value('" 
                + name
                + "','" + email
                + "','" + phone
                + "');";
        q.execute(qString);
        System.out.println(qString);
        System.out.println("Created successfully");
        c.close();
    }    
    
    public void update(int id, String name, String email, String phone) throws SQLException {
        this.q = c.createStatement();
        String qString =  "Update customers set" 
                + " name='" + name
                + "', email='" + email
                + "', phone='" + phone
                + "' where id=" + id + ";";
        this.q.executeUpdate(qString);
        System.out.println(qString);
        System.out.println("Updated successfully");
        c.close();
    }    

    public void delete(int id) throws SQLException {
        this.q = c.createStatement();
        String qString = "Delete from customers where id=" + id + ";";
        q.execute(qString);
        System.out.println("Deleted successfully");
        c.close();
    }


}
