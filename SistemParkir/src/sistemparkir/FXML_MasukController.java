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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BOSS
 */
public class FXML_MasukController implements Initializable {
    public static ModelTiket dt = new ModelTiket();
    
    @FXML
    private TextField txtplat;
    @FXML
    private Button btnwktmsk;
    @FXML
    private Button btnexit;
    @FXML
    private Button btnhapus;
    @FXML
    private RadioButton rdbmotor;
    @FXML
    private ToggleGroup Jenis;
    @FXML
    private RadioButton rdbmobil;
    @FXML
    private DatePicker tanggal;
    @FXML
    private ComboBox<String> CBGedung;
    @FXML
    private Label lmobil;
    @FXML
    private Label lmotor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rdbmobil.setSelected(true);
        CBGedung.getItems().addAll(FXML_UtamaController.dtgedung.List());
    }    

  
    //java sql time,time get text sdf,time valueof 
    //java util time, set text 
    @FXML
    private void wktmskklik(ActionEvent event) {
        dt.setPlat(txtplat.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy:MM:dd");
        dt.setJamMasuk(Time.valueOf(sdf.format(new java.util.Date())));
        dt.setHariMasuk(Date.valueOf(tanggal.getValue()));
        dt.setIdgedung(CBGedung.getSelectionModel().getSelectedItem());
        if(rdbmobil.isSelected()){
            dt.setJenis("mobil");
        } else if(rdbmotor.isSelected()){
            dt.setJenis("motor");
        }
        
        ArrayList<String> List = FXML_UtamaController.dtparkir.CheckKode(txtplat.getText());
        String urutan;
        if(!List.isEmpty()){
           int urut = List.size() + 1;
           if(urut<10){urutan = "0"+String.valueOf(urut);}
           else {urutan = String.valueOf(urut);}
       } else {
           urutan ="01";
       }
        dt.setNoTiket(txtplat.getText()+urutan);
        dt.setStatus("false");
        FXML_UtamaController.dtparkir.setModelTiket(dt);
        if(FXML_UtamaController.dtparkir.validasi(dt.getNoTiket())<=0){
            if(rdbmobil.isSelected()){
                if(Integer.valueOf(lmobil.getText())>0){
                    if(FXML_UtamaController.dtparkir.insert()){
                    ModelGedung g = FXML_UtamaController.dtgedung.Search(CBGedung.getSelectionModel().getSelectedItem());
                    g.setKmobil(g.getKmobil()-1);
                    FXML_UtamaController.dtgedung.setModelGedung(g);
                    if(FXML_UtamaController.dtgedung.update()){}
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Nomor Tiket Anda Adalah " + dt.getNoTiket(),ButtonType.OK);
               a.showAndWait();            hapusklik(event);
               ModelGedung cek = FXML_UtamaController.dtgedung.Search(CBGedung.getSelectionModel().getSelectedItem());
        lmobil.setText(String.valueOf(cek.getKmobil()));
        lmotor.setText(String.valueOf(cek.getKmotor()));
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
                }
               
            } else if(rdbmotor.isSelected()){
                if(Integer.valueOf(lmotor.getText())>0){
                if(FXML_UtamaController.dtparkir.insert()){
               ModelGedung g = FXML_UtamaController.dtgedung.Search(CBGedung.getSelectionModel().getSelectedItem());
                    g.setKmotor(g.getKmotor()-1);
                    FXML_UtamaController.dtgedung.setModelGedung(g);
                    if(FXML_UtamaController.dtgedung.update()){}
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Nomor Tiket Anda Adalah " + dt.getNoTiket(),ButtonType.OK);
               a.showAndWait();            hapusklik(event);
               ModelGedung cek = FXML_UtamaController.dtgedung.Search(CBGedung.getSelectionModel().getSelectedItem());
        lmobil.setText(String.valueOf(cek.getKmobil()));
        lmotor.setText(String.valueOf(cek.getKmotor()));
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
                }
            }
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtplat.requestFocus();
        }
        clear();
        
    }
//simpledateformat 
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
        } catch (IOException e){   
            e.printStackTrace();   
        }
        txtplat.getScene().getWindow().hide();
        
    }


    @FXML
    private void hapusklik(ActionEvent event) {
        clear();
    }
    
    private void clear(){
        txtplat.setText("");
        rdbmobil.setSelected(true);
        rdbmotor.setSelected(false);
        tanggal.setValue(null);
    }

    @FXML
    private void cekkuota(ActionEvent event) {
        ModelGedung g = FXML_UtamaController.dtgedung.Search(CBGedung.getSelectionModel().getSelectedItem());
        lmobil.setText(String.valueOf(g.getKmobil()));
        lmotor.setText(String.valueOf(g.getKmotor()));
    }
    
}
