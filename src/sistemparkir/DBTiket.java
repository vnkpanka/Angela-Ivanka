/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemparkir;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BOSS
 */
public class DBTiket {
    private ModelTiket dt=new ModelTiket();

    public ModelTiket getModelTiket() {
        return dt;
    }

    public void setModelTiket(ModelTiket dt) {
        this.dt = dt;
    }
    
    public ObservableList<ModelTiket> Load(){
        try {
            ObservableList<ModelTiket>
                    TableData=FXCollections.observableArrayList();
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbkoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select * from tiket where Status = 'false'");
            int i =1;
            while (rs.next()){
                ModelTiket d=new ModelTiket();
            d.setNoTiket(rs.getString("NoTiket"));
            d.setPlat(rs.getString("Plat"));
            d.setJamMasuk(rs.getTime("JamMasuk"));
            d.setHariMasuk(rs.getDate("HariMasuk"));
            d.setStatus(rs.getString("Status"));
            d.setJenis(rs.getString("jenis"));
            d.setIdgedung(rs.getString("idgedung"));
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
                    "update tiket set Status = ? where  NoTiket = ? ; ");
            con.preparedStatement.setString(1, getModelTiket().getStatus());
            con.preparedStatement.setString(2, getModelTiket().getNoTiket());
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from tiket where NoTiket = '" + nomor + "'");
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
        ("insert into tiket (NoTiket, Plat, JamMasuk, HariMasuk, Status, jenis, idgedung) values (?,?,?,?,?,?,?)");
            con.preparedStatement.setString(1, getModelTiket().getNoTiket());
            con.preparedStatement.setString(2, getModelTiket().getPlat());
            con.preparedStatement.setTime(3, getModelTiket().getJamMasuk());
            con.preparedStatement.setDate(4, getModelTiket().getHariMasuk());
            con.preparedStatement.setString(5, getModelTiket().getStatus());
            con.preparedStatement.setString(6, getModelTiket().getJenis());
            con.preparedStatement.setString(7, getModelTiket().getIdgedung());
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
     
     public boolean delete(String nomor) {
        boolean berhasil = false;
        koneksi con = new koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbkoneksi.prepareStatement("delete from tiket where NoTiket  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

     
    
     public ArrayList<String> CheckKode(String nomor){
         ArrayList<String> List = new ArrayList<>();
        try {
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbkoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select NoTiket from tiket where Plat = '"+nomor+"'");
            int i =1;
            while (rs.next()){
            List.add(rs.getString("NoTiket"));
            }
            con.tutupKoneksi();
        return List;
        }catch (Exception e){
    e.printStackTrace();
    return null;
}
}

    public ModelTiket Search(String nomor){
        ModelTiket m = new ModelTiket();
        try {
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbkoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select * from tiket where NoTiket like '%"+nomor+"%'");
            int i =1;
            while (rs.next()){
            m.setNoTiket(rs.getString("NoTiket"));
            m.setHariMasuk(rs.getDate("HariMasuk"));
            m.setJamMasuk(rs.getTime("JamMasuk"));
            m.setPlat(rs.getString("Plat"));
            m.setStatus(rs.getString("Status"));
            m.setJenis(rs.getString("jenis"));
            m.setIdgedung(rs.getString("idgedung"));
            }
            con.tutupKoneksi();
        return m;
        }catch (Exception e){
    e.printStackTrace();
    return null;
}
    }
            
     
     public ArrayList<String> ListParkiran(){
            ArrayList list = new ArrayList<>();
            try {
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbkoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select NoTiket from tiket where Status = 'false'");
            int i =1;
            while (rs.next()){
            list.add(rs.getString("NoTiket"));
            }
            con.tutupKoneksi();
        return list;
        }catch (Exception e){
    e.printStackTrace();
    return null;
}
            
}


}

