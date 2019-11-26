package coe528.display;

import coe528.func.Users.Manager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Help implements Initializable{

    @FXML
    Label help_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { Help(); }

    @FXML
    void Help(){
        String help;
        if(Main.currentUser.getClass() == Manager.class) {
            help = "Help: \n\n\n To add a new Customer: \nenter their username, password and \ninitial account balance \n\n" +
                    "To remove a Customer:\nenter their username \n\nUse the last tab to view Customer accounts";
        }
        else{
            help = "Help: \n\n\n To add funds:\nenter the amount and click deposit\n\n To withdraw funds:\n enter the amount and" +
                    "click withdraw\n\nTo make a purchase:\nenter the amount and click purchase";
        }
        help_label.setText(help);
    }





}
