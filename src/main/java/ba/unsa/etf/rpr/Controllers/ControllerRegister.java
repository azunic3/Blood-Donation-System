package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DonorDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import ba.unsa.etf.rpr.business.BloodManager;
import ba.unsa.etf.rpr.business.DonorManager;
import ba.unsa.etf.rpr.business.HospitalManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.SQLException;

/**
 * JavaFX Controller for Registration window
 * @author Azra Žunić
 * version 1.3
 */
public class ControllerRegister {
    public Button btnCancel;
    public TextField fieldUsername2;
    public TextField fieldPassword;
    public TextField fieldPhoneNumber;
    public DatePicker fieldDateofBirth;
    public CheckBox F;
    public ChoiceBox<Hospital> hospitals=new ChoiceBox<>();
    public ChoiceBox<Blood> bloods=new ChoiceBox<>();
    public CheckBox M;
    public CheckBox fldAlready;
    public Button btnOK;

    private Donor d=new Donor();
    private HospitalManager hospitalManager=new HospitalManager();
    private BloodManager bloodManager=new BloodManager();

    private DonorManager donorManager = new DonorManager();

    private DonorDaoSQLImpl dao = new DonorDaoSQLImpl();

    /**
     * specifies actions on ChoiceBox option
     */
    public void initialize(){
        try {
            hospitals.setItems(FXCollections.observableList(hospitalManager.getAll()));
            bloods.setItems(FXCollections.observableList(bloodManager.getAll()));
        } catch (BloodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * action on submit button that saves new donor into db table
     * @param actionEvent
     * @throws BloodException
     */
    public void akcijaSubmit(ActionEvent actionEvent) throws BloodException {
      try{ d =new Donor();
        if (fieldUsername2.getText().isEmpty()  || fieldPassword.getText().isEmpty() || fldAlready.getText().isEmpty() ) {
            HomeController m=new HomeController();
            m.setNoviprozor2(null);
            new Alert(Alert.AlertType.NONE,"Invalid registration",ButtonType.OK).show();
        }
        if(M.isSelected() && F.isSelected())
            new Alert(Alert.AlertType.NONE,"You can only choose one",ButtonType.OK).show();
            d.setFullName(fieldUsername2.getText());
            d.setPassword(fieldPassword.getText());
            d.setDateOfBirth(Date.valueOf(fieldDateofBirth.getValue()));
            d.setAlreadyDonated(fldAlready.getText());
            d.setPhoneNumber(Integer.parseInt(fieldPhoneNumber.getText()));
            d.setBloodType_id_fk(bloods.getValue());
            d.setFk_Hospital(hospitals.getValue());
          if(F.isSelected())
             d.setGender("F");
          else if(M.isSelected())
         d.setGender("M");
           donorManager.add(d);
           refreshDonors();

        } catch (BloodException | SQLException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
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

    /**
     * setting values
     */
    private void refreshDonors() {
        fieldUsername2.setText("");
        fieldPassword.setText("");
        fldAlready.setText("");
        fieldDateofBirth.setValue(null);
        fieldPhoneNumber.setText("");
        F.setText("");
        M.setText("");
        hospitals.setValue(null);
        bloods.setValue(null);
    }
}