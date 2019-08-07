
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


public class FeedListController implements Initializable {
    
    // loading fxml file variables
    AnchorPane feedPurchaseView;
    AnchorPane feedConsumeView;
    
    
    @FXML
    private Label purchaseLabelID;
    @FXML
    private Label consumeLabelID;

    @FXML
    private void addPurchaseFeedToCenter(MouseEvent event) {
         Object source = event.getSource();
          Scene scene = purchaseLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add(feedPurchaseView);
    }

    @FXML
    private void addConsumeFeedToCenter(MouseEvent event) {
         Object source = event.getSource();
          Scene scene = consumeLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add(feedConsumeView);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
      try {
             feedPurchaseView = (AnchorPane)FXMLLoader.load(getClass().getResource("/feeds/Purchase/purchaseView.fxml"));
             feedConsumeView = (AnchorPane)FXMLLoader.load(getClass().getResource("/feeds/consume/feedConsumeView.fxml"));
         
         } catch (IOException ex) {
             Logger.getLogger(CheckenListController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    
    }
    
}
