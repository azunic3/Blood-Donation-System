package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.PatientDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.business.PatientManager;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * JavaFX Controller for Medical staff action
 * shows list of all patient's information
 */
public class MedstaffController {
    public CheckBox F;
    public CheckBox M;
    public TextField fieldHosp;
    public TextField fieldBlood;
    public TextField fieldID;
    public TextField fieldName;
    public DatePicker DatePick;
    private PatientDaoSQLImpl patientDaoSQL = new PatientDaoSQLImpl();
    public Button btnCancel;
    public Button DeletePat;
    public TextField search;
    public TableView patientsTable;

    public BorderPane patientsScreen;
    //components
    public TableColumn<Patient, Integer> Idcol;
    public TableColumn<Patient, String> namecol;
    public TableColumn<Patient, String> gendercol;
    public TableColumn<Patient, LocalDate> datecol;
    public TableColumn<Patient, Integer> hospcol;
    public TableColumn<Patient, Integer> bloodcol;
    PatientManager manager = new PatientManager();

    /**
     * special class that specifies actions on TableColumns
     *
     * @throws BloodException
     */
    @FXML
    public void initialize() throws BloodException {
        Idcol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("Id"));
        namecol.setCellValueFactory(new PropertyValueFactory<Patient, String>("Full_Name"));
        gendercol.setCellValueFactory(new PropertyValueFactory<Patient, String>("Gender"));
        datecol.setCellValueFactory(new PropertyValueFactory<Patient, LocalDate>("DateOfBirth"));
        hospcol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("fk_Hospital_id"));
        bloodcol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("Fk_BloodType"));
        try {
            patientsTable.setItems(FXCollections.observableList(patientDaoSQL.getAll()));
            patientsTable.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * search patients event handler
     *
     * @param actionEvent
     * @throws BloodException
     */
    @FXML
    public void searchPatient(ActionEvent actionEvent) throws BloodException {
        Patient e = patientDaoSQL.getById(Integer.parseInt(search.getText()));
        ObservableList<Patient> emp = FXCollections.observableArrayList();
        emp.add(e);
        patientsTable.setItems(emp);
    }

    /**
     * closing window
     */
    public void akcijaZatvori(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * method used for deleting a patient from the table
     * @param actionEvent
     * @throws BloodException
     */
    @FXML
    public void DeletePat(ActionEvent actionEvent) throws BloodException {
        try {
            Patient ee = (Patient) patientsTable.getSelectionModel().getSelectedItem();
            manager.delete(ee.getId());
            patientsTable.getItems().remove(ee);
        } catch (BloodException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * adding a new patient to the table
     * @param actionEvent
     */

    public void addNew(ActionEvent actionEvent) {
        try {
            Patient p = new Patient();
            p.setFull_Name(fieldName.getText());
            p.setId(Integer.parseInt(fieldID.getText()));

            if(F.isSelected())
                p.setGender("F");
            else if(M.isSelected())
                p.setGender("M");

//            p.setFk_Hospital_id(Integer.parseInt(fieldHosp.getText()));
//            p.setFk_BloodType(Integer.parseInt(fieldBlood.getText()));
//
//            p.setDateOfBirth(DatePick.getValue());


            patientsTable.getItems().add(p);
            p=manager.add(p);
            fieldName.setText("");
            fieldID.setText("");
            fieldHosp.setText("");
            fieldBlood.setText("");
            DatePick= null;
            //za gender choice box set
        } catch(BloodException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
