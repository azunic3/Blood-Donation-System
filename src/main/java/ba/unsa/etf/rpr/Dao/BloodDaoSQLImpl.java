package ba.unsa.etf.rpr.Dao;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Hospital;

import java.io.FileReader;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public abstract class BloodDaoSQLImpl implements BloodDao{
    private Connection connection;
    public BloodDaoSQLImpl(){
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


