package ba.unsa.etf.rpr.controllers;
import ba.unsa.etf.rpr.Dao.BloodDaoSQLImpl;
import ba.unsa.etf.rpr.Dao.DonorDaoSQLImpl;
import ba.unsa.etf.rpr.Dao.HospitalDaoSQLImpl;
import ba.unsa.etf.rpr.Dao.PatientDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.exceptions.BloodException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {
    public ListView<Blood> bloodListView;
    public TableView<Donor> donorTableView;
    public TableColumn<Blood, String> colBloodType;
    public TableColumn<Blood, String> colBloodBagNumber;
    public TableColumn<Blood, String> colDonateDate;
    private BloodDaoSQLImpl bloodDaoSQL;
    private DonorDaoSQLImpl donorDaoSQL;
    private PatientDaoSQLImpl patientDaoSQL;
    private HospitalDaoSQLImpl hospitalDaoSQL;
    private ObservableList<Donor> donors;
    private ObservableList<Patient> patients;
    private ObservableList<Hospital> hospitals;
    private ObservableList<Blood> bloodtypes;

    public HomeController() {
        try {
            bloodDaoSQL = new BloodDaoSQLImpl();
            donorDaoSQL = new DonorDaoSQLImpl();
            patientDaoSQL = new PatientDaoSQLImpl();
            hospitalDaoSQL = new HospitalDaoSQLImpl();
            donors = FXCollections.observableArrayList(donorDaoSQL.getAll());
            patients = FXCollections.observableArrayList(patientDaoSQL.getAll());
            //donors = FXCollections.observableArrayList();
            //patients = FXCollections.observableArrayList();
            hospitals = FXCollections.observableArrayList();
            bloodtypes = FXCollections.observableArrayList();
        } catch (BloodException e) {
            System.out.println("Something is not right with table of categories");
            throw new RuntimeException(e);
        }
    }
}
