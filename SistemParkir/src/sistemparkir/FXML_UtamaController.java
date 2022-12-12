/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package sistemparkir;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author BOSS
 */
public class FXML_UtamaController implements Initializable {
    public static DBTiket dtparkir=new DBTiket();
    public static DBTransaksi dttrans = new DBTransaksi();
    public static DBGedung dtgedung = new DBGedung();
    

    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnmasuk;
    @FXML
    private Button btntrans;
    @FXML
    private Button btnexit;
    @FXML
    private Button btnrt;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    


    @FXML
    private void keluarklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLKeluar.fxml"));    
        Parent root = (Parent)loader.load();        
        Scene scene = new Scene(root);        
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        
        stg.setIconified(false);        
        stg.setScene(scene);
        stg.showAndWait();
        btnmasuk.getScene().getWindow().hide();
        } catch (IOException e){   
            e.printStackTrace();   
        }
    }

    @FXML
    private void masukklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_Masuk.fxml"));    
        Parent root = (Parent)loader.load();        
        Scene scene = new Scene(root);        
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        
        stg.setIconified(false);        
        stg.setScene(scene);
        stg.showAndWait();
        btnmasuk.getScene().getWindow().hide();
        } catch (IOException e){   
            e.printStackTrace();   
        }
    }

    @FXML
    private void transklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_Rekap.fxml"));    
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
    private void exitklik(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void rtklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_RT.fxml"));    
        Parent root = (Parent)loader.load();        
        Scene scene = new Scene(root);        
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        
        stg.setIconified(false);        
        stg.setScene(scene);
        stg.showAndWait();
        btnmasuk.getScene().getWindow().hide();
        } catch (IOException e){   
            e.printStackTrace();   
        }
    }
    
}
