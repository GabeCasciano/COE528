package coe528.project.display;

import coe528.project.func.Bank.Bank;
import coe528.project.func.Users.Customer;
import coe528.project.func.Users.Manager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.logging.Handler;

public class Main extends Application {

    //Bank Variables
    static Bank testBank;
    static Manager admin;
    Connection conn = null;
    Statement stmnt = null;
    static final String DB_URL = "jdbc:sqlite:bank.db";

    Button login_enter;
    Handler login_handler;
    TextField login_username;
    PasswordField login_password;

    Scene login_scene;

    public void loadAdmin(){
        try{
            conn = DriverManager.getConnection(DB_URL);
            stmnt = conn.createStatement();

            ResultSet rs = stmnt.executeQuery("SELECT * FROM Managers");

            admin = new Manager(rs.getString("username"), rs.getString("passord"));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initBank(){
        testBank = new Bank(admin);
        testBank.loadBackUp();
    }


    public void login(){
        String username = login_username.getText();
        String password = login_password.getText();

        for(Iterator<Customer> c = testBank.getCustomers().iterator(); c.hasNext();){
            Customer customer = c.next();
            if(customer.getUsername().equals(username)){
                if(customer.getPassword().equals(password)){
                    //enter as customer
                }
            }
        }
        for(Iterator<Manager> m = testBank.getManagers().iterator(); m.hasNext();){
            Manager manager = m.next();
            if(manager.getUsername().equals(username)){
                if(manager.getPassword().equals(password)){
                    //enter as manager
                }
            }
        }
    }

    //public Scene setupLogin(){  }
    //public Scene setupCustomerHome(){ }
    //public Scene setupManagerHome(){ }


    @Override
    public void start(Stage primaryStage) throws Exception{
        //loadAdmin();
        //initBank();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        login_scene = new Scene(root, 220, 220);
        primaryStage.setScene(login_scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}


