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
public final class EmployeeModel {
    private int id;
    private String name;
    private String email;
    private String phone;
    private int age;
    private byte[] image;
    private int count;
    
    Database db = new Database();
    Connection c = db.Connect();
    Statement create;
    Statement fetch;
    Statement update;
    Statement delete;
    ResultSet dbData;
    
    private List<EmployeeModel> items;
    private EmployeeModel item;
    
    public EmployeeModel() throws SQLException {
    }

    public EmployeeModel(int id, String name, String email, String phone, int age, byte[] image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.image = image;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
   
    
    public List<EmployeeModel> fetch() throws SQLException {
        this.fetch = c.createStatement();
        this.dbData  = fetch.executeQuery("Select * from employees;");        
        
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
            this.age = this.dbData.getInt("age");
            this.image = this.dbData.getBytes("image");            
            items.add(new EmployeeModel(this.id, this.name, this.email, this.phone, this.age, this.image));
            
            this.dbData.next();
        }
        return items;
    }
    
    public EmployeeModel fetchById(int inputId) throws SQLException {
        this.fetch = c.createStatement();
        this.dbData  = fetch.executeQuery("Select * from employees where id=" + inputId + ";");        
        
        item  = new EmployeeModel();
        item.id = this.dbData.getInt("id");
        item.name = this.dbData.getString("name");
        item.email = this.dbData.getString("email");
        item.phone = this.dbData.getString("phone");
        item.age = this.dbData.getInt("age");
        item.image = this.dbData.getBytes("image");

        return item;
    }    
    
    public void create(int id, String name, String email, String phone, int age, byte[] image) throws SQLException {
        
        update.executeQuery("Insert into employees (id, name, email, phone, age, image) value(" 
                + "," + id
                + "," + name
                + "," + email
                + "," + phone
                + "," + age
                + "," + image
                + ");"
        );
    }    
    
    public void update(int id, String name, String email, String phone, int age, byte[] image) throws SQLException {
        
        update.executeUpdate("Update from employees set" 
                + "id=" + id
                + "name=" + name
                + "email=" + email
                + "phone=" + phone
                + "age=" + age
                + "image" + image
                + ";"
        );
    }    

    public void delete(int id) throws SQLException {
        delete.executeUpdate("Delete from employees where id=" + id + ";");
    }


}
