
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


public class AccountListController implements Initializable {
      // loading fxml files varialbles
     AnchorPane companyAccountView;
     AnchorPane customerAccountView ;
    @FXML
    private Label companyAccountLabelID;
    @FXML
    private Label customerAccountLabelID;

    @FXML
    private void addCompanyAccountToCenter(MouseEvent event) {
          Object source = event.getSource();
          Scene scene = companyAccountLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add(companyAccountView);
          // root.setLayoutX(200);
    }

    @FXML
    private void addCustomerAccountToCenter(MouseEvent event) {
          Object source = event.getSource();
          Scene scene = customerAccountLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add( customerAccountView );
          // root.setLayoutX(200);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             companyAccountView = (AnchorPane)FXMLLoader.load(getClass().getResource("/accounts/companyAccount/addCompanyAccountView.fxml"));
             customerAccountView = (AnchorPane)FXMLLoader.load(getClass().getResource("/accounts/customerAccount/addCustomerAccountView.fxml"));
         
         } catch (IOException ex) {
             Logger.getLogger(CheckenListController.class.getName()).log(Level.SEVERE, null, ex);
         }
       
    
    }
    
}
