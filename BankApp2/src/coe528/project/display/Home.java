package coe528.project.display;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;

import coe528.project.func.*;

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
    Account currentAccount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void Home(){
        bank = new Bank(Main.admin);
        currentAccount = Main.currentUser;
    }

    @FXML
    private void makeDeposit(ActionEvent event) throws Exception{
        try{
            bank.makeDeposit(currentAccount, currentAccount.getOwner, (int) value_to_move.getText());
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void makeWithdraw(ActionEvent event){
        try{
            bank.makeWithdraw(currentAccount, currentAccount.getOwner, (int) value_to_move.getText());
        }catch(Exception e){
            System.out.println(e);
        }

    }
    @FXML
    private void makePurchase(ActionEvent event){
        try{
            bank.makePurchase(currentAccount, currentAccount.getOwner, (int) value_to_move.getText());
        }catch(Exception e){
            System.out.println(e);
        }
    }


    @FXML
    private void logout(ActionEvent event){

        Main.currentAccount = null;
        bank.backUp();

        try{
            root1=new FXMLLoader(getClass().getResource("home.fxml"));
            stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Bank App - Customer Home");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){

        }
    }


}
