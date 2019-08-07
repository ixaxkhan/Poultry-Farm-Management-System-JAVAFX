
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


public class ExpenseListController implements Initializable{
    
     // loading fxml file variables
    AnchorPane addExpenseView;
    AnchorPane addExpenseCategView;
    @FXML
    private Label expenseLabelID;
    @FXML
    private Label expenseCategLabelID;

    @FXML
    private void addExpenseToCenter(MouseEvent event) {
         Object source = event.getSource();
          Scene scene = expenseLabelID.getScene();
          AnchorPane lookup = (AnchorPane)scene.lookup("#center");
          lookup.getChildren().clear();
          lookup.getChildren().add(addExpenseView);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
              addExpenseView= (AnchorPane)FXMLLoader.load(getClass().getResource("/expenses/expenseAddView.fxml"));
              addExpenseCategView = (AnchorPane) FXMLLoader.load(getClass().getResource("/expenses/categ/expenseAddCategView.fxml"));
            
         } catch (IOException ex) {
             Logger.getLogger(CheckenListController.class.getName()).log(Level.SEVERE, null, ex);
         }
        }

    @FXML
    private void addExpenseCategToCenter(MouseEvent event) {
        Object source = event.getSource();
        Scene scene = expenseCategLabelID.getScene();
        AnchorPane lookup = (AnchorPane) scene.lookup("#center");
        lookup.getChildren().clear();
        lookup.getChildren().add(addExpenseCategView);
        
    }
    
    
    
}
