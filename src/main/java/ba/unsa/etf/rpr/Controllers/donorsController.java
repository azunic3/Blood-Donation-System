package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DonorDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX Controller for showing a TableView of all donors names from db table
 */
public class donorsController {
    public Button btnRegister;
    public Button btnCancel;
    private DonorDaoSQLImpl donorDaoSQL=new DonorDaoSQLImpl();
    public TableView DonorTable;
    public TableColumn<Donor, Integer> IDcol;
    public TableColumn<Donor,String> NAMEcol;
    public TableColumn<Donor, Integer> typecol;

    @FXML
    public void initialize() throws BloodException {
        IDcol.setCellValueFactory(new PropertyValueFactory<Donor,Integer>("Id"));
        NAMEcol.setCellValueFactory(new PropertyValueFactory<Donor,String>("FullName"));
        typecol.setCellValueFactory(new PropertyValueFactory<Donor,Integer>("BloodType_id_fk"));
        //NEDOSTAJE ZA BLOODTYPE, za sad je samo id
        try {
            DonorTable.setItems(FXCollections.observableList(donorDaoSQL.getAll()));
            DonorTable.refresh();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void akcijaZatvori(ActionEvent actionEvent) {
        Stage stage=(Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    public void otvoriRegister(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/registerClick.fxml"));
            Parent root = fl.load();
            ControllerRegister treci = fl.getController();
            stage.setTitle("Registration");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
