/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Configurations.Database;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
public final class Seeder {

    private static final Database db = new Database();
    private static final Connection c = db.Connect();

    public static void transactionTypeSeeder() throws SQLException {
        Statement transactionTypeSeeder = c.createStatement();
        
        if (tableEmpty("transaction_types")) {
//            Initalize Data
            List<TransactionTypeModel> seeder = new ArrayList<>();
            seeder.add(new TransactionTypeModel(1, "'selling'"));
            seeder.add(new TransactionTypeModel(2, "'purchasing'"));

//            Initialize Query String
            String qString = "Insert into transaction_types (id, name) values";
            for (int i = 0; i < seeder.size(); i++) {
                qString += "(" + seeder.get(i).getId() + "," + seeder.get(i).getName() + ")";
                if (i < seeder.size() - 1) {
                    qString += ",";
                } else {
                    qString += ";";
                }
            };
            
//            Exexcute Query String
            if (qString.isEmpty()) {
                System.out.println("No seed data!!");
            } else {
                System.out.println(qString);
                transactionTypeSeeder.execute(qString);
                System.out.println("Data is seeded successfully!");
            }
        } else {
            System.out.println("Data already exist!!");
        }
    }

    public static void flowerTypeSeeder() throws SQLException {
        Statement flowerTypeSeeder = c.createStatement();
        
        if (tableEmpty("flower_types")) {
//            Initalize Data
            List<FlowerTypeModel> seeder = new ArrayList<>();
            seeder.add(new FlowerTypeModel(1, "'Bouquet'"));
            seeder.add(new FlowerTypeModel(2, "'Basket'"));
            seeder.add(new FlowerTypeModel(3, "'Pot'"));

//            Initialize Query String
            String qString = "Insert into flower_types (id, name) values";
            for (int i = 0; i < seeder.size(); i++) {
                qString += "(" + seeder.get(i).getId() + "," + seeder.get(i).getName() + ")";
                if (i < seeder.size() - 1) {
                    qString += ",";
                } else {
                    qString += ";";
                }
            };
            
//            Exexcute Query String
            if (qString.isEmpty()) {
                System.out.println("No seed data!!");
            } else {
                System.out.println(qString);
                flowerTypeSeeder.execute(qString);
                System.out.println("Data is seeded successfully!");
            }
        } else {
            System.out.println("Data already exist!!");
        }

    }

    public static void flowerColorSeeder() throws SQLException {
        Statement flowerColorSeeder = c.createStatement();
        
        if (tableEmpty("flower_colors")) {
//            Initalize Data
            List<FlowerColorModel> seeder = new ArrayList<>();
            seeder.add(new FlowerColorModel(1, "'Red'"));
            seeder.add(new FlowerColorModel(2, "'Orange'"));
            seeder.add(new FlowerColorModel(3, "'Yellow'"));
            seeder.add(new FlowerColorModel(4, "'Light Green'"));
            seeder.add(new FlowerColorModel(5, "'Green'"));
            seeder.add(new FlowerColorModel(6, "'Light Blue'"));
            seeder.add(new FlowerColorModel(7, "'Blue'"));
            seeder.add(new FlowerColorModel(8, "'Purple'"));
            seeder.add(new FlowerColorModel(9, "'Brown'"));
            seeder.add(new FlowerColorModel(10, "'Black'"));
            seeder.add(new FlowerColorModel(11, "'Grey'"));
            seeder.add(new FlowerColorModel(12, "'White'"));
//            Initialize Query String
            String qString = "Insert into flower_colors (id, name) values";
            for (int i = 0; i < seeder.size(); i++) {
                qString += "(" + seeder.get(i).getId() + "," + seeder.get(i).getName() + ")";
                if (i < seeder.size() - 1) {
                    qString += ",";
                } else {
                    qString += ";";
                }
            };
            
//            Exexcute Query String
            if (qString.isEmpty()) {
                System.out.println("No seed data!!");
            } else {
                System.out.println(qString);
                flowerColorSeeder.execute(qString);
                System.out.println("Data is seeded successfully!");
            }
        } else {
            System.out.println("Data already exist!!");
        }

    }

