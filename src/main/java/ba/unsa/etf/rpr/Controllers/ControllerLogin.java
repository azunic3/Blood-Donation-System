package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX Controller for login popup window
 * @author Azra Žunić
 */
public class ControllerLogin {
    public TextField fieldUsername;
    public PasswordField Password;
    public Button loginBtn;
    public Button btnCancel;

    /**
     * special class that specifies actions on text fields
     * using css files to style text areas
     */
    @FXML
    public void initialize() {
        fieldUsername.getStyleClass().add("poljeNijeIspravno");
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
    public void buttonClick(ActionEvent actionEvent) throws BloodException {
        try {
            if (fieldUsername.getText().isEmpty()) {
                fieldUsername.getStyleClass().add("poljeNijeIspravno");
                return;
            }
            Donor d = DaoFactory.donorDao().searchByDName(fieldUsername.getText());
            if (d != null) {
                if (!Objects.equals(d.getPassword(), Password.getText()))
                    new Alert(Alert.AlertType.NONE,"Incorrect password", ButtonType.OK).show();
                Stage s = (Stage) loginBtn.getScene().getWindow();
                s.close();
            }
            Stage stage = new Stage();
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/model.fxml"));
            Parent root = fl.load();
            ControllerModel prvi = fl.getController();
            prvi.setIme(fieldUsername.getText());
            stage.setTitle("Medical history");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e){
            new Alert(Alert.AlertType.NONE,"Please create an account", ButtonType.OK).show();
        }
    }

    /**
     * opening new window for registration
     * @param actionEvent
     * @throws IOException
     */
    public void buttonClick1(ActionEvent actionEvent) throws IOException {
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


