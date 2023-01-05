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

import java.sql.Date;

/**
 * JavaFX Controller for Medical staff action
 * shows list of all patient's information
 */
public class MedstaffController {
    //managers
    private final PatientManager pManager = new PatientManager();
    private PatientDaoSQLImpl patientDaoSQL=new PatientDaoSQLImpl();
    public Button btnCancel;
    public TextField search;
    public TableView patientsTable;

    public BorderPane patientsScreen;
    //components
    public TableColumn<Patient, Integer> Idcol;
    public TableColumn<Patient,String> namecol;
    public TableColumn<Patient, String> gendercol;
    public TableColumn<Patient, Date> datecol;
    public TableColumn<Hospital,String> hospcol;

    /**
     * special class that specifies actions on TableColumns
     * @throws BloodException
     */
    @FXML
    public void initialize() throws BloodException {
        Idcol.setCellValueFactory(new PropertyValueFactory<Patient,Integer>("Id"));
        namecol.setCellValueFactory(new PropertyValueFactory<Patient,String>("Full_Name"));
        gendercol.setCellValueFactory(new PropertyValueFactory<Patient, String>("Gender") );
        datecol.setCellValueFactory(new PropertyValueFactory<Patient,Date>("DateOfBirth"));
        try {
            patientsTable.setItems(FXCollections.observableList(patientDaoSQL.getAll()));
            patientsTable.refresh();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * search patients event handler
     * @param actionEvent
     * @throws BloodException
     */
    @FXML
    public void searchPatient(ActionEvent actionEvent) throws BloodException {
        Patient e=patientDaoSQL.getById(Integer.parseInt(search.getText()));
        ObservableList<Patient> emp=FXCollections.observableArrayList();
        emp.add(e);
        patientsTable.setItems(emp);
    }

    /**
     * closing window
     */
    public void akcijaZatvori(ActionEvent actionEvent) {
        Stage stage=(Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

//    public void searchPatients(ActionEvent actionEvent) {
//        patientsTable.setItems(FXCollections.observableList(patientsManager.searchByFullName(search.getText())));
//        patientsTable.refresh();
//    }
    

}
