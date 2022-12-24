package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DonorDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.exceptions.BloodException;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ControllerRegister {
    public Button btnCancel;
    public TextField fieldUsername2;
    public PasswordField fieldPassword;
    public TextField fieldPhoneNumber;
    public TextField fieldAdress;
    public DatePicker fieldDateofBirth;
    public CheckBox fieldGender;
    public CheckBox fldAlready;
    public Button btnOK;

    @FXML
    public void initialize() {
        fieldUsername2.getStyleClass().add("poljeNijeIsoravno");
        fieldUsername2.textProperty().addListener(new ChangeListener<String>() {

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
    }



    private DonorDaoSQLImpl dao=new DonorDaoSQLImpl();

    public void akcijaZatvori(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void akcijaSubmit(ActionEvent actionEvent) {
        if (fieldUsername2.getText().isEmpty()) {
            fieldUsername2.requestFocus();
            fieldUsername2.getStyleClass().removeAll("poljeJeIspravno");
            fieldUsername2.getStyleClass().add("poljeNijeIspravno");
        }
        if(fieldPassword.getText().isEmpty()){
            fieldPassword.requestFocus();
            fieldPassword.getStyleClass().removeAll("poljeJeIspravno");
            fieldPassword.getStyleClass().add("poljeNijeIspravno");
        }
        if(fldAlready.getText().isEmpty()){
            fldAlready.requestFocus();
            fldAlready.getStyleClass().removeAll("poljeJeIspravno");
            fldAlready.getStyleClass().add("poljeNijeIspravno");
        }

       Donor p=new Donor();
        p=p.searchByFullName(fieldUsername2.getText());
        //bilo razlicito
        if(p==null)
            return;
        p.setFullName(fieldUsername2.getText());
        p.setPassword(fieldPassword.getText());
        try{
            dao.add(p);
        } catch (BloodException e) {
            System.out.println("Problem with adding a new donor");
            throw new RuntimeException(e);
        }
    }

    private boolean validiraj() {
        boolean SveIspravno = true;
        if (fieldUsername2.getText().isEmpty()) {
            Alert upozorenje = new Alert(Alert.AlertType.ERROR);
            upozorenje.setTitle("Field cannot be empty");
            //upozorenje.showAndWait();
            fieldUsername2.requestFocus();
            fieldUsername2.getStyleClass().removeAll("poljeJeIspravno");
            fieldUsername2.getStyleClass().add("poljeNijeIspravno");
            SveIspravno = false;
        }
        return SveIspravno;
    }
}
