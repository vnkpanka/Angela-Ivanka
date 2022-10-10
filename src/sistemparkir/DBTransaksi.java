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
    private Transaksi dt=new Transaksi();
    public Transaksi getTransaksi() {return dt;}
    public void setTransaksi(Transaksi dt) {this.dt = dt;}
    
    public ObservableList<Transaksi> Load(){
        try {
            ObservableList<Transaksi>
            TableData=FXCollections.observableArrayList();
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbkoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select NoTrans, t.NoTiket, JamMasuk, Jam_Keluar, Total_Bayar from transaksi t join tiket k on (t.NoTiket = k.NoTiket)");
            int i =1;
            while (rs.next()){
            Transaksi d=new Transaksi();
            d.setNoTrans(rs.getString("NoTrans"));
            d.setNoTiket(rs.getString("NoTiket"));
            d.setJam_Masuk(rs.getTime("JamMasuk"));
            d.setJam_Keluar(rs.getTime("Jam_Keluar"));
            d.setTotal_Bayar(rs.getInt("Total_Bayar"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from siswa where NoTrans = '" + nomor + "'");
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
            con.preparedStatement = con.dbkoneksi.prepareStatement("insert into transaksi (NoTrans, NoTiket, Jam_Keluar, Hari_Keluar, Total_Bayar) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getTransaksi().getNoTrans());
            con.preparedStatement.setString(2, getTransaksi().getNoTiket());
            con.preparedStatement.setTime(3, getTransaksi().getJam_Keluar());
            con.preparedStatement.setDate(4, getTransaksi().getHari_Keluar());
            con.preparedStatement.setInt(5, getTransaksi().getTotal_Bayar());
            
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
                    "update siswa set NoTiket = ?, Jam_Keluar = ?, Hari_Keluar = ?, TotalBayar = ?  where  NoTrans = ? ; ");
            con.preparedStatement.setString(1, getTransaksi().getNoTiket());
            con.preparedStatement.setTime(2, getTransaksi().getJam_Keluar());
            con.preparedStatement.setDate(3, getTransaksi().getHari_Keluar());
            con.preparedStatement.setInt(4, getTransaksi().getTotal_Bayar());
            con.preparedStatement.setString(5, getTransaksi().getNoTrans());
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
     /*
     public ObservableList<Transaksi> LookUp(String fld,String dt){
   try{
       ObservableList<SiswaModel> tableData=FXCollections.observableArrayList();
       koneksi con=new koneksi();
       con.bukaKoneksi();
       con.statement = con.dbkoneksi.createStatement();
       ResultSet rs = con.statement.executeQuery("Select NPM, Nama, Alamat from siswa where "+fld+" like '%"+dt+"%'");
       int i=1;
       while (rs.next()){
                SiswaModel d=new SiswaModel();
                d.setNPM(rs.getString("NPM"));
                d.setNama(rs.getString("Nama"));
                d.setAlamat(rs.getString("Alamat"));
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
}
