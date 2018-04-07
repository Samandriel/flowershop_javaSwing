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

/**
 *
 * @author harri
 */
public final class FlowerModel {
    private int id;
    private byte[] image;
    private int flowerTypeId;
    private int flowerColorId;
    private int flowerNameId;
    private String name;
    private String type;
    private String color;
    private int stock;
    private double price;
    private int count;
    ResultSet dbData;
    private final Object[][] flowers;
    private Object[] flower = new Object[7];
    
    Database db = new Database();
    Connection c = db.Connect();
    Statement q;

    public FlowerModel() throws SQLException {
        this.q = c.createStatement();
        this.dbData  = q.executeQuery("Select *,flower_names.name,flower_types.name,flower_colors.name  from main_flowers\n"
            + "inner join flower_names\n"
            + "inner join flower_types\n"
            + "inner join flower_colors\n"
            + "on main_flowers.flower_name_id = flower_names.id\n"
            + "and main_flowers.flower_type_id = flower_types.id\n"
            + "and main_flowers.flower_color_id = flower_colors.id;");        
        
        
        while(!this.dbData.isLast()) {
            this.dbData.next();
            this.count++;
        }
        
         this.dbData.first();
        this.flowers  = new Object[this.count][7];
        
        for (int i = 0; i < this.flowers.length; i++) {
            this.flowers[i][0] = this.dbData.getInt("id");
            this.flowers[i][1] = this.dbData.getString("flower_names.name");
            this.flowers[i][2] = this.dbData.getString("flower_types.name");
            this.flowers[i][3] = this.dbData.getString("flower_colors.name");
            this.flowers[i][4] = this.dbData.getInt("in_stock");
            this.flowers[i][5] = this.dbData.getDouble("price");
            this.flowers[i][6] = this.dbData.getBytes("image");
            this.dbData.next();
        }
    }
    
    public Object[][] getFlowers() {
        return flowers;
    }
    
    public Object[] flower(int index) {
        this.flower[0] = this.id = (int) flowers[index][0];
        this.flower[1] = this.name = flowers[index][1].toString();
        this.flower[2] = this.type = flowers[index][2].toString();
        this.flower[3] = this.color = flowers[index][3].toString();
        this.flower[4] = this.stock = (int) flowers[index][4];
        this.flower[5] = this.price = (double) flowers[index][5];
        this.flower[6] = this.image = (byte[]) flowers[index][6];
        return this.flower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    
    public void create() throws SQLException {
        this.q = c.createStatement();
        q.executeQuery("Insert into main_flowers (id, image, flower_name_id, flower_color_id, flower_type_id, in_stock, price) value(" 
                + "," + this.id
                + "," + this.image
                + "," + this.flowerNameId
                + "," + this.flowerColorId
                + "," + this.flowerTypeId
                + "," + this.stock
                + "," + this.price
                + ");"
        );
    }
    
//    public EmployeeModel fetchById(int inputId) throws SQLException {
     
//        
//        dbData  = new EmployeeModel();
//        dbData.id = this.dbData.getInt("id");
//        dbData.name = this.dbData.getString("name");
//        dbData.email = this.dbData.getString("email");
//        dbData.phone = this.dbData.getString("phone");
//        dbData.age = this.dbData.getInt("age");
//        dbData.image = this.dbData.getBytes("image");
//
//        return dbData;
//    } 
    
    public void update(int id) throws SQLException {
        this.q = c.createStatement();
        q.executeUpdate("Update main_flowers set" 
                + "id=" + this.id
                + "image=" + this.image
                + "flower_name_id=" + this.flowerNameId
                + "flower_color_id=" + this.flowerColorId
                + "flower_type_id=" + this.flowerTypeId
                + "in_stock=" + this.stock
                + "price=" + this.price
                + ";"
        );
    }


    public void updateAmount(int id, int amount) throws SQLException {
        this.q = c.createStatement();
        ResultSet data  = q.executeQuery("Select * from main_flowers where id=" + id + ";");
        data.first();
        int currentAmount = data.getInt("in_stock");
        int newAmount = currentAmount - amount;

        this.q = c.createStatement();
        q.executeUpdate("Update main_flowers set in_stock=" + newAmount + " where id=" + id + ";");
    }
    
    public void delete(int id) throws SQLException {
        this.q = c.createStatement();
        q.executeUpdate("Delete from main_flowers where id=" + id + ";");
    }

    public void setFlowerTypeId(int flowerTypeId) {
        this.flowerTypeId = flowerTypeId;
    }

    public void setFlowerColorId(int flowerColorId) {
        this.flowerColorId = flowerColorId;
    }

    public void setFlowerNameId(int flowerNameId) {
        this.flowerNameId = flowerNameId;
    }
}
