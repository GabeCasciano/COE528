package coe528.display;

import coe528.func.Bank.Account;
import coe528.func.Bank.Bank;
import coe528.func.Users.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class customer implements Initializable{

    @FXML Button logout_btn;
    @FXML Label accnt_number;
    @FXML Label accnt_owner;
    @FXML Label accnt_balance;

    @FXML TextField value_to_move;

    @FXML TextField purchase_amount;
    @FXML Button purchase_enter;

    Bank bank;
    Customer currentUser;
    Account acnt;

    public static Stage window;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Home();
    }

    private void alertWindow(){
        Parent root1;

        try {
            window = new Stage();
            window.initModality(Modality.WINDOW_MODAL);
            window.initStyle(StageStyle.DECORATED);

            root1 = FXMLLoader.load(getClass().getResource("help_window.fxml"));

            window.setTitle("Alert");
            window.setScene(new Scene(root1));
            window.show();
        }catch(Exception f){
            f.printStackTrace();
        }
    }

    @FXML
    private void helpWindow(){
        Stage helpWindow;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("help.fxml"));
            helpWindow = new Stage();
            helpWindow.initStyle(StageStyle.DECORATED);
            helpWindow.initModality(Modality.WINDOW_MODAL);
            helpWindow.setTitle("Help Window");
            helpWindow.setScene(new Scene(root, 325, 250));
            helpWindow.show();

        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private void updateAccount(){
        try {
            acnt = bank.findAccount(currentUser);
            accnt_number.setText(Integer.toString(acnt.getAccountNumber()));
            accnt_owner.setText(acnt.getOwner().getUsername());
            String temp = new DecimalFormat("#.00#").format(acnt.getAccountBalance());
            accnt_balance.setText("$ " + temp);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    void Home(){
        try {
            bank = new Bank(Main.admin);
            currentUser = (Customer) Main.currentUser;
            updateAccount();
        }catch(Exception e) {

        }
    }

    @FXML
    private void makeDeposit(ActionEvent event) throws Exception{
        try{
            bank.makeDeposit(acnt, currentUser, Integer.parseInt(value_to_move.getText()));
        }catch(Exception e){
            System.out.println(e);
        }finally{
            updateAccount();
            value_to_move.clear();
        }
    }

    @FXML
    private void makeWithdraw(ActionEvent event){
        try{
            bank.makeWithdraw(acnt, currentUser, Integer.parseInt(value_to_move.getText()));
        }catch(IllegalArgumentException i){
            alertWindow();
        }
        catch(Exception e){

        }finally{
            updateAccount();
            value_to_move.clear();
        }

    }
    @FXML
    private void makePurchase(ActionEvent event){
        try{
            bank.makePurchase(acnt, currentUser, Integer.parseInt(purchase_amount.getText()));
        }catch(Exception e){
           alertWindow();
        }finally{
            updateAccount();
            purchase_amount.clear();
        }
    }


    @FXML
    private void logout(){
        Login.logout();
    }


}
