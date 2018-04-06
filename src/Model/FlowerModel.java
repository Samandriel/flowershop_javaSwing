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
    ResultSet item;
    private final Object[][] flowers;
    private Object[] flower = new Object[7];
    
    Database db = new Database();
    Connection c = db.Connect();
    Statement create = c.createStatement();
    Statement fetch = c.createStatement();
    Statement update = c.createStatement();
    Statement delete = c.createStatement();

    public FlowerModel() throws SQLException {

        this.item  = fetch.executeQuery("Select *,flower_names.name,flower_types.name,flower_colors.name  from main_flowers\n"
            + "inner join flower_names\n"
            + "inner join flower_types\n"
            + "inner join flower_colors\n"
            + "on main_flowers.flower_name_id = flower_names.id\n"
            + "and main_flowers.flower_type_id = flower_types.id\n"
            + "and main_flowers.flower_color_id = flower_colors.id;");        
        
        
        while(!this.item.isLast()) {
            this.item.next();
            this.count++;
        }
        
         this.item.first();
        this.flowers  = new Object[this.count][7];
        
        for (int i = 0; i < this.flowers.length; i++) {
            this.flowers[i][0] = this.item.getInt("id");
            this.flowers[i][1] = this.item.getString("flower_names.name");
            this.flowers[i][2] = this.item.getString("flower_types.name");
            this.flowers[i][3] = this.item.getString("flower_colors.name");
            this.flowers[i][4] = this.item.getInt("in_stock");
            this.flowers[i][5] = this.item.getDouble("price");
            this.flowers[i][6] = this.item.getBytes("image");
            this.item.next();
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
        
        update.executeQuery("Insert into main_flowers (id, image, flower_name_id, flower_color_id, flower_type_id, in_stock, price) value(" 
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
    
    
    public void update() throws SQLException {
        
        update.executeUpdate("Update from main_flowers set" 
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
    
    public void delete(int id) throws SQLException {
        delete.executeUpdate("Delete from main_flowers where id=" + id + ";");
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
