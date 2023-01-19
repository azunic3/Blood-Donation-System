package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.BloodDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Blood;
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

import java.time.LocalDate;

public class ControllerModel {
    public TableColumn<Blood, Integer> IDcol;
    public TableColumn<Blood, String> bloodCol;
    public TableColumn<Blood, LocalDate> DateCol;

    public DatePicker fdate;
    public TextField fgroup;
    private final DonorManager donorManager = new DonorManager();

    BloodManager bmanager=new BloodManager();
   public TableView bloodTable ;
    public TextField search;
    private BloodDaoSQLImpl bloodDaoSQL = new BloodDaoSQLImpl();

    @FXML
    public void initialize() throws BloodException {
       IDcol.setCellValueFactory(new PropertyValueFactory<Blood, Integer>("Id"));
        bloodCol.setCellValueFactory(new PropertyValueFactory<Blood, String>("BloodGroup"));
        DateCol.setCellValueFactory(new PropertyValueFactory<Blood, LocalDate>("DonateDate"));
            bloodTable.getSelectionModel().selectedItemProperty().addListener((obs, oldBlood, newBlood) -> {
            BloodModel bloodModel = new BloodModel();
            bloodModel.fromBlood((Blood) newBlood);
        fdate.valueProperty().bindBidirectional(bloodModel.fdate);
        fgroup.textProperty().bindBidirectional(bloodModel.fgroup);
        });
           try{
               bloodTable.setItems(FXCollections.observableList(bloodDaoSQL.getAll()));
               bloodTable.refresh();
           }catch(Exception e){
               e.printStackTrace();
           }
    }
    public ControllerModel(BloodDaoSQLImpl bloodDaoSQL) {
        this.bloodDaoSQL=bloodDaoSQL;
    }
    public ControllerModel(){}
    private void refresh() throws BloodException{
        try{
            bloodTable.setItems(FXCollections.observableList(bmanager.getAll()));
            bloodTable.refresh();
        }catch (BloodException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public class BloodModel {
    public SimpleStringProperty fgroup = new SimpleStringProperty("");
    public SimpleObjectProperty<LocalDate> fdate = new SimpleObjectProperty<LocalDate>();

    public void fromBlood(Blood d) {
        this.fgroup.set(d.getBloodGroup());
        this.fdate.set(d.getDonateDate());
    }
    public Blood toBlood() {
        Blood d = new Blood();
        d.setBloodGroup(this.fgroup.getName());
        d.setDonateDate(this.fdate.getValue());
        return d;
    }
}
public void searchD (ActionEvent actionEvent) throws BloodException {
        Blood p = bloodDaoSQL.getById(Integer.parseInt(search.getText()));
        ObservableList<Blood> pat = FXCollections.observableArrayList();
        pat.add(p);
        bloodTable.setItems(pat);
    }
    }



