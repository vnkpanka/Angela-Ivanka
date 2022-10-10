/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemparkir;
import java.sql.Time;
import java.sql.Date;

/**
 *
 * @author BOSS
 */
public class Parkir {
    private String plat, NoTiket, Status;
    Time wktmsk;
    Date tglmsk;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    
    
    public String getNoTiket() {
        return NoTiket;
    }

    public void setNoTiket(String notiket) {
        this.NoTiket = notiket;
    }

    
    
    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public Time getWktmsk() {
        return wktmsk;
    }

    public void setWktmsk(Time wktmsk) {
        this.wktmsk = wktmsk;
    }

    public Date getTglmsk() {
        return tglmsk;
    }

    public void setTglmsk(Date tglmsk) {
        this.tglmsk = tglmsk;
    }

   

    
}
