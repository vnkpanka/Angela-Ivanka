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
    private RadioButton rdbmobil;
    private RadioButton rdbmotor;
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
    @FXML
    private TextField txtjenis;
    @FXML
    private TextField txtgedung;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<String> List = FXML_UtamaController.dtparkir.ListParkiran();
        CBTiket.getItems().addAll(List);
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
        ModelTransaksi n=new ModelTransaksi();
        n.setNoTiket(CBTiket.getSelectionModel().getSelectedItem());
        n.setIdgedung(txtgedung.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        n.setJam_Keluar(Time.valueOf(sdf.format(new java.util.Date())));
        n.setHari_Keluar(FXML_UtamaController.dtparkir.getModelTiket().getHariMasuk());
        n.setTotal_Bayar((int)biaya);
        ArrayList<String> List = FXML_UtamaController.dttrans.List();
        String urutan;
        if(!List.isEmpty()){
           int urut = Integer.valueOf(List.get(List.size()-1)) + 1;
           if(urut<100){urutan = "00"+String.valueOf(urut);}
           else if(urut<10){urutan = "0"+String.valueOf(urut);}
           else {urutan = String.valueOf(urut);}
       } else {
           urutan ="001";
       }
        n.setNoTrans(urutan);
        
        FXML_UtamaController.dttrans.setModelTransaksi(n);
        if(FXML_UtamaController.dttrans.validasi(n.getNoTiket())<=0){
            if(FXML_UtamaController.dttrans.insert()){
               ModelGedung g = FXML_UtamaController.dtgedung.Search(txtgedung.getText());
               g.setIdgedung(txtgedung.getText());
               System.out.println(g.getKmobil());
               System.out.println(g.getKmotor());
               if(txtjenis.getText().equalsIgnoreCase("mobil")){
                   g.setKmobil(g.getKmobil()+1);
               } else if(txtjenis.getText().equalsIgnoreCase("motor")){
                   g.setKmotor(g.getKmotor()+1);
               }
               System.out.println(g.getKmobil());
               System.out.println(g.getKmotor());
               FXML_UtamaController.dtgedung.setModelGedung(g);
               if(FXML_UtamaController.dtgedung.update()){}
               
               ModelTiket t = FXML_UtamaController.dtparkir.Search(CBTiket.getSelectionModel().getSelectedItem());
               t.setStatus("true");
               FXML_UtamaController.dtparkir.setModelTiket(t);
               if(FXML_UtamaController.dtparkir.update()){}
               
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            clear();
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            CBTiket.requestFocus();
    }
    ArrayList<String> ListTiket = FXML_UtamaController.dtparkir.ListParkiran();
    CBTiket.getItems().clear();
    CBTiket.getItems().addAll(ListTiket);
    clear();
    }

    @FXML
    private void CheckJenis(ActionEvent event) {
        ModelTiket m = FXML_UtamaController.dtparkir.Search(CBTiket.getSelectionModel().getSelectedItem());
        txtjenis.setText(m.getJenis());
        txtdspmasuk.setText(String.valueOf(m.getJamMasuk()));
        txtgedung.setText(m.getIdgedung());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        txtdspkeluar.setText(sdf.format(new java.util.Date()));
        long time = (Time.valueOf(sdf.format(new java.util.Date())).getTime() - m.getJamMasuk().getTime());
        long diffHours = (time / (60 * 60 * 1000) % 24);
        diffHours += 1;
        
        // Hitung int biaya = diffHours * biayaperjam 
        if(m.getJenis().equalsIgnoreCase("mobil")){
            biaya = diffHours * 3000;
        } else if(m.getJenis().equalsIgnoreCase("motor")){
            biaya = diffHours * 2000;
        }
        txtbiaya.setText(String.valueOf(biaya));
    }
    
    private void clear(){
        txtgedung.setText("");
        txtjenis.setText("");
        txtdspmasuk.setText("");
        txtdspkeluar.setText("");
        txtbiaya.setText("");
    }

}