    public static void flowerNameSeeder() throws SQLException {
        Statement flowerNameSeeder = c.createStatement();
        
        if (tableEmpty("flower_names")) {
//            Initalize Data
            List<FlowerNameModel> seeder = new ArrayList<>();
            seeder.add(new FlowerNameModel(1, "'Rose'"));
            seeder.add(new FlowerNameModel(2, "'Carnation'"));
            seeder.add(new FlowerNameModel(3, "'Tulip'"));
            seeder.add(new FlowerNameModel(4, "'Daisy'"));
            seeder.add(new FlowerNameModel(5, "'Sunflower'"));
            seeder.add(new FlowerNameModel(6, "'Daffodil'"));
            seeder.add(new FlowerNameModel(7, "'Gerbera'"));
            seeder.add(new FlowerNameModel(8, "'Orchid'"));
            seeder.add(new FlowerNameModel(9, "'Iris'"));
            seeder.add(new FlowerNameModel(10, "'Lilac'"));
            seeder.add(new FlowerNameModel(11, "'Gardenia'"));
            seeder.add(new FlowerNameModel(12, "'Jasmine'"));
            seeder.add(new FlowerNameModel(13, "'Magnolia'"));
            seeder.add(new FlowerNameModel(14, "'Hyacinth'"));
            seeder.add(new FlowerNameModel(15, "'Lilly'"));            
//            Initialize Query String
            String qString = "Insert into flower_names (id, name) values";
            for (int i = 0; i < seeder.size(); i++) {
                qString += "(" + seeder.get(i).getId() + "," + seeder.get(i).getName() + ")";
                if (i < seeder.size() - 1) {
                    qString += ",";
                } else {
                    qString += ";";
                }
            };
            
//            Exexcute Query String
            if (qString.isEmpty()) {
                System.out.println("No seed data!!");
            } else {
                System.out.println(qString);
                flowerNameSeeder.execute(qString);
                System.out.println("Data is seeded successfully!");
            }
        } else {
            System.out.println("Data already exist!!");
        }
    }


    public static void employeeSeeder() throws SQLException, IOException {
        Statement employeeSeeder = c.createStatement();
        String filePath = "D:/Pictures/man.jpg";
        File imageFile = new File(filePath);
        byte[] imageFileByte = Files.readAllBytes(imageFile.toPath());
        
        if (tableEmpty("employees")) {
//            Initalize Data
            List<EmployeeModel> seeder = new ArrayList<>();
            seeder.add(new EmployeeModel(0, "'John'", "'example1@email.com'", "'012345678'", 30, imageFileByte));
     
//            Initialize Query String
            String qString = "Insert into employees (id, name, email, phone, age, image) values";
            for (int i = 0; i < seeder.size(); i++) {
                qString += "(" + seeder.get(i).getId() + ","
                        + seeder.get(i).getName() + ","
                        + seeder.get(i).getEmail() + ","
                        + seeder.get(i).getPhone() + ","
                        + seeder.get(i).getAge() + ","
                        + "load_file('" + filePath + "'))";
                
                
                if (i < seeder.size() - 1) {
                    qString += ",";
                } else {
                    qString += ";";
                }
            };
            
//            Exexcute Query String
            if (qString.isEmpty()) {
                System.out.println("No seed data!!");
            } else {
                System.out.println(qString);
                employeeSeeder.execute(qString);
                System.out.println("Data is seeded successfully!");
            }
        } else {
            System.out.println("Data already exist!!");
        }
    }

    public static void customerSeeder() throws SQLException, IOException {
        Statement customerSeeder = c.createStatement();
        
        if (tableEmpty("customers")) {
//            Initalize Data
            List<CustomerModel> seeder = new ArrayList<>();
            seeder.add(new CustomerModel(0, "'Gena'", "'customer1@email.com'", "'0987654321'"));
     
//            Initialize Query String
            String qString = "Insert into customers (id, name, email, phone) values";
            for (int i = 0; i < seeder.size(); i++) {
                qString += "(" + seeder.get(i).getId() + ","
                        + seeder.get(i).getName() + ","
                        + seeder.get(i).getEmail() + ","
                        + seeder.get(i).getPhone() + ")";
                
                
                if (i < seeder.size() - 1) {
                    qString += ",";
                } else {
                    qString += ";";
                }
            };
            
//            Exexcute Query String
            if (qString.isEmpty()) {
                System.out.println("No seed data!!");
            } else {
                System.out.println(qString);
                customerSeeder.execute(qString);
                System.out.println("Data is seeded successfully!");
            }
        } else {
            System.out.println("Data already exist!!");
        }
    }    
    
