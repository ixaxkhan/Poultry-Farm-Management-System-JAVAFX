
package login.superAdmin;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.Modality;


public class SuperAdminModal {
    
   Connection connection= null;
   Statement statement =null;
   ResultSet resultSet =null;
   String sql =null;
    public SuperAdminModal() throws ClassNotFoundException{
        connection = DatabaseConnection.getConnection();
    }
    public boolean getLoginToSystemBySuperAdmin(JFXTextField userName,JFXPasswordField password){
        boolean check=false;
        sql="SELECT * FROM SUPER_ADMIN";
       try {
           statement=connection.createStatement();
           resultSet =statement.executeQuery(sql);
           while(resultSet.next()){
           
           if((resultSet.getString("USER_NAME").equals(userName.getText()))&& (resultSet.getString("PASSWORD").equals(password.getText()))){
                 check=true;
           }
           }
           
       } catch (SQLException ex) {
           Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.setContentText(ex.toString()+" super admin login");
                alert.initModality(Modality.APPLICATION_MODAL);
               
                alert.show();
       }
       return check;
    }
    
   
    
}
