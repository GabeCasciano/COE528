package coe528.display;

import coe528.func.Bank.Account;
import coe528.func.Bank.Bank;
import coe528.func.Users.Customer;
import coe528.func.Users.Manager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class manager implements Initializable {

    //account summary
    @FXML
    Label selected_accnt_number;
    @FXML
    Label selected_accnt_owner;
    @FXML
    Label selected_accnt_balance;

    //manager summary
    @FXML
    Label manager_number;
    @FXML
    Label manager_username;

    //add customer
    @FXML
    TextField add_user;
    @FXML
    TextField add_password;
    @FXML
    TextField add_init_val;
    //remove customer
    @FXML
    TextField remove_user;

    @FXML
    ListView customer_list;

    //remove manager
    @FXML
    TextField add_manager;
    @FXML
    TextField add_man_password;

    @FXML
    Button logout_btn;
    Bank bank;

    @FXML
    private void managerSummary(){
        try{
            manager_number.setText(Integer.toString(Main.currentUser.getId()));
            manager_username.setText(Main.currentUser.getUsername());
        }catch (Exception e){}
    }


    void updateListView() {
        customer_list.setEditable(true);
        customer_list.setCellFactory(TextFieldListCell.forListView());
        customer_list.getItems().clear();

        for (Iterator<Customer> c = bank.getCustomers().iterator(); c.hasNext(); ) {
            Customer acnt = c.next();

            customer_list.getItems().add(acnt.getUsername());
        }
        customer_list.setEditable(false);
    }
    public void test(){
        for(Iterator<Account> c = bank.getAccounts().iterator(); c.hasNext();){
            Account account = c.next();

            System.out.println(account);
        }
    }
    @FXML
    public void addCustomer(ActionEvent event) {
        try {
            Double initVal = 0.0;

            String username = add_user.getText();
            String password = add_password.getText();
            Customer customer = new Customer(username, password);

            if(!add_init_val.getText().isEmpty())
                initVal = Double.parseDouble(add_init_val.getText());


            bank.addCustomer(customer, (Manager)Main.currentUser);
            bank.addAccount(customer, initVal, (Manager)Main.currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            add_user.clear();
            add_password.clear();
            add_init_val.clear();
            updateListView();

            System.out.println("Account Added:"+  bank.getAccounts().get(Math.abs(bank.getCustomers().size() - 1)).toString());
            System.out.println("Customer Added: " + bank.getCustomers().get(Math.abs(bank.getCustomers().size() - 1)).toString());
        }
    }

    @FXML
    public void removeCustomer(ActionEvent event) {

        try {
            Account acnt = new Account();
            String username = "";

            if(!remove_user.getText().isEmpty())
                username = remove_user.getText();

            acnt = new Account(bank.findAccount(bank.findCustomer(username)));

            System.out.println(acnt.getOwner());
            if(acnt != null) {
                bank.removeAccount(new Account(acnt), (Manager)Main.currentUser);
                bank.removeCustomer(new Customer(acnt.getOwner()), (Manager)Main.currentUser);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            remove_user.clear();
            test();
            System.out.println("Customer Removed: " + remove_user.getText());
        }
    }

    @FXML
    private void addManager(){
        try {

            String username = add_manager.getText();
            String password = add_man_password.getText();
            Manager man = new Manager(username, password);

            bank.addManager(man, (Manager)Main.currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            add_manager.clear();
            add_man_password.clear();

            System.out.println("Manager Added: " + bank.getManagers().get(Math.abs(bank.getManagers().size() - 1)).toString());
        }

    }
    @FXML
    private void removeManager(){

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


    @FXML
    void manager() {
        bank = new Bank(Main.admin);
        updateListView();
        managerSummary();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manager();
        customer_list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                try {
                    String current = (String) t1;
                    Account acnt = bank.findAccount(bank.findCustomer(current));

                    selected_accnt_number.setText(Integer.toString(acnt.getAccountNumber()));
                    selected_accnt_owner.setText(acnt.getOwner().getUsername());
                    selected_accnt_balance.setText(Double.toString(acnt.getAccountBalance()));
                }catch (Exception e){
                    System.out.println(e.toString());
                }
            }
        });
    }

    @FXML
    private void help(){

    }

    @FXML
    private void logout() {
        Login.logout();
    }


}
