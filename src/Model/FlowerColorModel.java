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
public class FlowerColorModel {
    private int id;
    private String name;
    
    Database db = new Database();
    Connection c = db.Connect();
    Statement q;
    ResultSet dbData;
    private int count;
    List<FlowerColorModel> items;

    public FlowerColorModel() {
    }
    
    public FlowerColorModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<FlowerColorModel> fetch() throws SQLException {
        this.q = c.createStatement();
        this.dbData  = q.executeQuery("Select * from flower_colors;");        
        
        while(!this.dbData.isLast()) {
            this.dbData.next();
            this.count++;
        }
        
        this.dbData.first();
        this.items  = new ArrayList<>();
        for (int i = 0; i < this.count; i++) {
            this.id = this.dbData.getInt("id");
            this.name = this.dbData.getString("name");

            items.add(new FlowerColorModel(this.id, this.name));
            
            this.dbData.next();
        }
        c.close();
        return this.items;
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

    public int getCount() {
        return count;
    }
    
}
