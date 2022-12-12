package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MainFX extends Application {
    public void start(Stage stage) throws Exception {
        FXMLLoader fl=new FXMLLoader(getClass().getResource("/fxml/home-layout.fxml"));
        Controllerlogin controller = new Controllerlogin();
        fl.setController(controller);
        Parent root = null;
        root = fl.load();
        stage.setTitle("Account information");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); //da bude taman veličine prozor
        //osim toga, padding za GridPane treba postaviti da ne bude zalijepljeno za ivice, hgap i vgap za razmak izmedju redova i kolona
        //velicina prozora je po defaultu resizable, a ne treba biti za dijaloske prozore, moramo to mijenjati kroz kod:
        //stage.setResizable(false);
        stage.setMinHeight(120); //postavimo minimalnu velicinu prozora
        stage.setMinWidth(200);
        //Hgrow na always da s promjenom velicine prozora raste i text field i button
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
