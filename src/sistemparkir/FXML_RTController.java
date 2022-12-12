/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemparkir;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author BOSS
 */
public class FXML_RTController implements Initializable {

    @FXML
    private Button btnexit;
    @FXML
    private TableView<ModelTiket> tbvrt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdata();
    }    

    @FXML
    private void exitklik(ActionEvent event) {
    }
    
    public void showdata(){
        ObservableList<ModelTiket> data=FXML_UtamaController.dtparkir.Load();
        if(data!=null){            
            tbvrt.getColumns().clear();            
            tbvrt.getItems().clear();
            
            TableColumn col=new TableColumn("ID Gedung");
            col.setCellValueFactory(new PropertyValueFactory<ModelTiket, String>("idgedung"));
            tbvrt.getColumns().addAll(col);
            
            col=new TableColumn("No Tiket");
            col.setCellValueFactory(new PropertyValueFactory<ModelTiket, String>("NoTiket"));
            tbvrt.getColumns().addAll(col);
            
            col=new TableColumn("Plat");
            col.setCellValueFactory(new PropertyValueFactory<ModelTiket, String>("Plat"));
            tbvrt.getColumns().addAll(col);
            
            col=new TableColumn("Jenis Kendaraan");
            col.setCellValueFactory(new PropertyValueFactory<ModelTiket, String>("jenis"));
            tbvrt.getColumns().addAll(col);
            
            col=new TableColumn("Jam Masuk");
            col.setCellValueFactory(new PropertyValueFactory<ModelTiket, Time>("JamMasuk"));
            tbvrt.getColumns().addAll(col);
            
            tbvrt.setItems(data);
            
            } else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvrt.getScene().getWindow().hide();;
        }     
    }
}
