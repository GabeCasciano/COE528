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

    @FXMl
    void Login(){
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }

    private void loadNext(User user){
        Parent root1;
        Stage stage;

        if(user.getClass() == Manager.class){
            try {
                root1 = new FXMLLoader(getClass().getResource("manager.fxml"));
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("Bank App - Manager Home");
                stage.setScene(new Scene(root1));
                stage.show();

            }catch (Exception e){

            }finally{
                System.out.println(cust.toString());
            }
        }
        else if(user.getClass() == Customer.class){
            try {
                Main.currentAccount = bank.findAccount(user);
                root1 = new FXMLLoader(getClass().getResource("home.fxml"));
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("Bank App - Customer Home");
                stage.setScene(new Scene(root1));
                stage.show();

            }catch (Exception e){

            }finally{
                System.out.println(cust.toString());
            }
        }
        else{
            System.out.println("Rauh Rouh Raggy");
        }
    }

    @FXML
    private void test(ActionEvent event){
        String username = login_username.getText();
        String password = login_password.getText();

        if(Main.admin.getUsername().equals(username) && Main.admin.getPassword().equals(password))
            loadNext(Main.admin);
        for(Iterator<Customer> c = Main.testBank.getCustomers().iterator(); c.hasNext();){
            Customer cust = c.next();
            if(cust.getUsername().equals(username)){
                if(cust.getPassword().equals(password)){
                   loadNext(cust);
                }
            }
            
        }
    }

}
