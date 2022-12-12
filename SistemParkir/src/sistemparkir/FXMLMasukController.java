/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemparkir;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author BOSS
 */
public class FXMLMasukController implements Initializable {

    @FXML
    private RadioButton rdbmobil;
    @FXML
    private ToggleGroup Jenis;
    @FXML
    private RadioButton rdbmotor;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnexit;
    @FXML
    private Button btnwktmsk;
    @FXML
    private TextField txtplat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void hapusklik(ActionEvent event) {
    }

    @FXML
    private void exitklik(ActionEvent event) {
    }

    @FXML
    private void wktmskklik(ActionEvent event) {
    }
    
}
