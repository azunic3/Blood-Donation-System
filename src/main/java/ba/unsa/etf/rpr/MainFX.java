package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/home-layout.fxml"));
            //nije do kontrolera, nije do putanje, stvar je u tome sto ipak ne prepoznaje .fxml koji treba da load-a iz nekog razloga
            //pokusat drugi napravit i vidjet dal ce uspjet
            //malo sam izguglao pise da je problem u putanji kada se javi exception ovaj tipa load
            //sad cu ja pokusat malo istrazit pa cu ti reci sta bi jos moglo biti hvalaa
            //javim se za 5 min
            Contollerlogin controller = new Contollerlogin();
            fl.setController(controller);
            Parent root = fl.load();
            //samo da ja kod sebe vidim layout.. minutica
            stage.setTitle("Account information");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE)); //da bude taman veličine prozor
            //osim toga, padding za GridPane treba postaviti da ne bude zalijepljeno za ivice, hgap i vgap za razmak izmedju redova i kolona
            //velicina prozora je po defaultu resizable, a ne treba biti za dijaloske prozore, moramo to mijenjati kroz kod:
            //stage.setResizable(false);
            stage.setMinHeight(120); //postavimo minimalnu velicinu prozora
            stage.setMinWidth(200);
            //Hgrow na always da s promjenom velicine prozora raste i text field i button
            //.css za boju button-a
            stage.show();
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
