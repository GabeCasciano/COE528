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

    public void loadAdmin(boolean bo){
        try{
            admin = new Manager("admin", "admin1234");
            admin2 = new Manager("adnin2", "admin2121");
            System.out.println("Admins Initialized");
        }catch(Exception e){
            
        }

    }

    public void initBank(boolean bo) throws Exception{
        testBank = new Bank(admin);
        custom = new Customer("gabe1234", "password1234");

        if(bo)
           testBank.loadBackUp();
        else
            testBank.addCustomer(custom, admin);

        testBank.addAccount(custom, 100, admin);
        System.out.println("Bank Initialized");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        loadAdmin(true);
        initBank(false);

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        primaryStage.setTitle("Bank App - Login");
        primaryStage.setScene(new Scene(root, 250, 220));
        start_stage = primaryStage;
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


