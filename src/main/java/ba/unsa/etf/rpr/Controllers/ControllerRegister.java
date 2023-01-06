package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Dao.DonorDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.beans.value.ObservableValue;

import java.sql.Date;

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
   public CheckBox F;
   public CheckBox M;
    public CheckBox fldAlready;
    public Button btnOK;

    private Donor d;
    private DonorDaoSQLImpl dao = new DonorDaoSQLImpl();

    /**
     * action od submit button that saves new donor into db table
     * @param actionEvent
     * @throws BloodException
     */
    public void akcijaSubmit(ActionEvent actionEvent)  {
        d=new Donor();

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

            if(F.isSelected())
                d.setGender("F");
            else if(M.isSelected())
                d.setGender("M");

            try {
                DaoFactory.donorDao().add(d);
            } catch (Exception e1) {
                System.out.println("Problem with adding a new user in the database");
                throw new RuntimeException(e1);
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
