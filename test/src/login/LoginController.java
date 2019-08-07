/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login.superAdmin.SuperAdminModal;


public class LoginController implements Initializable {
    
    
    //object of the model classes
    SuperAdminModal superadminModal;
    AdminLoginModal adminModal;
    // take value from fields variables
    private String userName="";
    private String password ="";
    private Stage stage =new Stage();

 
    @FXML
    private JFXTextField superUserName;
    @FXML
    private JFXPasswordField superPassword;
    @FXML
    private JFXTextField adminUserName;
    @FXML
    private JFXPasswordField adminPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            superadminModal = new SuperAdminModal();
            adminModal= new AdminLoginModal();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void windowClose(ActionEvent event) {
        
        System.exit(0);
    }


    @FXML
    private void clear(ActionEvent event) {
        
        adminUserName.setText("");
        adminPassword.setText("");
        superUserName.setText("");
        superPassword.setText("");
    }

    @FXML
    private void superLogin(ActionEvent event) throws IOException{
        
        
        
        userName= superUserName.getText();
        password= superPassword.getText();
         //use modal class
        if(superadminModal.getLoginToSystemBySuperAdmin(superUserName, superPassword)){
            
            
            Parent root;
            root = FXMLLoader.load(getClass().getResource("superAdmin/superAdmin.fxml"));
            final Node source = (Node) event.getSource();
            final Stage stage2 = (Stage) source.getScene().getWindow();
            stage2.close();
            Scene sceen= new Scene(root);
            stage.setScene(sceen);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            
       
        }else{
            if((userName.equals(""))&& (password.equals(""))){
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(" Empty");
                alert.initModality(Modality.APPLICATION_MODAL);
               
                alert.show();
                
            
            }else{
                 Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Wrong password and userName");
                alert.initModality(Modality.APPLICATION_MODAL);
               
                alert.show();
              
            }
        }
               
    }

    @FXML
    private void adminLogin(ActionEvent event) throws IOException {
        userName = adminUserName.getText();
        password = adminPassword.getText();
        //use modal class
        if (adminModal.getLoginToSystemByAdmin(adminUserName, adminPassword)) {
            
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/test/Main.fxml"));
           
            final Node source = (Node) event.getSource();
            final Stage stage2 = (Stage) source.getScene().getWindow();
            stage2.close();
            Scene sceen = new Scene(root);
            stage.setScene(sceen);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

             
        } else {
            if ((userName.equals("")) && (password.equals(""))) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(" Empty");
                alert.initModality(Modality.APPLICATION_MODAL);

                alert.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Wrong password and userName");
                alert.initModality(Modality.APPLICATION_MODAL);

                alert.show();

            }
        }
        
        
       
        
    }
    
}
