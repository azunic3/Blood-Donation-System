package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Domain.Blood;
import java.sql.*;
public abstract class DonorDaoSQLImpl implements DonorDao{
    private Connection connection;
    public DonorDaoSQLImpl(){
        try {
            this.connection = DriverManager.getConnection("sql7.freemysqlhosting.net", "sql7582883", "siF4VIbzWy");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
