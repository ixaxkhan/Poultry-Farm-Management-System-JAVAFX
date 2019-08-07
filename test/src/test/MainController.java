/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author EngrIzazKhan
 */
public class MainController implements Initializable {
    
   // private VBox addlist;
    @FXML
    private AnchorPane center;
    // loading image throught that
    File strightImage;
    File downImage;
    Image imageforStright;
    Image imagefordown;
    // image variable of fxml
    @FXML
    private ImageView checkenImage;
       @FXML
    private ImageView medicineImage;
    @FXML
    private ImageView feedImage;
    @FXML
    private ImageView companyImage;
    @FXML
    private ImageView expenseImage;
    @FXML
    private ImageView accountImage;
    @FXML
    private ImageView reportImage;
    @FXML
    private ImageView databaseImage;
    @FXML
    private ImageView graphImage;
    @FXML
    private ImageView settingImage;
     @FXML
    private ImageView customerImage;
     @FXML
    private ImageView eggImage;
    @FXML
    private ImageView EmployeeImage;
    
    
    //loading variables for avery fxmls
    VBox medicineList ;
    VBox checkenList ;
    VBox companyList;
    VBox databaseList;
    VBox expenseList;
    VBox feedList;
    VBox graphList;
    VBox reportList;
    VBox settingList;
    VBox accountList;
    VBox customerList;
    VBox eggList;
    VBox employeeList;
    
    //this for main menu vbox to add fxml for each type
    
    @FXML
    private VBox checkenListAdd;
    @FXML
    private VBox medicineListAdd;
    @FXML
    private VBox feedListAdd;
    @FXML
    private VBox companylListAdd;
    @FXML
    private VBox expenseListAdd;
    @FXML
    private VBox accountListAdd;
    @FXML
    private VBox reportListAdd;
    @FXML
    private VBox databaseListAdd;
    @FXML
    private VBox graphListAdd;
    @FXML
    private VBox settingListAdd;
    @FXML
    private VBox customerListAdd;
    @FXML
    private VBox eggListAdd;
    @FXML
    private VBox employeeListAdd;
    
   
 
    
 
    @FXML
    private void addCheckenList(MouseEvent event) {
        
        loaderAndRemover(checkenListAdd,checkenList,event,checkenImage);
       }
    

    @FXML
    private void addMedicienList(MouseEvent event) {
        
      loaderAndRemover(medicineListAdd,medicineList,event,medicineImage);
    }

    @FXML
    private void addFeedList(MouseEvent event) {
        
        loaderAndRemover(feedListAdd,feedList,event,feedImage);
        
    }

    @FXML
    private void addCompanyList(MouseEvent event) {
        loaderAndRemover(companylListAdd,companyList,event,companyImage);
    }

    @FXML
    private void addExpenseList(MouseEvent event) {
         loaderAndRemover(expenseListAdd,expenseList,event,expenseImage);
    }

    @FXML
    private void addAccountList(MouseEvent event) {
         loaderAndRemover(accountListAdd,accountList,event,accountImage);
    }

    @FXML
    private void addReportList(MouseEvent event) {
         loaderAndRemover(reportListAdd,reportList,event,reportImage);
    }

    @FXML
    private void addDatabaseList(MouseEvent event) {
         loaderAndRemover(databaseListAdd,databaseList,event,databaseImage);
    }

    @FXML
    private void addSettingList(MouseEvent event) {
         loaderAndRemover(settingListAdd,settingList,event,settingImage);
    }
    @FXML
    private void addGraphList(MouseEvent event){
     loaderAndRemover(graphListAdd,graphList,event,graphImage);
    
    }
     @FXML
    private void addCustomerList(MouseEvent event) {
        
        loaderAndRemover(customerListAdd,customerList,event,customerImage);
    }
     @FXML
    private void addEggList(MouseEvent event) {
         loaderAndRemover(eggListAdd,eggList,event,eggImage);
    }

    @FXML
    private void addEmployeeList(MouseEvent event) {
         loaderAndRemover(employeeListAdd,employeeList,event,EmployeeImage);
        
    }
    @FXML
    private void closeWindow(ActionEvent event){
    
    Platform.exit();
    
    }
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            //loading avary fxml file before use them
          medicineList = (VBox )FXMLLoader.load(getClass().getResource("medicineList.fxml"));
          checkenList = (VBox )FXMLLoader.load(getClass().getResource("checkenList.fxml"));
          feedList = (VBox )FXMLLoader.load(getClass().getResource("feedList.fxml"));
          companyList = (VBox )FXMLLoader.load(getClass().getResource("companyList.fxml"));
          databaseList = (VBox )FXMLLoader.load(getClass().getResource("databaseList.fxml"));
          accountList = (VBox )FXMLLoader.load(getClass().getResource("accountList.fxml"));
          settingList = (VBox )FXMLLoader.load(getClass().getResource("settingList.fxml"));
          graphList = (VBox )FXMLLoader.load(getClass().getResource("graphList.fxml"));
          reportList = (VBox )FXMLLoader.load(getClass().getResource("reportList.fxml"));
          settingList = (VBox )FXMLLoader.load(getClass().getResource("settingList.fxml"));
          expenseList=(VBox )FXMLLoader.load(getClass().getResource("expenseList.fxml"));
          customerList=(VBox )FXMLLoader.load(getClass().getResource("customerList.fxml"));
          employeeList=(VBox )FXMLLoader.load(getClass().getResource("employeeList.fxml"));
          eggList=(VBox )FXMLLoader.load(getClass().getResource("eggList.fxml"));
          // image loading
          downImage = new File("F:\\final Year project\\test\\src\\image\\icons8-sort-down-filled-100.png");
          strightImage= new File("F:\\final Year project\\test\\src\\image\\icons8-sort-right-24.png");
            imageforStright = new Image(strightImage.toURI().toString());
             imagefordown = new Image(downImage.toURI().toString());
        
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    } 
    
    private void loaderAndRemover(VBox vbox1, VBox vbox2,MouseEvent event,ImageView image){
    
      if(event.getClickCount()==2){
          image.setImage(imagefordown);
        
         vbox1.getChildren().add(vbox2);
        
       }else{
            image.setImage(imageforStright);
            ObservableList<Node> children = vbox1.getChildren();
            children.remove(1);
       
      
       
       }
    
    }

   

   
    
    
}
