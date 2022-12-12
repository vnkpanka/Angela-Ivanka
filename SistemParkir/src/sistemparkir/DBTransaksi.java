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
public class DBTransaksi {
    private ModelTransaksi dt=new ModelTransaksi();
    public ModelTransaksi getModelTransaksi() {return dt;}
    public void setModelTransaksi(ModelTransaksi dt) {this.dt = dt;}
    
    public ObservableList<ModelTransaksi> Load(){
        try {
            ObservableList<ModelTransaksi>
            TableData=FXCollections.observableArrayList();
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbkoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select NoTrans, Gedung, t.NoTiket, HariMasuk, JamMasuk, Jam_Keluar, Total_Bayar, jenis, Plat from transaksi t "
                                                    + "join tiket k on (t.NoTiket = k.NoTiket)");
            int i =1;
            while (rs.next()){
            ModelTransaksi d=new ModelTransaksi();
            d.setNoTrans(rs.getString("NoTrans"));
            d.setNoTiket(rs.getString("NoTiket"));
            d.setHari_Masuk(rs.getDate("HariMasuk"));
            d.setJam_Masuk(rs.getTime("JamMasuk"));
            d.setJam_Keluar(rs.getTime("Jam_Keluar"));
            d.setTotal_Bayar(rs.getInt("Total_Bayar"));
            d.setIdgedung(rs.getString("Gedung"));
            d.setJenis(rs.getString("jenis"));
            d.setPlat(rs.getString("Plat"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from transaksi where NoTrans = '" + nomor + "'");
            while (rs.next()) {   val = rs.getInt("jml");            }
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
            con.preparedStatement = con.dbkoneksi.prepareStatement("insert into transaksi (NoTrans, NoTiket, Jam_Keluar, Hari_Keluar, Total_Bayar, Gedung) values (?,?,?,?,?,?)");
            con.preparedStatement.setString(1, getModelTransaksi().getNoTrans());
            con.preparedStatement.setString(2, getModelTransaksi().getNoTiket());
            con.preparedStatement.setTime(3, getModelTransaksi().getJam_Keluar());
            con.preparedStatement.setDate(4, getModelTransaksi().getHari_Keluar());
            con.preparedStatement.setInt(5, getModelTransaksi().getTotal_Bayar());
            con.preparedStatement.setString(6, getModelTransaksi().getIdgedung());
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
            con.preparedStatement = con.dbkoneksi.prepareStatement("delete from transaksi where NoTrans  = ? ");
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
                    "update transaksi set NoTiket = ?, Jam_Keluar = ?, Hari_Keluar = ?, TotalBayar = ?, Gedung = ?  where  NoTrans = ? ; ");
            con.preparedStatement.setString(1, getModelTransaksi().getNoTiket());
            con.preparedStatement.setTime(2, getModelTransaksi().getJam_Keluar());
            con.preparedStatement.setDate(3, getModelTransaksi().getHari_Keluar());
            con.preparedStatement.setInt(4, getModelTransaksi().getTotal_Bayar());
            con.preparedStatement.setString(5, getModelTransaksi().getNoTrans());
            con.preparedStatement.setString(6, getModelTransaksi().getNoTrans());
            
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
     
     public ArrayList<String> List(){
            ArrayList list = new ArrayList<>();
            try {
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbkoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select NoTrans from transaksi");
            while (rs.next()){
            list.add(rs.getString("NoTrans"));
            }
            con.tutupKoneksi();
        return list;

        }catch (Exception e){
    e.printStackTrace();
    return null;
}
            
        }
    
     public ObservableList<ModelTransaksi> LookUp(String fld,String dt){
   try{
       ObservableList<ModelTransaksi> tableData=FXCollections.observableArrayList();
       koneksi con=new koneksi();
       con.bukaKoneksi();
       con.statement = con.dbkoneksi.createStatement();
       ResultSet rs = con.statement.executeQuery("select NoTrans, Gedung, t.NoTiket, HariMasuk, JamMasuk, Jam_Keluar, Total_Bayar from transaksi t "
                                                    + "join tiket k on (t.NoTiket = k.NoTiket) where "+fld+" like '%"+dt+"%'");
       int i=1;
       while (rs.next()){
                ModelTransaksi d=new ModelTransaksi();
            d.setNoTrans(rs.getString("NoTrans"));
            d.setNoTiket(rs.getString("NoTiket"));
            d.setHari_Masuk(rs.getDate("HariMasuk"));
            d.setJam_Masuk(rs.getTime("JamMasuk"));
            d.setJam_Keluar(rs.getTime("Jam_Keluar"));
            d.setTotal_Bayar(rs.getInt("Total_Bayar"));
            d.setIdgedung(rs.getString("Gedung"));
                tableData.add(d);
                i++;
       }
       con.tutupKoneksi();
       return tableData;
   } catch (SQLException ex){       
       ex.printStackTrace();       
       return null;      
   }
}
}
