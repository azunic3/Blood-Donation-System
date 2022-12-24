package ba.unsa.etf.rpr.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class PrviController {
    public TextArea textArea;
    public Label StatusBarLabel;
    public MenuItem mniClose;
    public Button tbClose;
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
            stage.setResizable(false);
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
            stage.setTitle("Medical staff help");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void akcijaKraj(ActionEvent actionEvent) throws IOException{
        System.exit(0);
    }
    //ne radi mi pitaj
    public void akcijaOtvori(ActionEvent actionEvent) throws IOException{
        FileChooser izbornik = new FileChooser();
        izbornik.setTitle("Choose file: ");
        izbornik.getExtensionFilters().add(new FileChooser.ExtensionFilter("Txt file", "*.txt"));
        File izabrani=izbornik.showOpenDialog(textArea.getScene().getWindow());
        if(izabrani==null) return;
        try {
            String tekst = new String(Files.readAllBytes(izabrani.toPath()));
            textArea.setText(tekst);
        }
        catch(IOException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("There was an error while reading file!");
            alert.setContentText(e.getMessage());
            alert.setTitle("Cannot open file");
            alert.show();
            StatusBarLabel.setText("File executed");
        }
    }
    public void akcijaZatvori(ActionEvent actionEvent) {
        Stage stage = (Stage) tbClose.getScene().getWindow();
        stage.close();
    }

}



