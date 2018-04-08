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
    private List<FlowerModel> items;
    
    Database db = new Database();
    Connection c = db.Connect();
    Statement q;

    public FlowerModel() throws SQLException {
    }

    public FlowerModel(int id, int flowerTypeId, String type, int flowerNameId, String name, int flowerColorId, String color, int stock, double price, byte[] image) {
        this.id = id;
        this.image = image;
        this.flowerTypeId = flowerTypeId;
        this.flowerColorId = flowerColorId;
        this.flowerNameId = flowerNameId;
        this.stock = stock;
        this.price = price;
        this.type = type;
        this.name = name;
        this.color = color;
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

    public int getFlowerTypeId() {
        return flowerTypeId;
    }

    public int getFlowerColorId() {
        return flowerColorId;
    }

    public int getFlowerNameId() {
        return flowerNameId;
    }

    
    public void create(int flowerTypeId, int flowerNameId, int flowerColorId, int stock, double price, String image) throws SQLException {
        this.q = c.createStatement();
        String qString = "Insert into main_flowers (flower_name_id, flower_color_id, flower_type_id, in_stock, price, image) value(" 
                + flowerNameId
                + "," + flowerColorId
                + "," + flowerTypeId
                + "," + stock
                + "," + price
                + ", load_file('" + image + "'));";
        q.execute(qString);
        System.out.println(qString);
        System.out.println("Created successfully");
        c.close();
    }
    
    
    public void update(int id, int flowerTypeId, int flowerNameId, int flowerColorId, int stock, double price, String image) throws SQLException {
        this.q = c.createStatement();
        String qString;
        if (image.contentEquals("")) {
            qString = "Update main_flowers set " 
                + "flower_name_id=" + flowerNameId
                + ", flower_color_id=" + flowerColorId
                + ", flower_type_id=" + flowerTypeId
                + ", in_stock=" + stock
                + ", price=" + price
                + " where id=" + id + ";";
        } else {
            qString = "Update main_flowers set" 
                + "flower_name_id=" + flowerNameId
                + ", flower_color_id=" + flowerColorId
                + ", flower_type_id=" + flowerTypeId
                + ", in_stock=" + stock
                + ", price=" + price
                + ", image= load_file('" + image + "')"
                + " where id=" + id + ";";
        }
        
        q.execute(qString);
        System.out.println(qString);
        System.out.println("Created successfully");
        c.close();
    }


    public void updateAmount(int id, int amount) throws SQLException {
        this.q = c.createStatement();
        ResultSet data  = q.executeQuery("Select * from main_flowers where id=" + id + ";");
        data.first();
        int currentAmount = data.getInt("in_stock");
        int newAmount = currentAmount - amount;

        this.q = c.createStatement();
        q.executeUpdate("Update main_flowers set in_stock=" + newAmount + " where id=" + id + ";");
        c.close();
    }
    
    public void delete(int id) throws SQLException {
        this.q = c.createStatement();
        String qString = "Delete from main_flowers where id=" + id + ";";
        q.executeUpdate(qString);
        System.out.println(qString);
        System.out.println("Created successfully");
        c.close();
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

    public List<FlowerModel> fetch() throws SQLException {
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
        this.items  = new ArrayList<>();
        
        for (int i = 0; i < this.count; i++) {
            this.id= this.dbData.getInt("id");
            this.flowerTypeId = this.dbData.getInt("flower_types.id");
            this.flowerNameId = this.dbData.getInt("flower_names.id");
            this.flowerColorId = this.dbData.getInt("flower_colors.id");
            this.stock = this.dbData.getInt("in_stock");
            this.price = this.dbData.getDouble("price");
            this.image = this.dbData.getBytes("image");
            this.type = this.dbData.getString("flower_types.name");
            this.name = this.dbData.getString("flower_names.name");
            this.color = this.dbData.getString("flower_colors.name");
            
            items.add(new FlowerModel(this.id, this.flowerTypeId, this.type, this.flowerNameId, this.name, this.flowerColorId, this.color, this.stock, this.price, this.image));
            
            this.dbData.next();
        }
        c.close();
        return items;        
    }
}