    public static boolean tableEmpty (String tableName) throws SQLException {
        Statement check = c.createStatement();
        ResultSet firstRow = check.executeQuery("select * from " + tableName + ";");
        return !firstRow.first();
    }
    
    public static void main(String[] args) throws SQLException, IOException {
        transactionTypeSeeder();
        flowerTypeSeeder();
        flowerColorSeeder();
        flowerNameSeeder();
        employeeSeeder();
        customerSeeder();
    }

//    private int id;
//    private byte[] image;
//    private int flowerTypeId;
//    private int flowerColorId;
//    private int flowerNameId;
//    private String name;
//    private String type;
//    private String color;
//    private int stock;
//    private double price;
//    private int count;
//    ResultSet item;
//    private final Object[][] flowers;
//    private Object[] flower = new Object[7];
//    
//
//
//    public Seeder() throws SQLException {
//
//        this.item  = fetch.executeQuery("Select *,flower_names.name,flower_types.name,flower_colors.name  from main_flowers\n"
//            + "inner join flower_names\n"
//            + "inner join flower_types\n"
//            + "inner join flower_colors\n"
//            + "on main_flowers.flower_name_id = flower_names.id\n"
//            + "and main_flowers.flower_type_id = flower_types.id\n"
//            + "and main_flowers.flower_color_id = flower_colors.id;");        
//        
//        
//        while(!this.item.isLast()) {
//            this.item.next();
//            this.count++;
//        }
//        
//         this.item.first();
//        this.flowers  = new Object[this.count][7];
//        
//        for (int i = 0; i < this.flowers.length; i++) {
//            this.flowers[i][0] = this.item.getInt("id");
//            this.flowers[i][1] = this.item.getString("flower_names.name");
//            this.flowers[i][2] = this.item.getString("flower_types.name");
//            this.flowers[i][3] = this.item.getString("flower_colors.name");
//            this.flowers[i][4] = this.item.getInt("in_stock");
//            this.flowers[i][5] = this.item.getDouble("price");
//            this.flowers[i][6] = this.item.getBytes("image");
//            this.item.next();
//        }
//    }
//    
//    public Object[][] getFlowers() {
//        return flowers;
//    }
//    
//    public Object[] flower(int index) {
//        this.flower[0] = this.id = (int) flowers[index][0];
//        this.flower[1] = this.name = flowers[index][1].toString();
//        this.flower[2] = this.type = flowers[index][2].toString();
//        this.flower[3] = this.color = flowers[index][3].toString();
//        this.flower[4] = this.stock = (int) flowers[index][4];
//        this.flower[5] = this.price = (double) flowers[index][5];
//        this.flower[6] = this.image = (byte[]) flowers[index][6];
//        return this.flower;
//    }
//
//    
//    public void create() throws SQLException {
//        
//
//    }
//    
//    
//    public void update() throws SQLException {
//        
//        update.executeUpdate("Update from main_flowers set" 
//                + "id=" + this.id
//                + "image=" + this.image
//                + "flower_name_id=" + this.flowerNameId
//                + "flower_color_id=" + this.flowerColorId
//                + "flower_type_id=" + this.flowerTypeId
//                + "in_stock=" + this.stock
//                + "price=" + this.price
//                + ";"
//        );
//    }
//    
//    public void delete(int id) throws SQLException {
//        delete.executeUpdate("Delete from main_flowers where id=" + id + ";");
//    }
//
//    public void setFlowerTypeId(int flowerTypeId) {
//        this.flowerTypeId = flowerTypeId;
//    }
//
//    public void setFlowerColorId(int flowerColorId) {
//        this.flowerColorId = flowerColorId;
//    }
//
//    public void setFlowerNameId(int flowerNameId) {
//        this.flowerNameId = flowerNameId;
//    }
}
