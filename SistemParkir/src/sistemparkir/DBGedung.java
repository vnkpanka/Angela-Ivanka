/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemparkir;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BOSS
 */
public class DBGedung {
    private ModelGedung dt=new ModelGedung();

    public ModelGedung getModelGedung() {
        return dt;
    }

    public void setModelGedung(ModelGedung dt) {
        this.dt = dt;
    }
    
    public ObservableList<ModelGedung> Load(){
        try {
            ObservableList<ModelGedung>
                    TableData=FXCollections.observableArrayList();
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbkoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select * from gedung");
            int i =1;
            while (rs.next()){
                ModelGedung d=new ModelGedung();
            d.setIdgedung(rs.getString("idgedung"));
            d.setKmobil(rs.getInt("kmobil"));
            d.setKmotor(rs.getInt("kmotor"));
            TableData.add(d);
            i++;
            }
            con.tutupKoneksi();
        return TableData;
        }catch (Exception e){
    e.printStackTrace();
    return null;
}
        
    }
    
    public boolean update() {
        boolean berhasil = false;
        koneksi con = new koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbkoneksi.prepareStatement(
                    "update gedung set kmobil = ?, kmotor = ? where  idgedung = ?");
            con.preparedStatement.setInt(1, getModelGedung().getKmobil());
            con.preparedStatement.setInt(2, getModelGedung().getKmotor());
            con.preparedStatement.setString(3, getModelGedung().getIdgedung());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
    
     public int validasi(String nomor) {
        int val = 0;
        try {  
            koneksi con = new koneksi();     
            con.bukaKoneksi();   
            con.statement = con.dbkoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from gedung where idgedung = '" + nomor + "'");
            while (rs.next()) {val = rs.getInt("jml");}
            con.tutupKoneksi();
        } catch (SQLException e) 
        {            
            e.printStackTrace();        
        }
        return val;
    }        

     public boolean insert() {
        boolean berhasil = false;    
        koneksi con = new koneksi();
        try {         
            con.bukaKoneksi();
            con.preparedStatement = con.dbkoneksi.prepareStatement
        ("insert into tiket (idgedung, kmobil, kmotor) values (?,?,?)");
            con.preparedStatement.setString(1, getModelGedung().getIdgedung());
            con.preparedStatement.setInt(2, getModelGedung().getKmobil());
            con.preparedStatement.setInt(3, getModelGedung().getKmotor());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) 
        {            
            e.printStackTrace();            
            berhasil = false;
        } finally 
        {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }

    public ModelGedung Search(String nomor){
        ModelGedung m = new ModelGedung();
        try {
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbkoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from gedung where idgedung = '"+nomor+"'");
            int i =1;
            while (rs.next()){
            m.setIdgedung(rs.getString("idgedung"));
            m.setKmobil(rs.getInt("kmobil"));
            m.setKmotor(rs.getInt("kmotor"));
            }
            con.tutupKoneksi();
        return m;
        }catch (Exception e){
    e.printStackTrace();
    return null;
}
    }
            
     
     public ArrayList<String> List(){
            ArrayList list = new ArrayList<>();
            try {
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbkoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select idgedung from gedung");
            int i =1;
            while (rs.next()){
            list.add(rs.getString("idgedung"));
            }
            con.tutupKoneksi();
        return list;
        }catch (Exception e){
    e.printStackTrace();
    return null;
}
            
}
}
