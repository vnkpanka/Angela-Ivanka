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
public class ModelTiket {
    private String NoTiket, Plat, Status, jenis, idgedung;
    private Date HariMasuk;
    private Time JamMasuk;

    public String getIdgedung() {
        return idgedung;
    }

    public void setIdgedung(String idgedung) {
        this.idgedung = idgedung;
    }
    
    
    public String getNoTiket() {
        return NoTiket;
    }

    public void setNoTiket(String NoTiket) {
        this.NoTiket = NoTiket;
    }

    public String getPlat() {
        return Plat;
    }

    public void setPlat(String Plat) {
        this.Plat = Plat;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public Date getHariMasuk() {
        return HariMasuk;
    }

    public void setHariMasuk(Date HariMasuk) {
        this.HariMasuk = HariMasuk;
    }

    public Time getJamMasuk() {
        return JamMasuk;
    }

    public void setJamMasuk(Time JamMasuk) {
        this.JamMasuk = JamMasuk;
    }
    
    
    
}
