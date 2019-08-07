
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


public class EggListController implements Initializable{
     // loading fxml files varialbles
     AnchorPane eggSaleView;
     AnchorPane eggWestageView;
     AnchorPane eggAddView;
    @FXML
    private Label eggSaleLabelID;
    @FXML
    private Label eggWestageLabelID;
    @FXML
    private Label eggAddLabel;

    @FXML
    private void addEggSaleToCenter(MouseEvent event) {
          // Object source = event.getSource();
          Scene scene = eggSaleLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add( eggSaleView);
    }

    @FXML
    private void addEggWestageToCenter(MouseEvent event) {
           //Object source = event.getSource();
          Scene scene =  eggWestageLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add(eggWestageView);
    }
    @FXML
    private void addEggToCenter(MouseEvent event) {
       // Object source = event.getSource();
        Scene scene = eggAddLabel.getScene();
        AnchorPane lookup3 = (AnchorPane) scene.lookup("#center");
        
        lookup3.getChildren().clear();
        lookup3.getChildren().add(eggAddView);
        
    }
       @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            eggSaleView = (AnchorPane)FXMLLoader.load(getClass().getResource("/eggs/add/eggAddView.fxml"));
            eggWestageView = (AnchorPane)FXMLLoader.load(getClass().getResource("/eggs/wastage/eggWastageView.fxml"));
            eggAddView = (AnchorPane)FXMLLoader.load(getClass().getResource("/eggs/add/eggAddView.fxml"));
         
         } catch (IOException ex) {
             Logger.getLogger(CustomerListController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    
    
    }

    
    
}
