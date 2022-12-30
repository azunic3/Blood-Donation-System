package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Exceptions.BloodException;
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

/**
 * JavaFX Controller for login popup window
 *
 * @author Azra Žunić
 */
public class ControllerLogin {
    public TextField fieldUsername;
    public PasswordField Password;
    public Button loginBtn;
    public Button btnCancel;
    public TextField fieldUsername2;
    //private Object actionEvent;

    /**
     * special class that specifies actions on text fields
     */
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

    /**
     * checking if donor exists in a table already, so he can sign in
     * @param actionEvent
     * @throws IOException
     * @throws BloodException
     */
    public void buttonClick(ActionEvent actionEvent) throws IOException, BloodException {
        Donor d = new Donor();
        if (fieldUsername.getText().isEmpty()) {
            fieldUsername.getStyleClass().add("poljeNijeIspravno");
            return;
        }
        d= (Donor) d.searchByDonorsName(fieldUsername.getText());
        if (d!=null) {
            if(!Objects.equals(d.getPassword(), Password.getText()))
                return;
            Stage s=(Stage) loginBtn.getScene().getWindow();
            s.close();}
        else return;
    }

    /**
     * opening new window for registration
     * @param actionEvent
     * @throws IOException
     */
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
        secondstage3.setResizable(false);
        secondstage3.show();
    }

    /**
     * action on close button
     * @param actionEvent
     */
    public void akcijaZatvori(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}


