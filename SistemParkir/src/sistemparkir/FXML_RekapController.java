/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemparkir;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BOSS
 */
public class FXML_RekapController implements Initializable {

    @FXML
    private TableView<ModelTransaksi> tbvhistory;
    @FXML
    private Button btnmasuk;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnexit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdata();
    }    

    public void showdata(){
        ObservableList<ModelTransaksi> data=FXML_UtamaController.dttrans.Load();
        if(data!=null){            
            tbvhistory.getColumns().clear();            
            tbvhistory.getItems().clear();
            
            TableColumn col=new TableColumn("ID Transaksi");
            col.setCellValueFactory(new PropertyValueFactory<ModelTransaksi, String>("NoTrans"));
            tbvhistory.getColumns().addAll(col);
            
            col=new TableColumn("ID Gedung");
            col.setCellValueFactory(new PropertyValueFactory<ModelTransaksi, String>("idgedung"));
            tbvhistory.getColumns().addAll(col);
            
            col=new TableColumn("No Tiket");
            col.setCellValueFactory(new PropertyValueFactory<ModelTransaksi, String>("NoTiket"));
            tbvhistory.getColumns().addAll(col);
            
            col=new TableColumn("Plat");
            col.setCellValueFactory(new PropertyValueFactory<ModelTransaksi, String>("Plat"));
            tbvhistory.getColumns().addAll(col);
            
            col=new TableColumn("Jenis Kendaraan");
            col.setCellValueFactory(new PropertyValueFactory<ModelTransaksi, String>("jenis"));
            tbvhistory.getColumns().addAll(col);
            
            col=new TableColumn("Jam Masuk");
            col.setCellValueFactory(new PropertyValueFactory<ModelTransaksi, Time>("Jam_Masuk"));
            tbvhistory.getColumns().addAll(col);
            
            col=new TableColumn("Jam Keluar");
            col.setCellValueFactory(new PropertyValueFactory<ModelTransaksi, Time>("Jam_Keluar"));
            tbvhistory.getColumns().addAll(col);
            
            col=new TableColumn("Total Bayar");
            col.setCellValueFactory(new PropertyValueFactory<ModelTransaksi, Integer>("Total_Bayar"));
            tbvhistory.getColumns().addAll(col);
            tbvhistory.setItems(data);
            
            } else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvhistory.getScene().getWindow().hide();;
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
        } catch (IOException e){   
            e.printStackTrace();   
        }
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
        } catch (IOException e){   
            e.printStackTrace();   
        }
    }

    @FXML
    private void exitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }
    
}
