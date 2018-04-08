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
public class TransactionTypeModel {
    private int id;
    private String name;

    Database db = new Database();
    Connection c = db.Connect();
    Statement q;
    ResultSet dbData;
    private int count;
    List<TransactionTypeModel> items;    
    
    public TransactionTypeModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TransactionTypeModel() {
    }

    TransactionTypeModel(int id, int transactionTypeId, int mainFlowerId, int amount, double price) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<TransactionTypeModel> fetch() throws SQLException {
        this.q = c.createStatement();
        this.dbData  = q.executeQuery("Select * from transaction_types;");        
        
        while(!this.dbData.isLast()) {
            this.dbData.next();
            this.count++;
        }
        
        this.dbData.first();
        this.items  = new ArrayList<>();
        for (int i = 0; i < this.count; i++) {
            this.id = this.dbData.getInt("id");
            this.name = this.dbData.getString("name");

            items.add(new TransactionTypeModel(this.id, this.name));
            
            this.dbData.next();
        }        
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
