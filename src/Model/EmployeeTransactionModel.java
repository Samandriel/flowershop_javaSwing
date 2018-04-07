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
public final class EmployeeTransactionModel {
    private int transactionId;
    private int employeeId;
    private int count;
    
    Database db = new Database();
    Connection c = db.Connect();
    Statement q;
    Statement fetch;
    Statement update;
    Statement delete;
    ResultSet dbData;
    
    private List<EmployeeTransactionModel> items;
    private EmployeeTransactionModel item;
    
    public EmployeeTransactionModel() throws SQLException {
    }

    public EmployeeTransactionModel(int transactionId,int employeeId) {
        this.transactionId = transactionId;
        this.employeeId = employeeId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    
    public List<EmployeeTransactionModel> fetch() throws SQLException {
        this.fetch = c.createStatement();
        this.dbData  = fetch.executeQuery("Select * from employee_transactions;");        
        
        while(!this.dbData.isLast()) {
            this.dbData.next();
            this.count++;
        }
        
        this.dbData.first();
        this.items  = new ArrayList<>();
        for (int i = 0; i < this.count; i++) {
            this.transactionId = this.dbData.getInt("transaction_id");
            this.employeeId = this.dbData.getInt("employee_id");
            
            items.add(new EmployeeTransactionModel(this.transactionId, this.employeeId));
            
            this.dbData.next();
        }
        return items;
    }
      
    
    public void create(int transactionId,int employeeId) throws SQLException {
        this.q = c.createStatement();
        q.execute("Insert into employee_transactions (transaction_id, employee_id) value(" 
                + transactionId
                + "," + employeeId
                + ");"
        );
    }    
    
    public void update(int transactionId,int employeeId) throws SQLException {
        
        update.executeUpdate("Update from employee_transactions set" 
                + "transaction_id=" + transactionId
                + "employee_id=" + employeeId
                + ";"
        );
    }    

    public void delete(int id) throws SQLException {
        delete.executeUpdate("Delete from employee_transactions where id=" + id + ";");
    }


}
