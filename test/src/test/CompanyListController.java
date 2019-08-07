
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


public class CompanyListController implements Initializable{
    
      // loading fxml file variables
    AnchorPane addCompanyView;
   
    @FXML
    private Label companyLabelID;

    @FXML
    private void addCompanyToCenter(MouseEvent event) {
         Object source = event.getSource();
          Scene scene = companyLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add(addCompanyView);
       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       try {
             addCompanyView = (AnchorPane)FXMLLoader.load(getClass().getResource("/companies/add/companyAddView.fxml"));
            
         } catch (IOException ex) {
             Logger.getLogger(CheckenListController.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    }
    
}
