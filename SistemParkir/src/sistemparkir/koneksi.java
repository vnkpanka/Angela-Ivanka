/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemparkir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;



/**
 *
 * @author BOSS
 */
public class koneksi {
    public Connection dbkoneksi;
    public Statement statement;
    public PreparedStatement preparedStatement;
    public koneksi() {this.dbkoneksi = null;}
    
    public void bukaKoneksi() {
        try {    
            Class.forName("com.mysql.jdbc.Driver");
            dbkoneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkir?user=root&password=ivanka");
        } catch (Exception e) {
            e.printStackTrace();        }    }
    public void tutupKoneksi() {
        try { if (statement != null) {    
            statement.close();           
        }
            if (preparedStatement != null) {     
                preparedStatement.close();            
            }
            if (dbkoneksi != null) {            
                dbkoneksi.close();            
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());        
        }    }
}
