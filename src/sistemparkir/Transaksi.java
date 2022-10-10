/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemparkir;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author BOSS
 */
public class Transaksi {
    private String NoTrans, NoTiket;
    private Time Jam_Keluar, Jam_Masuk;
    private Date Hari_Keluar;
    private int Total_Bayar;

    public Time getJam_Masuk() {
        return Jam_Masuk;
    }

    public void setJam_Masuk(Time Jam_Masuk) {
        this.Jam_Masuk = Jam_Masuk;
    }

    
    
    public String getNoTrans() {
        return NoTrans;
    }

    public void setNoTrans(String NoTrans) {
        this.NoTrans = NoTrans;
    }

    public String getNoTiket() {
        return NoTiket;
    }

    public void setNoTiket(String NoTiket) {
        this.NoTiket = NoTiket;
    }

    public Time getJam_Keluar() {
        return Jam_Keluar;
    }

    public void setJam_Keluar(Time Jam_Keluar) {
        this.Jam_Keluar = Jam_Keluar;
    }

    public Date getHari_Keluar() {
        return Hari_Keluar;
    }

    public void setHari_Keluar(Date Hari_Keluar) {
        this.Hari_Keluar = Hari_Keluar;
    }

    public int getTotal_Bayar() {
        return Total_Bayar;
    }

    public void setTotal_Bayar(int Total_Bayar) {
        this.Total_Bayar = Total_Bayar;
    }
    
    
    
}
