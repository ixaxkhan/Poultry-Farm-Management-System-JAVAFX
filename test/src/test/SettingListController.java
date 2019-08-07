
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


public class SettingListController  implements Initializable{
    // loading fxml files varialbles
     AnchorPane systemUserView;
    @FXML
    private Label systemUserLabelID;

    @FXML
    private void addSystemUserToCenter(MouseEvent event) {
           Object source = event.getSource();
          Scene scene = systemUserLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add(systemUserView);
          // root.setLayoutX(200);
        
        
        
    }
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            systemUserView = (AnchorPane)FXMLLoader.load(getClass().getResource("/setting/addSystemUser/systemUserAddView.fxml"));
          
         } catch (IOException ex) {
             Logger.getLogger(CheckenListController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    
    }
    
}
