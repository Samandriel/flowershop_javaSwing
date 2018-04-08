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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author harri
 */
public class FlowerTypeModel {
    private int id;
    private String name;
    private int count;
    
    Database db = new Database();
    Connection c = db.Connect();
    Statement q;
    ResultSet dbData;
    
    List<FlowerTypeModel> items;

    public FlowerTypeModel() {
    }
    
    
    
    public FlowerTypeModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<FlowerTypeModel> fetch() throws SQLException {
        this.q = c.createStatement();
        this.dbData  = q.executeQuery("Select * from flower_types;");        
        
        while(!this.dbData.isLast()) {
            this.dbData.next();
            this.count++;
        }
        
        this.dbData.first();
        this.items  = new ArrayList<>();
        for (int i = 0; i < this.count; i++) {
            this.id = this.dbData.getInt("id");
            this.name = this.dbData.getString("name");

            items.add(new FlowerTypeModel(this.id, this.name));
            
            this.dbData.next();
        }
        c.close();        
        return this.items;
    }

    public int getCount() {
        return count;
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
}
