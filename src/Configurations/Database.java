/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configurations;
import java.sql.*;
/**
 *
 * @author PC
 */
public class Database {
    
  public boolean loadDriver(){
      boolean s=false;
      try{ 
          Class.forName("com.mysql.jdbc.Driver").newInstance(); 
          s=true;
      }catch (ClassNotFoundException | IllegalAccessException | InstantiationException e){
          System.out.println(e);
      }
//      System.out.println("Database Connection Success");
      return s;
      
  }  
  
 public Connection Connect(){
     loadDriver();
     Connection myCon=null;
     try{
          DatabaseConfig murl = new DatabaseConfig();
          Connection c = DriverManager.getConnection( murl.getHost()+murl.getDB() ,murl.getUser(), murl.getPassword());   
          myCon =c;
     }catch (SQLException e){
          System.out.println(e);
      }
     return myCon;
 }
 
// public Statement mycreatStatement(Connection c){
//     Statement myst=null;
//     try{
//          myst = c.createStatement(); 
//     }catch (Exception e){
//          System.out.println(e);
//      }
//     return myst;
// }
// 
// public boolean mySave(Statement s, String sql){
//     boolean result=false;
//     try{
//          s.execute(sql);
//          result=true;
//     }catch (Exception e){
//          System.out.println(e);
//      }
//     return result;
// }
// 
// public ResultSet myLoad(Statement s, String table){
//     ResultSet myresultset=null;
//     try{
//          myresultset = s.executeQuery(table);          
//     }catch (Exception e){
//          System.out.println(e);
//      }
//     return myresultset;
// }
 
}
