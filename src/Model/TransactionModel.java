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
public final class TransactionModel {
    private final String tableName = "transactions";
    private int id;
    private int transactionTypeId;
    private int mainFlowerId;
    private int amount;
    private double price;
    private int count;
    private String transactionTypeName;
    private String mainFlowerName;
    
    Database db = new Database();
    Connection c = db.Connect();
    Statement q;
    ResultSet dbData;
    
    private List<TransactionModel> items;
    private TransactionModel item;
    
    public TransactionModel() throws SQLException {
    }

    public TransactionModel(int id, int transactionTypeId, int mainFlowerId, int amount, double price, String transactionTypeName, String mainFlowerName) {
        this.id = id;
        this.transactionTypeId = transactionTypeId;
        this.mainFlowerId = mainFlowerId;
        this.amount = amount;
        this.price = price;
        this.transactionTypeName = transactionTypeName;
        this.mainFlowerName = mainFlowerName;
    }


    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() throws SQLException {

        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public int getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public int getMainFlowerId() {
        return mainFlowerId;
    }

    public void setMainFlowerId(int mainFlowerId) {
        this.mainFlowerId = mainFlowerId;
    }    

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    public String getMainFlowerName() {
        return mainFlowerName;
    }

    public void setMainFlowerName(String mainFlowerName) {
        this.mainFlowerName = mainFlowerName;
    }
    
    public List<TransactionModel> fetch() throws SQLException {
        this.q = c.createStatement();
        this.dbData  = q.executeQuery("Select *,transaction_types.id,main_flowers.id, flower_names.id  from transactions\n"
            + "inner join transaction_types\n"
            + "inner join main_flowers\n"
            + "inner join flower_names\n"
            + "on transactions.transaction_type_id = transaction_types.id\n"
            + "and transactions.main_flower_id = main_flowers.id\n"
            + "and main_flowers.flower_name_id = flower_names.id;");        
        
        while(!this.dbData.isLast()) {
            this.dbData.next();
            this.count++;
        }
        
        this.dbData.first();
        this.items  = new ArrayList<>();
        for (int i = 0; i < this.count; i++) {
            this.id = this.dbData.getInt("transactions.id");
            this.transactionTypeId = this.dbData.getInt("transaction_type_id");
            this.mainFlowerId = this.dbData.getInt("main_flower_id");
            this.amount = this.dbData.getInt("amount");
            this.price = this.dbData.getDouble("price");
            this.mainFlowerName = this.dbData.getString("flower_names.name");
            this.transactionTypeName = this.dbData.getString("transaction_types.name");

            items.add(new TransactionModel(this.id, this.transactionTypeId, this.mainFlowerId, this.amount, this.price, this.mainFlowerName, this.transactionTypeName));
            
            this.dbData.next();
        }
        return items;
    }
    
    public TransactionModel fetchById(int inputId) throws SQLException {
        this.q = c.createStatement();
        this.dbData  = q.executeQuery("Select *,transaction_types.id,main_flowers.id, flower_names.id  from transactions\n"
            + "inner join transaction_types\n"
            + "inner join main_flowers\n"
            + "inner join flower_names\n"
            + "on transactions.transaction_type_id = transaction_types.id\n"
            + "and transactions.main_flower_id = main_flowers.id\n"
            + "and main_flowers.flower_name_id = flower_names.id"
            + "where transactions.id=" + inputId + ";");        
        
        item  = new TransactionModel();
        item.id = this.dbData.getInt("id");
        item.transactionTypeId = this.dbData.getInt("transaction_types.id");
        item.mainFlowerId = this.dbData.getInt("main_flower_id");
        item.amount = this.dbData.getInt("amount");
        item.price = this.dbData.getDouble("price");

        return item;
    }    
    
    public void create(int userId, int transactionTypeId, int mainFlowerId, int amount, double price) throws SQLException {
        FlowerModel flower = new FlowerModel();
        flower.updateAmount(mainFlowerId, amount);
        
        this.q = c.createStatement();
        q.execute("Insert into transactions (transaction_type_id, main_flower_id, amount, price) values (" 
                + transactionTypeId
                + "," + mainFlowerId
                + "," + amount
                + "," + price
                + ");"
                
        );
        
        switch (transactionTypeId) {
            case 1:
                CustomerTransactionModel ct = new CustomerTransactionModel();
                ct.create(transactionTypeId, userId);
                break;
            case 2:
                EmployeeTransactionModel et = new EmployeeTransactionModel();
                et.create(transactionTypeId, userId);
                break;
            default:
                throw new AssertionError();
        }
    }    
    
    public void update(int id, int transactionTypeId, int mainFlowerId, int amount, double price) throws SQLException {
        this.q = c.createStatement();
        q.executeUpdate("Update transactions set" 
                + "transaction_type_id=" + transactionTypeId
                + "main_flower_id=" + mainFlowerId
                + "amount=" + amount
                + "price=" + price
                + "where id=" + id + ";"
        );
    }    

    public void delete(int id) throws SQLException {
        this.q = c.createStatement();
        q.executeUpdate("Delete from transactions where id=" + id + ";");
    }
    


}
