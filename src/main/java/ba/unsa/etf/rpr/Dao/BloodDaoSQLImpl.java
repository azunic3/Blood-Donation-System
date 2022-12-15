package ba.unsa.etf.rpr.Dao;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Domain.Hospital;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class BloodDaoSQLImpl implements BloodDao{
    private Connection connection;
    public BloodDaoSQLImpl(){
        try {
            FileReader reader = new FileReader("src/main/resources/database.properties");
            Properties p = new Properties();
            p.load(reader);
            String url = p.getProperty("url");
            String user = p.getProperty("username");
            String password = p.getProperty("password");

            this.connection = DriverManager.getConnection(url, user, password);
        }
            catch (Exception e){
                e.printStackTrace();
            }
    }
    @Override
    public Blood getById(int id) {
        String query = "SELECT * FROM Blood WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Blood category = new Blood();
                category.setBloodType(rs.getString("id"));
                category.setBloodBagNumber(rs.getInt("BloodBagNumber"));
                rs.close();
                return category;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }
        @Override
        public List<Blood> getAll() {
            String query = "SELECT * FROM Blood";
            List<Blood> Blood = new ArrayList<Blood>();
            try{
                PreparedStatement stmt = this.connection.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()){ // result set is iterator.
                    Blood BloodType = new Blood();
                    BloodType.setBloodType(rs.getString("BloodType"));
                    BloodType.setStatus(rs.getString("Status"));
                    BloodType.setBloodAmount(rs.getInt("BloodAmount"));
                    BloodType.setBloodBagNumber(rs.getInt("BloodBagNumber"));
                    Blood.add(BloodType);
                }
                rs.close();
            }catch (SQLException e){
                e.printStackTrace(); // poor error handling
            }
            return Blood;
        }
    @Override
    public Blood update(Blood item) {
        String insert = "UPDATE categories SET BloodAmount = ? WHERE BloodType = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getBloodAmount());
            stmt.setObject(2, item.getBloodType());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public Blood returnBloodBagNumberForBloodType(int BloodType) {
        String query = "SELECT * FROM Blood WHERE BloodType = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, BloodType);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Blood c = new Blood();
                c.setBloodType(rs.getString(1));
                c.setBloodBagNumber(rs.getInt(2));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Blood> searchByText(String text) {
        //mora sa concat jer inace nece raditi jer radi sa key chars
        String query = "SELECT * FROM Blood WHERE BloodType LIKE concat(0,'%')";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, text);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Blood> BloodTypeLista = new ArrayList<>();
            while (rs.next()) {
                Blood b = new Blood();
                b.setBloodType(rs.getString(1));
                b.setBloodBagNumber(rs.getInt(2));
                b.setDonateDate(rs.getDate(3));
                b.setStatus(rs.getString(2));
                BloodTypeLista.add(b);
            }
            return BloodTypeLista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}


