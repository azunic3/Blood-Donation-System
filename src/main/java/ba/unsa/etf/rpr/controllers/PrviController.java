package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class PrviController {
    @FXML
    public void initialize() {

    }
    public void btnDonorakcija(ActionEvent actionEvent) throws IOException {
        try {
            Stage stage = new Stage();
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/donorClick.fxml"));
            Parent root = fl.load();
            ControllerDonor prvi = fl.getController();
            stage.setTitle("Blood donation");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void btnMedicalStaffakcija(ActionEvent actionEvent) throws IOException{
       try {
            Stage stage = new Stage();
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/medstaffClick.fxml"));
            Parent root = fl.load();
            MedstaffController drugi = fl.getController();
            stage.setTitle("Blood donation");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}



