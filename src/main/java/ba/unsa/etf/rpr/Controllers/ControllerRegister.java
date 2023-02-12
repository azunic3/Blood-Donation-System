package ba.unsa.etf.rpr.Controllers;
import ba.unsa.etf.rpr.Dao.DonorDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import ba.unsa.etf.rpr.business.BloodManager;
import ba.unsa.etf.rpr.business.DonorManager;
import ba.unsa.etf.rpr.business.HospitalManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
    private DonorModel donorModel=new DonorModel();
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
              //d.setGender("F");
          else if(M.isSelected())
         d.setGender("M");
       /* String username = fieldUsername2.getText();
        String pass = fieldPassword.getText();
        Date datum = Date.valueOf(fieldDateofBirth.getValue());
        String fid= fldAlready.getText();
        int phoneNumber = Integer.parseInt(fieldPhoneNumber.getText());
        String selectedHospitalName = String.valueOf(hospitals.getValue());
        Hospital selectedHospital = new Hospital();
        for(Hospital h : hospitalManager.getAll()){
            if(h.getName().equals(selectedHospitalName)){
                selectedHospital = h; break;
            }
        }
        System.out.println(selectedHospital.getId()+" "+selectedHospital.toString());
        //d.setFk_Hospital(selectedHospital);
        Hospital h = selectedHospital;
       // String selectedbloodtype=String.valueOf(bloods.getValue());
        Blood selectedBlood= new Blood();
        for(Blood b : bloodManager.getAll()){
            if(b.getBloodGroup().equals(bloods.getValue().getBloodGroup())){
                selectedBlood = b; break;
            }
        }
        Blood b = selectedBlood;
        System.out.println(selectedBlood.getId()+" "+selectedBlood.toString());
        d.setBloodType_id_fk(selectedBlood);
        String gender = null;
        if(F.isSelected())
            gender = "F";
            //d.setGender("F");
        else if(M.isSelected())
            gender = "M";
        //d.setGender("M");
        d = new Donor(username,pass,datum,gender,phoneNumber,b,fid,h);
        try {
            donorManager.add(d);
            //DaoFactory.donorDao().add(d);
        } catch (Exception e1) {
            System.out.println("Problem with adding a new user in the database");
            throw new RuntimeException(e1);
        }*/
        //   donorModel.fromDonor(d);
           donorManager.add(d);
           refreshDonors();

         //imam ovu refrwsh u drugoj klasi cek mozel ta
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
    public class DonorModel {

        public SimpleIntegerProperty id = new SimpleIntegerProperty();
        public SimpleStringProperty name = new SimpleStringProperty("");
        public SimpleStringProperty pass = new SimpleStringProperty("");
        public SimpleObjectProperty<Date> date = new SimpleObjectProperty<Date>();
        public SimpleObjectProperty<Blood> bid = new SimpleObjectProperty();
        public SimpleObjectProperty<Hospital> hid = new SimpleObjectProperty<>();
        public SimpleStringProperty gender = new SimpleStringProperty("");
        public SimpleIntegerProperty number=new SimpleIntegerProperty();
        public SimpleStringProperty donated = new SimpleStringProperty("");


        public void fromDonor(Donor d) {
            this.id.set(d.getId());
            this.name.set(d.getFullName());
            this.pass.set(d.getPassword());
            this.gender.set(d.getGender());
            this.date.set((Date) d.getDateOfBirth());
            this.bid.set(d.getBloodType_id_fk());
            this.hid.set(d.getFk_Hospital());
            this.number.set(d.getPhoneNumber());
            this.donated.set(d.getAlreadyDonated());

        }

        public Donor toDonor() {
            Donor donor = new Donor();
            donor.setId(this.id.getValue());
           donor.setFullName(this.name.getValue());
           donor.setPassword(this.pass.getValue());
           donor.setGender(this.gender.getValue());
           donor.setDateOfBirth(this.date.getValue());
           donor.setBloodType_id_fk(this.bid.getValue());
           donor.setFk_Hospital(this.hid.getValue());
           donor.setPhoneNumber(this.number.getValue());
           donor.setAlreadyDonated(this.donated.getValue());
            return donor;
        }
    }
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