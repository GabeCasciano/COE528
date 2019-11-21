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

    static Account currentAccount;

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

    @Override
    public void start(Stage primaryStage) throws Exception{
        loadAdmin();
        initBank();
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


