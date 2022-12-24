package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Domain.Donor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ControllerDonor {
    public TextField fieldUsername;
    public PasswordField Password;
    public Button loginBtn;
    public Button btnCancel;
    public TextField fieldUsername2;
    //private Object actionEvent;

    @FXML
    public void initialize() {
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

    public void buttonClick(ActionEvent actionEvent) throws IOException {
        if (fieldUsername.getText().isEmpty()) {
            fieldUsername.getStyleClass().add("poljeNijeIspravno");
            return;
        }
        Donor d = new Donor();
        d = d.searchByFullName(fieldUsername.getText());
        if (d != null) {
            if (!Objects.equals(d.getPassword(), Password.getText()))
                return;
            Stage s = (Stage) loginBtn.getScene().getWindow();
            s.close();

        } else return;
    }

       /* Stage secondstage = new Stage();
        FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/medstaffClick.fxml"));
        Parent root = fl.load();
        Noviprozor noviprozor = fl.getController();
        noviprozor.labels.setText(noviprozor.labels.getText() + fieldUsername.getText());
        secondstage.setTitle("Sign in");
        secondstage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        secondstage.show();*/


    public void buttonClick1(ActionEvent actionEvent) throws IOException {
     /*   if (fieldUsername.getText().isEmpty()) {
            fieldUsername.getStyleClass().add("poljeNijeIspravno");
            return;
        }*/
        Stage secondstage3 = new Stage();
        FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/registerClick.fxml"));
        Parent root = fl.load();
        ControllerRegister noviprozor = fl.getController();
        noviprozor.fieldUsername2.setText(noviprozor.fieldUsername2.getText());
        secondstage3.setTitle("Register form");
        secondstage3.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        secondstage3.show();
    }

    public void akcijaZatvori(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}


 /*Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pozdrav");
        alert.setHeaderText("Zdravo");
        alert.setContentText("Vaše korisničko ime je: "+fieldUsername.getText());
        alert.show();*/
