package coe528.project.display;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;

import coe528.project.func.Users.*;

public class Login implements Initializable{
    
    
    @FXML TextField login_username;
    @FXML PasswordField login_password;
    @FXML Button login_enter;
    
    void Login(){
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
    @FXML
    private void test(ActionEvent event){
        String username = login_username.getText();
        String password = login_password.getText();
        if(Main.admin.getUsername().equals(username) && Main.admin.getPassword().equals(password))
            System.out.println("System Admin");
        for(Iterator<Customer> c = Main.testBank.getCustomers().iterator(); c.hasNext();){
            Customer cust = c.next();
            
            if(cust.getUsername().equals(username)){
                if(cust.getPassword().equals(password)){
                    System.out.println("Customer" + cust.toString());
                }
            }
            
        }
        System.out.print(username);
    }

}
