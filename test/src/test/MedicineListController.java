
package test;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class MedicineListController implements Initializable{
    
    // loading fxml files varialbles
     AnchorPane medicinePurchaseView;
     AnchorPane  medicineConsumeView ;
    
    @FXML
    private Label purchaseLabelID;
    @FXML
    private Label consumeLabelID;

    @FXML
    private void addPurchaseMedicineToCenter(MouseEvent event) {
         Object source = event.getSource();
          Scene scene = purchaseLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add(medicinePurchaseView);
          // root.setLayoutX(200);
        
        
    }

    @FXML
    private void addConsumeMedicineToCenter(MouseEvent event) {
         Object source = event.getSource();
          Scene scene = consumeLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add(medicineConsumeView);
          // root.setLayoutX(200);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
             medicinePurchaseView = (AnchorPane)FXMLLoader.load(getClass().getResource("/medicine/Purchase/MedicinePurchaseView.fxml"));
             medicineConsumeView = (AnchorPane)FXMLLoader.load(getClass().getResource("/medicine/Consume/medicineConsumeView.fxml"));
         
         } catch (IOException ex) {
             Logger.getLogger(CheckenListController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    
    }
    
}
