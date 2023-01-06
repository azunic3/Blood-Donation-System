package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class ControllerModel {
    //just blood group and date of last donation
    public SimpleStringProperty fgroup = new SimpleStringProperty("");
    public SimpleObjectProperty<LocalDate> fdate = new SimpleObjectProperty<LocalDate>();

    public void fromBlood(Blood d) {
        this.fgroup.set(d.getBloodGroup());
        //opet datum sranje
        this.fdate.set(d.getDonateDate());
    }
    public Blood toBlood(){
        Blood d=new Blood();
        d.setBloodGroup(this.fgroup.getName());
        d.setDonateDate(this.fdate.getValue());
        return d;
    }
    }

