package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Dao.PatientDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.business.patientsManager;
import ba.unsa.etf.rpr.exceptions.BloodException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MedstaffController {
    private final patientsManager pManager = new patientsManager();
    private PatientDaoSQLImpl patientDaoSQL=new PatientDaoSQLImpl();
    public Button btnCancel;
    public TextField search;
    public TableView patientsTable;

    public BorderPane patientsScreen;
    public TableColumn<Patient, Integer> Idcol;
    public TableColumn<Patient,String> namecol;
    public TableColumn<Patient, String> gendercol;
    public TableColumn<Patient, Date> datecol;
    public TableColumn<Hospital,String> hospcol;


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
    @FXML
    public void searchPatient(ActionEvent actionEvent) throws BloodException {
        Patient e=patientDaoSQL.getById(Integer.parseInt(search.getText()));
        ObservableList<Patient> emp=FXCollections.observableArrayList();
        emp.add(e);
        patientsTable.setItems(emp);
    }


    public void akcijaZatvori(ActionEvent actionEvent) {
        Stage stage=(Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

//    public void searchPatients(ActionEvent actionEvent) {
//        patientsTable.setItems(FXCollections.observableList(patientsManager.searchByFullName(search.getText())));
//        patientsTable.refresh();
//    }
    

}
