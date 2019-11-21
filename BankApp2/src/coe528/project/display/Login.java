package coe528.project.display;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;

import coe528.project.func.Users.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login implements Initializable{
    
    
    @FXML TextField login_username;
    @FXML PasswordField login_password;
    @FXML Button login_enter;
    
    @FXML
    void Login(){
        System.out.println("init");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       Login();
    }

    private void loadNext(User user, boolean isManger){
        Parent root1;
        Stage stage;
        Main.currentAccount = user;
        
        if(isManger){
            try {
                root1 = FXMLLoader.load(getClass().getResource("manager.fxml"));
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("Bank App - Manager Home");
                stage.setScene(new Scene(root1));
                stage.show();

            }catch (Exception e){
                e.printStackTrace();
            }finally{
                System.out.println(user.toString());
            }
        }
        else{
            try {
                
                root1 = FXMLLoader.load(getClass().getResource("home.fxml"));
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("Bank App - Customer Home");
                stage.setScene(new Scene(root1));
                stage.show();

            }catch (Exception e){
                System.out.println("f");
            }finally{
                System.out.println(user.toString());
                System.out.println(user.getClass());
            }
        }
    }

    @FXML
    private void test(ActionEvent event){
        String username = login_username.getText();
        String password = login_password.getText();

        if(Main.admin.getUsername().equals(username) && Main.admin.getPassword().equals(password))
            loadNext(Main.admin, true);
        for(Iterator<Customer> c = Main.testBank.getCustomers().iterator(); c.hasNext();){
            Customer cust = c.next();
            if(cust.getUsername().equals(username)){
                if(cust.getPassword().equals(password)){
                   loadNext(cust, false);
                }
            }
            
        }
    }

}
