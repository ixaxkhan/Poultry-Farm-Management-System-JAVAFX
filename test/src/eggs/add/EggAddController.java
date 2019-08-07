/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eggs.add;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;



/**
 *
 * @author khan
 */
public class EggAddController {
    @FXML
    private JFXTextField txfTotalEggs;
    @FXML
    private JFXDatePicker txfDate;
    @FXML
    private JFXTextField eggPrim;
    @FXML
    private ImageView totalImage;
    @FXML
    private ImageView dateImage;
    @FXML
    private TableColumn<?, ?> tbAddID;
    @FXML
    private TableColumn<?, ?> tbTotalEggs;
    @FXML
    private TableColumn<?, ?> tbDate;
    @FXML
    private JFXButton savebtn;
    @FXML
    private JFXButton updatebtn;
    @FXML
    private JFXButton deletebtn;
    @FXML
    private JFXButton clearbtn;
    @FXML
    private JFXButton printbtn;

    @FXML
    private void loadDataIntoFields(MouseEvent event) {
    }

    @FXML
    private void saveDataIntoTable(ActionEvent event) {
    }

    @FXML
    private void updateDataIntoTable(ActionEvent event) {
    }

    @FXML
    private void deleteDataIntoData(ActionEvent event) {
    }

    @FXML
    private void clearFields(ActionEvent event) {
    }

    @FXML
    private void printSlips(ActionEvent event) {
    }

    @FXML
    private void printRecords(MouseEvent event) {
    }
  
   

    
}
