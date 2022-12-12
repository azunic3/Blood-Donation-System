package ba.unsa.etf.rpr.Dao;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Hospital;
import java.sql.*;
import java.util.List;

public abstract class BloodDaoSQLImpl implements BloodDao{
    private Connection connection;
    public BloodDaoSQLImpl(){
        try {
            this.connection = DriverManager.getConnection("sql7.freemysqlhosting.net", "sql7582883", "siF4VIbzWy");
        }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        }


