package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import ba.unsa.etf.rpr.business.BloodManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX Home Controller for the first popup window
 * contains button for all other windows
 * @author Azra Žunić
 */

public class HomeController {
    private static final String filename = "File.txt";
    ControllerRegister noviprozor2;
    BloodManager manager=new BloodManager();

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
    public void mniSave(ActionEvent actionEvent) throws IOException{
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<Blood> iterator = null;
            try {
                iterator = manager.getAll().iterator();
            } catch (BloodException e) {
                e.printStackTrace();
            }
            while (true) {
                assert iterator != null;
                if (!iterator.hasNext()) break;
                Blood temp = iterator.next();
                bw.write(String.format("%s\t%s\t%s\t%s\t%s\t%s", temp.getBloodGroup(), temp.getId(), temp.getBloodAmount(), temp.getDonateDate(), temp.getBloodBagNumber(), temp.getFk_hospital_id()));
                bw.newLine();
            }

        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

    /**
     * menu option that opens file imported in project
     * @param actionEvent
     */
    public void akcijaOtvori(ActionEvent actionEvent) {
        try {
            File file = new File("File.txt");
            if (!Desktop.isDesktopSupported()) {

                System.out.println("Not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists())
                desktop.open(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param title, file, controller
     */
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
}



