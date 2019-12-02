package coe528.display;

import coe528.func.Bank.Bank;
import coe528.func.Users.Customer;
import coe528.func.Users.Manager;
import coe528.func.Users.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class Main extends Application {

    //Bank Variables
    static Bank testBank;
    static Manager admin;
    static Manager admin2;
    static Customer custom;
    Connection conn = null;
    Statement stmnt = null;
    public static Stage start_stage;
    static final String DB_URL = "jdbc:sqlite:/home/student1/gcascian/COE528/BankApp2/src/bank.db";

    static User currentUser;

    public void initBank(boolean db) throws Exception{
        testBank = new Bank(admin);

        if(db)
           testBank.loadBackUp();
        else {
            testBank.addCustomer(custom, admin);
            testBank.addAccount(custom, 100, admin);
        }
        System.out.println("Bank Initialized");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        admin = new Manager("admin", "admin1234");
        System.out.println("Admins Initialized");

        initBank(true);

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        primaryStage.setTitle("Bank App - Login");
        primaryStage.setScene(new Scene(root, 250, 220));
        start_stage = primaryStage;
        primaryStage.show();
    }

    @Override
    public void stop(){
        testBank.backUp();
        System.out.println("Bank Backed up\nExitting");
    }

    public static void main(String[] args) {
        launch(args);
    }
}


