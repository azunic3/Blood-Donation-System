package ba.unsa.etf.rpr.Dao;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Domain.Blood;
import java.sql.*;
public abstract class PatientDaoSQLImpl implements PatientDao {
    private Connection connection;
    public PatientDaoSQLImpl(){
        try {
            this.connection = DriverManager.getConnection("sql7.freemysqlhosting.net", "sql7582883", "siF4VIbzWy");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
