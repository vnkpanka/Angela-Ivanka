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
    private Parkir dt=new Parkir();

    public Parkir getParkir() {
        return dt;
    }

    public void setParkir(Parkir dt) {
        this.dt = dt;
    }
    
    public ObservableList<Parkir> Load(){
        try {
            ObservableList<Parkir>
                    TableData=FXCollections.observableArrayList();
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbkoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select NoTiket, Plat, JamMasuk, HariMasuk, Status from tiket");
            int i =1;
            while (rs.next()){
                Parkir d=new Parkir();
            d.setNoTiket(rs.getString("NoTiket"));
            d.setPlat(rs.getString("Plat"));
            d.setWktmsk(rs.getTime("JamMasuk"));
            d.setTglmsk(rs.getDate("HariMasuk"));
            d.setStatus(rs.getString("Status"));
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
        ("insert into tiket (NoTiket, Plat, JamMasuk, HariMasuk, Status) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getParkir().getNoTiket());
            con.preparedStatement.setString(2, getParkir().getPlat());
            con.preparedStatement.setTime(3, getParkir().getWktmsk());
            con.preparedStatement.setDate(4, getParkir().getTglmsk());
            con.preparedStatement.setString(5, getParkir().getStatus());
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

     public boolean update() {
        boolean berhasil = false;
        koneksi con = new koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbkoneksi.prepareStatement(
                    "update tiket set Plat = ?, JamMasuk = ?, HariMasuk = ?, Status = ? where  NoTiket = ? ; ");
            con.preparedStatement.setString(1, getParkir().getPlat());
            con.preparedStatement.setTime(2, getParkir().getWktmsk());
            con.preparedStatement.setDate(3, getParkir().getTglmsk());
            con.preparedStatement.setString(4, getParkir().getStatus());
            con.preparedStatement.setString(5, getParkir().getNoTiket());
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
     /*
     public ObservableList<Parkir> LookUp(String fld,String dt){
   try{
       ObservableList<Parkir> tableData=FXCollections.observableArrayList();
       koneksi con=new koneksi();
       con.bukaKoneksi();
       con.statement = con.dbkoneksi.createStatement();
       ResultSet rs = con.statement.executeQuery("Select NoTiket, JamMasuk, HariMasuk from tiket where "
               +fld+" like '%"+dt+"%'");
       int i=1;
       while (rs.next()){
                Parkir d=new Parkir();
                d.setNoTiket(rs.getString("NoTiket"));
                d.setPlat(rs.getString("Plat"));
                d.setWktmsk(rs.getTime("Jam_Masuk"));
                d.setTglmsk(rs.getDate("Hari_Masuk"));
                tableData.add(d);
                i++;
       }
       con.tutupKoneksi();
       return tableData;
   } catch (SQLException ex){       
       ex.printStackTrace();       
       return null;      
   }
}*/
     
     public ArrayList<String> CheckKode(String nomor){
         ArrayList<String> List = new ArrayList<>();
        try {
            ObservableList<Parkir>
                    TableData=FXCollections.observableArrayList();
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbkoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select NoTiket from tiket where NoTiket like '%"+nomor+"%'");
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

     public AmbilTiket CariTiket(String Plat){
        try {
            AmbilTiket list = new AmbilTiket();
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbkoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select NoTiket, Plat, JamMasuk from tiket where Plat = '" + Plat + "' and Status = false");
                     
            int i =1;
            while (rs.next()){
                list.setNoTiket(rs.getString("NoTiket"));
                list.setJamMasuk(rs.getTime("JamMasuk"));
            }
            con.tutupKoneksi();
        return list;
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
            "Select Plat from tiket where Status = false");
            int i =1;
            while (rs.next()){
            list.add(rs.getString("Plat"));
            }
            con.tutupKoneksi();
        return list;
        }catch (Exception e){
    e.printStackTrace();
    return null;
}
            
        }


}

