package coe528.display;

import coe528.func.Users.Customer;
import coe528.func.Users.Manager;
import coe528.func.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable{
    
    
    @FXML TextField login_username;
    @FXML PasswordField login_password;

    @FXML
    void Login(){
        System.out.println("Login ....");
    }

    public static Stage nextStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       Login();
    }

    private void loadNext(User user, boolean isManger){

        Parent root1;
        Main.currentUser = user;

        try{
            nextStage = new Stage();
            nextStage.initModality(Modality.WINDOW_MODAL);
            nextStage.initStyle(StageStyle.DECORATED);
            if(isManger){
                root1 = FXMLLoader.load(getClass().getResource("manager.fxml"));
                nextStage.setTitle("Bank App - Manager Home");
                nextStage.setScene(new Scene(root1));
            }
            else{
                root1 = FXMLLoader.load(getClass().getResource("customer.fxml"));
                nextStage.setTitle("Bank App - Customer Home");
                nextStage.setScene(new Scene(root1));
            }
            nextStage.show();
            Main.start_stage.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }finally {
            login_password.clear();
            login_username.clear();
        }

    }

    @FXML
    private void login(ActionEvent event){
        String username = login_username.getText();
        String password = login_password.getText();
        try {
            try {
                Manager man = Main.testBank.findManager(username);
                if (man.getPassword().equals(password))
                    loadNext(man, true);

            }catch (Exception e){ System.out.println("Manager not found: " + username);}

            try {
                Customer customer = Main.testBank.findCustomer(username);
                if (customer.getPassword().equals(password))
                    loadNext(customer, false);

            }catch (Exception e){System.out.println("Customer not found:" + username);}

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void logout() {

        Stage stage;
        Main.currentUser = null;

        try {
            stage = Main.start_stage;
            stage.show();
            nextStage.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
