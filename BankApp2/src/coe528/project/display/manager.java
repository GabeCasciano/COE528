package coe528.project.display;

import coe528.project.func.Bank.Bank;
import coe528.project.func.Users.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class manager implements Initializable {

    @FXML Label accnt_id_number;
    @FXML Label accnt_owner;
    @FXML Label accnt_balance;

    @FXML TextField customer_user;
    @FXML TextField customer_user_id;
    @FXML Button add_accnt_btn;
    @FXML Button remove_accnt_btn;
    @FXML TextField customer_init_value;
    
    @FXML TextField customer_user_2;
    @FXML TextField customer_password;
    @FXML TextField customer_user_id_2;
    @FXML Button add_cusotomer_btn;
    @FXML Button remove_customer_btn;
    
    @FXML ListView customer_list;
    
    @FXML Button logout_btn;
    Bank bank;
    
    
    void updateListView(){
        ListView<String> list = new ListView<>();
        customer_list.setEditable(true);
        customer_list.setCellFactory(TextFieldListCell.forListView());
        for(Iterator<Customer> c = bank.getCustomers().iterator(); c.hasNext();){
            Customer cust = c.next();
            
            customer_list.getItems().add(cust.getUsername());
        }
    }
    
    @FXML
    public void addCustomer(ActionEvent event){
        System.out.println("Adding Customer");
        try{
            String username = customer_user.getText();
            String password = customer_password.getText();
            bank.addCustomer(new Customer(username, password), Main.admin);
            System.out.println("final");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            customer_user.clear();
            customer_password.clear();
            customer_user_id_2.clear();
            updateListView();
            System.out.println("final2");
            System.out.println(bank.getCustomers().get(1).toString());
        }
    }
    @FXML
    public void removeCustomer(ActionEvent event){
        try{
            String username = customer_user.getText();
            String password = customer_password.getText();
            bank.removeCustomer(new Customer(username, password), Main.admin);
        }catch(Exception e){
            e.printStackTrace();
        }
        customer_user.clear();
        customer_password.clear();
        customer_user_id_2.clear();
        updateListView();
    }
    @FXML
    public void addAccount(ActionEvent event){
        try{
            String username = customer_user_2.getText();
            String id = customer_user_id.getText();
            double initval = Double.parseDouble(customer_init_value.getText());
            Customer c = bank.findCustomer(username);
            bank.addAccount(c, initval, Main.admin);
        }catch(Exception e){
            e.printStackTrace();
        }
        customer_user.clear();
        customer_password.clear();
        customer_user_id_2.clear();
        updateListView();
    }
    @FXML
    public void removeAccount(ActionEvent event){
        try{
            String username = customer_user.getText();
            String password = customer_password.getText();
            Customer c = bank.findCustomer(username);
            bank.removeAccount(c, Main.admin);
        }catch(Exception e){
            e.printStackTrace();
        }
        customer_user.clear();
        customer_password.clear();
        customer_user_id_2.clear();
        updateListView();
    }
    
    
    @FXML
    void manager(){
        bank = new Bank(Main.admin);
        updateListView();
        System.out.println("init man");
        
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manager();
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
            e.printStackTrace();
        }
    }


}
