package ba.unsa.etf.rpr;
import ba.unsa.etf.rpr.Dao.BloodDaoSQLImpl;
import ba.unsa.etf.rpr.Dao.DonorDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.exceptions.BloodException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.Date;


import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * Hello world!
 *
 */
public class App{


    public static void main( String[] args )
    {
        Blood obj=new Blood();
        try {
            ArrayList<Blood> lista = new ArrayList<>((new BloodDaoSQLImpl()).getAll());
            for(Blood clan : lista) {
                System.out.println(clan.toString());
            }

        } catch (BloodException e) {
            System.out.println("Nesto nije u redu sa BloodDaoSQLImpl");
            throw new RuntimeException(e);
        }

    }
}






