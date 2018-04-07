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
public final class CustomerTransactionModel {
    private int transactionId;
    private int customerId;
    private int count;
    
    Database db = new Database();
    Connection c = db.Connect();
    Statement q;
    Statement fetch;
    Statement update;
    Statement delete;
    ResultSet dbData;
    
    private List<CustomerTransactionModel> items;
    private CustomerTransactionModel item;
    
    public CustomerTransactionModel() throws SQLException {
    }

    public CustomerTransactionModel(int transactionId,int customerId) {
        this.transactionId = transactionId;
        this.customerId = customerId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    
    public List<CustomerTransactionModel> fetch() throws SQLException {
        this.fetch = c.createStatement();
        this.dbData  = fetch.executeQuery("Select * from customer_transactions;");        
        
        while(!this.dbData.isLast()) {
            this.dbData.next();
            this.count++;
        }
        
        this.dbData.first();
        this.items  = new ArrayList<>();
        for (int i = 0; i < this.count; i++) {
            this.transactionId = this.dbData.getInt("transaction_id");
            this.customerId = this.dbData.getInt("customer_id");
            
            items.add(new CustomerTransactionModel(this.transactionId, this.customerId));
            
            this.dbData.next();
        }
        return items;
    }
      
    
    public void create(int transactionId,int customerId) throws SQLException {
        this.q = c.createStatement();
        q.execute("Insert into customer_transactions (transaction_id, customer_id) value(" 
                + transactionId
                + "," + customerId
                + ");"
        );
    }    
    
    public void update(int transactionId,int customerId) throws SQLException {
        
        update.executeUpdate("Update from customer_transactions set" 
                + "transaction_id=" + transactionId
                + "customer=" + customerId
                + ";"
        );
    }    

    public void delete(int id) throws SQLException {
        delete.executeUpdate("Delete from customer_transactions where id=" + id + ";");
    }


}
