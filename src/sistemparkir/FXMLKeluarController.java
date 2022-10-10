/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemparkir;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static sistemparkir.FXML_MasukController.dt;

/**
 * FXML Controller class
 *
 * @author BOSS
 */
public class FXMLKeluarController implements Initializable {

    long biaya = 0;
    
    private boolean editdata=false;
    @FXML
    private RadioButton rdbmobil;
    @FXML
    private ToggleGroup Jenis;
    @FXML
    private RadioButton rdbmotor;
    @FXML
    private Button btnhitung;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnexit;
    @FXML
    private TextField txtbiaya;
    @FXML
    private TextField txtdspmasuk;
    @FXML
    private TextField txtdspkeluar;
    @FXML
    private ComboBox<String> CBTiket;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<String> List = FXML_UtamaController.dtparkir.List();
        CBTiket.getItems().addAll(List);
        
    }    

    @FXML
    private void hitungklik(ActionEvent event) {
        AmbilTiket Tiket = new AmbilTiket();
        Tiket = FXML_UtamaController.dtparkir.CariTiket(CBTiket.getSelectionModel().getSelectedItem());
        txtdspmasuk.setText(String.valueOf(Tiket.getJamMasuk()));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        txtdspkeluar.setText(sdf.format(new java.util.Date()));
        long time = (Time.valueOf(sdf.format(new java.util.Date())).getTime() - Tiket.getJamMasuk().getTime());
        long diffHours = (time / (60 * 60 * 1000) % 24);
        diffHours += 1;
        //int biaya = diffHours * 
        if (rdbmobil.isSelected()){
            biaya = diffHours * 3000;
        }
        else if (rdbmotor.isSelected()){
            biaya = diffHours * 2000;
        }
        txtbiaya.setText(String.valueOf(biaya));
        
        }

    @FXML
    private void hapusklik(ActionEvent event) {
        
        rdbmobil.setSelected(false);
        rdbmotor.setSelected(false);
        txtdspmasuk.setText("");
        txtdspkeluar.setText("");
        txtbiaya.setText("");
    }

    @FXML
    private void exitklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_Utama.fxml"));    
        Parent root = (Parent)loader.load();        
        Scene scene = new Scene(root);        
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        
        stg.setIconified(false);        
        stg.setScene(scene);
        stg.showAndWait();
        txtbiaya.getScene().getWindow().hide();
        } catch (IOException e){   
            e.printStackTrace();   
        }
        
    }

    @FXML
    private void Simpanklik(ActionEvent event) {
        Transaksi n=new Transaksi();
        AmbilTiket Tiket = new AmbilTiket();
        Tiket = FXML_UtamaController.dtparkir.CariTiket(CBTiket.getSelectionModel().getSelectedItem());
        
        n.setNoTiket(Tiket.getNoTiket());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd:mm:yyyy");
        n.setJam_Keluar(Time.valueOf(sdf.format(new java.util.Date())));
        n.setHari_Keluar(null);
        n.setTotal_Bayar((int)biaya);
        
        ArrayList<String> List = FXML_UtamaController.dttrans.List();
        String urutan;
        if(!List.isEmpty()){
           int urut = List.size();
           if(urut<100){urutan = "00"+String.valueOf(urut);}
           else if(urut<10){urutan = "0"+String.valueOf(urut);}
           else {urutan = String.valueOf(urut);}
       } else {
           urutan ="001";
       }
        n.setNoTrans(urutan);
        
        FXML_UtamaController.dttrans.setTransaksi(n);
        if(editdata){
            if(FXML_UtamaController.dttrans.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   CBTiket.setEditable(true);        hapusklik(event);                
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
               a.showAndWait();                    
            }
        }else if(FXML_UtamaController.dttrans.validasi(n.getNoTiket())<=0){
            if(FXML_UtamaController.dttrans.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            hapusklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            CBTiket.requestFocus();
    }
    

    
    }
}

