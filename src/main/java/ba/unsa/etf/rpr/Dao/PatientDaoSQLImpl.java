package ba.unsa.etf.rpr.Dao;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Domain.Blood;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public abstract class PatientDaoSQLImpl implements PatientDao {
    private Connection connection;
    public PatientDaoSQLImpl(){
        try {
            FileReader reader = new FileReader("");
            Properties p = new Properties();
            p.load(reader);
            String url = p.getProperty("sql7.freemysqlhosting.net");
            String user = p.getProperty("sql7582883");
            String password = p.getProperty("siF4VIbzWy");
            this.connection = DriverManager.getConnection("sql7.freemysqlhosting.net", "sql7582883", "siF4VIbzWy");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
