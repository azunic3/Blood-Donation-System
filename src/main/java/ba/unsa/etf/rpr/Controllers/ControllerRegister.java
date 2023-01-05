package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DonorDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.beans.value.ObservableValue;

/**
 * JavaFX Controller for Registration window
 * @author Azra Žunić
 * version 1.3
 */
public class ControllerRegister {
    //attributes
    public Button btnCancel;
    public TextField fieldUsername2;
    public PasswordField fieldPassword;
    public TextField fieldPhoneNumber;
    public DatePicker fieldDateofBirth;
    public TextField fieldGender;
    public CheckBox fldAlready;
    public Button btnOK;
    private Donor d;
    private DonorDaoSQLImpl dao = new DonorDaoSQLImpl();

    /**
     * action od submit button that saves new donor into db table
     * @param actionEvent
     * @throws BloodException
     */
    public void akcijaSubmit(ActionEvent actionEvent) throws BloodException {
        d=new Donor();
        fieldUsername2.getStyleClass().add("poljeNijeIspravno");
        fieldUsername2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (fieldUsername2.getText().trim().isEmpty()) {
                    fieldUsername2.getStyleClass().removeAll("poljeJeIspravno");
                    fieldUsername2.getStyleClass().add("poljeNijeIspravno");
                } else {
                    fieldUsername2.getStyleClass().removeAll("poljeNijeIspravno");
                    fieldUsername2.getStyleClass().add("poljeJeIspravno");
                }
            }
        });
        if (fieldUsername2.getText().isEmpty()  || fieldPassword.getText().isEmpty() || fldAlready.getText().isEmpty())
        return;
        Donor  k=new Donor();
        k=k.searchByDonorsName(fieldUsername2.getText());
        if (k!=null)
            return;
        d.setFullName (fieldUsername2.getText());
        d.setPassword(fieldPassword.getText());
        d.setGender(fieldGender.getText());
        d.setAlreadyDonated(fldAlready.getText());

        try {
            dao.add(d);
        } catch (Exception e) {
            System.out.println("Problem with adding a new donor in the database");
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) btnOK.getScene().getWindow();
        stage.close();
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
