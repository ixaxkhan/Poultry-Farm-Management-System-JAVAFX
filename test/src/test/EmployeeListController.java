
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


public class EmployeeListController implements Initializable{
     // loading fxml files varialbles
     AnchorPane employeeRegistrationView;
     AnchorPane employeeSalaryView;
    @FXML
    private Label employeeRegistrationLabelID;
    @FXML
    private Label employeeSalaryLabelID;

    @FXML
    private void addEmployeeRegistrationToCenter(MouseEvent event) {
         Object source = event.getSource();
          Scene scene = employeeRegistrationLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add(employeeRegistrationView);
    }

    @FXML
    private void addEmployeeSalaryToCenter(MouseEvent event) {
         Object source = event.getSource();
          Scene scene = employeeSalaryLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add( employeeSalaryView);
    }
      @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             employeeRegistrationView = (AnchorPane)FXMLLoader.load(getClass().getResource("/employee/add/employeeAddView.fxml"));
             employeeSalaryView = (AnchorPane)FXMLLoader.load(getClass().getResource("/employee/salary/employeeSalaryView.fxml"));
         
         } catch (IOException ex) {
             Logger.getLogger(CustomerListController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    
    
    }
    
}
