package coe528.display;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpWindow implements Initializable {

    @FXML
    private void close(){
        Stage stage;

        try {
            stage = Login.nextStage;
            stage.show();
            customer.window.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}