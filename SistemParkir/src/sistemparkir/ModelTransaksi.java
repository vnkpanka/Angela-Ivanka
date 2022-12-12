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
public class ModelTransaksi {
    private String NoTrans, NoTiket, idgedung, Plat, jenis;
    private Date Hari_Masuk, Hari_Keluar;
    private Time Jam_Masuk, Jam_Keluar;
    private int Total_Bayar;

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    
    public int getTotal_Bayar() {
        return Total_Bayar;
    }

    public void setTotal_Bayar(int Total_Bayar) {
        this.Total_Bayar = Total_Bayar;
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

    public String getIdgedung() {
        return idgedung;
    }

    public void setIdgedung(String idgedung) {
        this.idgedung = idgedung;
    }
   

    public String getPlat() {
        return Plat;
    }

    public void setPlat(String Plat) {
        this.Plat = Plat;
    }

    public Date getHari_Masuk() {
        return Hari_Masuk;
    }

    public void setHari_Masuk(Date Hari_Masuk) {
        this.Hari_Masuk = Hari_Masuk;
    }

    public Date getHari_Keluar() {
        return Hari_Keluar;
    }

    public void setHari_Keluar(Date Hari_Keluar) {
        this.Hari_Keluar = Hari_Keluar;
    }

    public Time getJam_Masuk() {
        return Jam_Masuk;
    }

    public void setJam_Masuk(Time Jam_Masuk) {
        this.Jam_Masuk = Jam_Masuk;
    }

    public Time getJam_Keluar() {
        return Jam_Keluar;
    }

    public void setJam_Keluar(Time Jam_Keluar) {
        this.Jam_Keluar = Jam_Keluar;
    }
    
}
