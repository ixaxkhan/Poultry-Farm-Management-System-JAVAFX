/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class CheckenListController implements Initializable {
    
    //loading fxml through this variables
     AnchorPane checkenSaleView;
     AnchorPane checkenPurchaseView;
     AnchorPane checkenMotilityView;
     
     
    @FXML
    private Label saleLabelID;
     @FXML
    private Label purchaseLabelID;
    @FXML
    private Label motilityLabelID;
     
    @FXML
    private void addChickenSaleToCenter(MouseEvent event) throws IOException {
             Object source = event.getSource();
          Scene scene = saleLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add(checkenSaleView );
          // root.setLayoutX(200);
        
    }

    @FXML
    private void addChickenPurchaseToCenter(MouseEvent event) throws IOException {
        
       
          Scene scene = purchaseLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add(checkenPurchaseView);
          
    }
    @FXML
    private void addChickenMotilityToCenter(MouseEvent event) {
         Object source = event.getSource();
          Scene scene = motilityLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add(checkenMotilityView);
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
             checkenSaleView = (AnchorPane)FXMLLoader.load(getClass().getResource("/chickens/Sale/checkenSaleView.fxml"));
             checkenPurchaseView = (AnchorPane)FXMLLoader.load(getClass().getResource("/chickens/Purchase/checkenPurchaseView.fxml"));
             checkenMotilityView = (AnchorPane)FXMLLoader.load(getClass().getResource("/chickens/chickenMotility/chickenMotilityView.fxml"));
        
         } catch (IOException ex) {
             Logger.getLogger(CheckenListController.class.getName()).log(Level.SEVERE, null, ex);
         }
      
        
    }

    
    
}
