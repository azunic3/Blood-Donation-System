package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Controllers.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Main class for working with JavaFX framework
 */
public class MainFX extends Application {
   public void start(Stage stage){
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/home-layout.fxml"));
            HomeController controller = new HomeController();
            fl.setController(controller);
            Parent root = fl.load();
            stage.setTitle("Blood Donation System Application");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
       /*public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginWindow.fxml"));
            primaryStage.setTitle("Register");
            primaryStage.setScene(new Scene(root, 600, 575));
            primaryStage.show();
        }*/

    public static void main(String[] args) {
        launch(args);

    }
}
