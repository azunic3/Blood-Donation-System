package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.BloodDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import ba.unsa.etf.rpr.business.BloodManager;
import ba.unsa.etf.rpr.business.DonorManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class ControllerModel {

    public Text fdate ;
    public Text fgroup;
    public TextField fname;
    String ime;
    private final DonorManager donorManager = new DonorManager();

    BloodManager bmanager = new BloodManager();
BloodModel bm=new BloodModel();
    public TextField search;
    private BloodDaoSQLImpl bloodDaoSQL = new BloodDaoSQLImpl();


    public void setIme(String t) throws BloodException {
        this.ime=t;
        Donor d=new Donor();
        d=d.searchByDonorsName(ime);
        bm.fromBlood(d.getBloodType_id_fk());
    }

    @FXML
    public void initialize() throws BloodException {

        fname.textProperty().bindBidirectional(bm.fname);
fgroup.textProperty().bindBidirectional(bm.fgroup);
fdate.textProperty().bindBidirectional(bm.fdate);
    }
    public class BloodModel {
        public SimpleStringProperty fgroup = new SimpleStringProperty("");
        public SimpleObjectProperty<String> fdate = new SimpleObjectProperty<String>();
        public SimpleStringProperty fname = new SimpleStringProperty("");


        public void fromBlood(Blood d) {

            this.fgroup.set(d.getBloodGroup());
            this.fdate.set(String.valueOf(d.getDonateDate()));
            this.fname.set(ime);
        }
        public Blood toBlood() throws ParseException {
            Blood d = new Blood();
            d.setBloodGroup(this.fgroup.getName());
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            d.setDonateDate(format.parse(this.fdate.getName()));
            Donor d1=new Donor();
            d1.setFullName(this.fname.getName());
            return d;
        }
    }
    public void searchD (ActionEvent actionEvent) throws BloodException {
        Blood p = bloodDaoSQL.getById(Integer.parseInt(search.getText()));
        ObservableList<Blood> pat = FXCollections.observableArrayList();
        pat.add(p);
    }
}



