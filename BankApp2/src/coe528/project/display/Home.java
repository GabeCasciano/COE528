package coe528.project.display;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import coe528.project.func.Bank.Account;
import coe528.project.func.Bank.Bank;
import coe528.project.func.Users.Customer;
import coe528.project.func.Users.User;
import java.text.DecimalFormat;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Home implements Initializable{

    @FXML Button logout_btn;
    @FXML Label accnt_id_number;
    @FXML Label accnt_owner;
    @FXML Label accnt_balance;

    @FXML Button deposit_button;
    @FXML Button withdraw_button;
    @FXML TextField value_to_move;

    @FXML TextField purchase_amount;
    @FXML Button purchase_enter;

    Bank bank;
    Customer currentUser;
    Account acnt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Home();
    }

    private void updateAccount(){
        acnt = bank.findAccount(currentUser);
        accnt_id_number.setText(Integer.toString(acnt.getAccountNumber()));
        accnt_owner.setText(acnt.getOwner().getUsername());
        accnt_balance.setText("$ " + new String(new DecimalFormat("#.00#").format(acnt.getAccountBalance())));
    }
    
    @FXML
    void Home(){
        bank = Main.testBank;
        acnt = bank.findAccount(currentUser);
        updateAccount();
        
    }

    @FXML
    private void makeDeposit(ActionEvent event) throws Exception{
        try{
            bank.makeDeposit(acnt, acnt.getOwner(), Integer.parseInt(value_to_move.getText()));
        }catch(Exception e){
            System.out.println(e);
        }finally{
            updateAccount();
        }
    }

    @FXML
    private void makeWithdraw(ActionEvent event){
        try{
            bank.makeWithdraw(acnt, acnt.getOwner(), Integer.parseInt(value_to_move.getText()));
        }catch(Exception e){
            System.out.println(e);
        }finally{
            updateAccount();
        }

    }
    @FXML
    private void makePurchase(ActionEvent event){
        try{
            bank.makePurchase(acnt, acnt.getOwner(), Integer.parseInt(value_to_move.getText()));
        }catch(Exception e){
            System.out.println(e);
        }finally{
            updateAccount();
        }
    }


    @FXML
    private void logout(){

        Parent root1;
        Stage stage;
        
        Main.currentAccount = null;
        bank.backUp();

        try{
            root1 = FXMLLoader.load(getClass().getResource("login.fxml"));
            stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Bank App - Login");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){

        }
    }


}
