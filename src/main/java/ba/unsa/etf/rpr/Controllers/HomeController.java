package ba.unsa.etf.rpr.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX Home Controller for the first popup window
 * contains button for all other windows
 * @author Azra Žunić
 */

public class HomeController {
    //attributes
    public TextArea textArea;
    public Label StatusBarLabel;
    public MenuItem mniClose;
    public Button tbClose;
    ControllerRegister noviprozor2;
    @FXML
    public void initialize() {

    }
    public ControllerRegister getNoviprozor2() {
        return noviprozor2;
    }

    public void setNoviprozor2(ControllerRegister noviprozor2) {
        this.noviprozor2 = noviprozor2;
    }

    /**
     * opens sign in/register window
     * @param actionEvent
     * @throws IOException
     */
    public void btnDonorakcija(ActionEvent actionEvent) throws IOException {
        try {
            Stage stage = new Stage();
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/loginWindow.fxml"));
            Parent root = fl.load();
            ControllerLogin prvi = fl.getController();
            stage.setTitle("Blood donation");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * opens window that contains list of information about patients
     * @param actionEvent
     * @throws IOException
     */
    public void btnMedicalStaffakcija(ActionEvent actionEvent) throws IOException{
       try {
            Stage stage = new Stage();
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/medstaffClick.fxml"));
            Parent root = fl.load();
            MedstaffController drugi = fl.getController();
            stage.setTitle("Medical staff help");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
           stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * opens simple window that shows donors(only names because of privacy etc.)
     * @param actionEvent
     * @throws IOException
     */
    public void btnheroesakcija (ActionEvent actionEvent) throws IOException{
        try {
            Stage stage = new Stage();
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/donors.fxml"));
            Parent root = fl.load();
            donorsController treci = fl.getController();
            stage.setTitle("Registrated donors");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * closing window from MenuBar
     * @param actionEvent
     */
    public void closeApp(ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }

    /**
     * opening simple window from MenuBar that shows information about app
     * @param actionEvent
     */
    public void openAbout(ActionEvent actionEvent){
        openDialog("About", "/fxml/about.fxml", null);
    }

    /**
     * opening text file from MenuBar
     * @param actionEvent
     * @throws IOException
     */
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

    private void openDialog(String title, String file, Object controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene((Parent) loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void openEditDonor(ActionEvent event){
        openDialog("Manage Patients", "/fxml/medstaffClick.fxml", new MedstaffController());
    }

    public void openEditMedstaff(ActionEvent event){
        openDialog("Manage Donors", "/fxml/loginWindow.fxml", new ControllerLogin());
    }
    public void openEditHeroes(ActionEvent event){
        openDialog("Manage Heroes", "/fxml/donors.fxml", new donorsController());
    }
}



