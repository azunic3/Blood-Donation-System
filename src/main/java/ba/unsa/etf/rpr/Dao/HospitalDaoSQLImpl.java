package ba.unsa.etf.rpr.Dao;
import ba.unsa.etf.rpr.Domain.Hospital;
import java.sql.*;
public abstract class HospitalDaoSQLImpl implements HospitalDao{
    private Connection connection;
    public HospitalDaoSQLImpl(){
        try {
            this.connection = DriverManager.getConnection("sql7.freemysqlhosting.net", "sql7582883", "siF4VIbzWy");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
