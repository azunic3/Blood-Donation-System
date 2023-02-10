package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.PatientDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import ba.unsa.etf.rpr.business.BloodManager;
import ba.unsa.etf.rpr.business.HospitalManager;
import ba.unsa.etf.rpr.business.PatientManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.time.LocalDate;

/**
 * JavaFX Controller for Medical staff action
 * shows list of all patient's information
 * gives options to view, add, delete and update information about patients
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

    public TableColumn<Patient, Integer> Idcol;
    public TableColumn<Patient, String> namecol;
    public TableColumn<Patient, String> gendercol;
    public TableColumn<Patient, LocalDate> datecol;
    public TableColumn<Patient, Integer> hospcol;
    public TableColumn<Patient, Integer> bloodcol;
    PatientManager manager = new PatientManager();
    private final PatientManager patientManager = new PatientManager();
    private final HospitalManager bloodManager = new HospitalManager();
    private final BloodManager bManager = new BloodManager();

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
     * adding a new patient to the database and table
     * @param actionEvent
     */

    public void addNew(ActionEvent actionEvent) {
        try {
            Patient p = new Patient();
            p.setFull_Name(fieldName.getText());
            if(F.isSelected())
                p.setGender("F");
            else if(M.isSelected())
                p.setGender("M");

        p.setDateOfBirth(DatePick.getValue());
        p.setFk_Hospital_id(bloodManager.searchByName(fieldHosp.getText()));
        p.setFk_BloodType(bManager.searchByBloodGroup(fieldBlood.getText()));
            patientsTable.getItems().add(p);
            p=manager.add(p);
           refreshPatients();
        } catch(BloodException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * search patients by name event handler
     * @param actionEvent
     * @throws BloodException
     */

    public void searchPatients(ActionEvent actionEvent) {
        try {
            patientsTable.setItems(FXCollections.observableList(patientManager.searchPatients(search.getText())));
            patientsTable.refresh();
        } catch (BloodException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * fetch patients from database
     */
    private void refreshPatients(){
        try {

            patientsTable.setItems(FXCollections.observableList(patientManager.getAll()));
            patientsTable.refresh();
        } catch (BloodException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * closing window
     */
    public void akcijaZatvori(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}