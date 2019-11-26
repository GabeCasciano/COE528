package coe528.project.display;

import coe528.project.func.Bank.Account;
import coe528.project.func.Bank.Bank;
import coe528.project.func.Users.Customer;
import coe528.project.func.Users.Manager;
import coe528.project.func.Users.User;

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
    static Customer custom;
    Connection conn = null;
    Statement stmnt = null;
    static final String DB_URL = "jdbc:sqlite:/home/student1/gcascian/COE528/BankApp2/src/bank.db";

    static User currentAccount;

    Scene login_scene;

    public void loadAdmin(){
        try{
            conn = DriverManager.getConnection(DB_URL);
            stmnt = conn.createStatement();

            ResultSet rs = stmnt.executeQuery("SELECT * FROM Managers");

            admin = new Manager(rs.getString("username"), rs.getString("passord"));

        }catch (Exception e){
        }
        finally{           
            try{
                admin = new Manager("admin", "admin1234");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void loadAdmin(boolean bo){
        try{
            admin = new Manager("admin", "admin1234");
        }catch(Exception e){
            
        }
    }

    public void initBank(boolean bo) throws Exception{
        testBank = new Bank(admin);
        custom = new Customer("gabe1234", "password1234");
        testBank.addAccount(custom, 0, admin);
        if(bo)
           testBank.loadBackUp();
        else
            testBank.addCustomer(custom, admin);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        loadAdmin(true);
        initBank(false);
        System.out.println(testBank.findAccount(custom));
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        login_scene = new Scene(root, 220, 220);
        primaryStage.setTitle("Bank App - Login");
        primaryStage.setScene(login_scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}


