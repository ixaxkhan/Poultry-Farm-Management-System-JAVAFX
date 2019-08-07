
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


public class CustomerListController implements Initializable {
    
     // loading fxml files varialbles
     AnchorPane customerAddView;
     
    @FXML
    private Label customerAddLabelID;

    @FXML
    private void addCustomerAccountToCenter(MouseEvent event) {
         Object source = event.getSource();
          Scene scene = customerAddLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add( customerAddView);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             customerAddView = (AnchorPane)FXMLLoader.load(getClass().getResource("/customers/addCustomer/customerRegistrationView.fxml"));
         } catch (IOException ex) {
             Logger.getLogger(CustomerListController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    
    
    }
    
}
