package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
//import javafx.event.ActionEvent;
//import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class Contollerlogin {
    public TextField fieldUsername;
    public Button loginBtn;
    //private Object actionEvent;

    @FXML
    public void initialize() { //posebna
        fieldUsername.getStyleClass().add("poljeNijeIsoravno");
         fieldUsername.textProperty().addListener(new ChangeListener<String>() {
             @Override
             public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                 if (fieldUsername.getText().trim().isEmpty()) {
                     fieldUsername.getStyleClass().removeAll("poljeJeIspravno");
                     fieldUsername.getStyleClass().add("poljeNijeIspravno");
                 } else {
                     fieldUsername.getStyleClass().removeAll("poljeNijeIspravno");
                     fieldUsername.getStyleClass().add("poljeJeIspravno");
                 }
             }
         });
}

    public void buttonClick(ActionEvent actionEvent) {
        if (fieldUsername.getText().isEmpty()){
            fieldUsername.getStyleClass().add("poljeNijeIspravno");
            return;
        }
        //eh ovako, aj pisem na slack vazi
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pozdrav");
        alert.setHeaderText("Zdravo");
        alert.setContentText("Vaše korisničko ime je: "+fieldUsername.getText());
        alert.show();
    }
    }




