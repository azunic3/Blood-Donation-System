package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * JavaFX Controller for model class
 * uses binding
 */
public class ControllerModel {
   //form fields
    public Text fdate ;
    public Text fgroup;
    public TextField fname;
    String ime;

    BloodModel bm=new BloodModel();

    public void setIme(String t) throws BloodException {
        this.ime=t;
        Donor d=new Donor();
        d=d.searchByDonorsName(ime);
        bm.fromBlood(d.getBloodType_id_fk());
    }

    /**
     * specifies actions on Text(Field)
     * @throws BloodException
     */
    @FXML
    public void initialize() throws BloodException {
        fname.textProperty().bindBidirectional(bm.fname);
        fgroup.textProperty().bindBidirectional(bm.fgroup);
        fdate.textProperty().bindBidirectional(bm.fdate);
    }

    /**
     * class used for binding
     */
    public class BloodModel {
        public SimpleStringProperty fgroup = new SimpleStringProperty("");
        public SimpleObjectProperty<String> fdate = new SimpleObjectProperty<String>();
        public SimpleStringProperty fname = new SimpleStringProperty("");

        /**
         * from method for binding
         * @param d
         */
        public void fromBlood(Blood d) {
            this.fgroup.set(d.getBloodGroup());
            this.fdate.set(String.valueOf(d.getDonateDate()));
            this.fname.set(ime);
        }

        /**
         * to method
         * @throws ParseException
         */
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
}



