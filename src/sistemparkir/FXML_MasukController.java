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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BOSS
 */
public class FXML_MasukController implements Initializable {
    public static Parkir dt = new Parkir();
    
    @FXML
    private TextField txtplat;
    @FXML
    private Button btnwktmsk;
    @FXML
    private Button btnexit;
    @FXML
    private Button btnhapus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    boolean editdata=false;
    public void execute(Parkir d){
        if(!d.getPlat().isEmpty()){
          editdata=true;
          txtplat.setText(d.getPlat());
          txtplat.setEditable(false);
          txtplat.requestFocus();
        }
    }
    //java sql time,time get text sdf,time valueof 
    //java util time, set text 
    @FXML
    private void wktmskklik(ActionEvent event) {
        
        
        dt.setPlat(txtplat.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd:MM:yyyy");
        dt.setWktmsk(Time.valueOf(sdf.format(new java.util.Date())));
        //dt.setTglmsk(Date.valueOf(sdf2.format(new java.util.Date())));
        dt.setTglmsk(null);
        
        
        ArrayList<String> List = FXML_UtamaController.dtparkir.CheckKode(txtplat.getText());
        String urutan;
        if(!List.isEmpty()){
           int urut = Integer.valueOf(List.get(List.size()-1).substring(9)) + 1;
           if(urut<10){urutan = "0"+String.valueOf(urut);}
           else {urutan = String.valueOf(urut);}
       } else {
           urutan ="01";
       }
        dt.setNoTiket(txtplat.getText()+urutan);
        dt.setStatus("false");
        
        FXML_UtamaController.dtparkir.setParkir(dt);
        if(editdata){
            if(FXML_UtamaController.dtparkir.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   txtplat.setEditable(true);        hapusklik(event);                
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
               a.showAndWait();                    
            }
            
        }else if(FXML_UtamaController.dtparkir.validasi(dt.getNoTiket())<=0){
            if(FXML_UtamaController.dtparkir.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Nomor Tiket Anda Adalah " + dt.getNoTiket(),ButtonType.OK);
               a.showAndWait();            hapusklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtplat.requestFocus();
        }
        
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
        txtplat.getScene().getWindow().hide();
        } catch (IOException e){   
            e.printStackTrace();   
        }
        
    }

    private void keluarklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_Keluar.fxml"));    
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
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        txtplat.setText("");
        txtplat.requestFocus();
    }
    
}
