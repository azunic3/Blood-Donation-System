package ba.unsa.etf.rpr.Dao;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.exceptions.BloodException;

import java.sql.*;
import java.util.*;

public class BloodDaoSQLImpl extends AbstractDao<Blood> implements BloodDao {
    public BloodDaoSQLImpl() {
        super("Blood");
    }

    @Override
    public Blood row2object(ResultSet rs) throws BloodException {
        try {
            Blood b = new Blood();
            b.setId(rs.getInt("Blood_id"));
            b.setBloodGroup(rs.getString("BloodGroup"));
            b.setBloodBagNumber(rs.getString("BloodBagNumber"));
            b.setDonateDate(rs.getDate("DonateDate"));
            b.setBloodAmount(rs.getInt("BloodAmount"));
            b.setFk_hospital_id (DaoFactory.hospitalDao().getById(rs.getInt("fk_hospital_id")));
            return b;
        } catch (Exception e) {
            throw new BloodException(e.getMessage(), e);
        }
    }

    /**
     * @param object
     * @return
     */
    @Override
    public Map<String, Object> object2row(Blood object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("Blood_id", object.getId());
        item.put("BloodGroup", object.getBloodGroup());
        item.put("BloodBagNumber", object.getBloodBagNumber());
        item.put("DonateDate", object.getDonateDate());
        item.put("BloodAmount", object.getBloodAmount());
        item.put("fk_hospital_id", object.getFk_hospital_id().getId());
        return item;
    }

    public List<Blood> searchByBagNumber(int BloodBagNumber) throws BloodException {
        //mora sa concat jer inace nece raditi jer radi sa key chars
        String query = "SELECT * FROM Blood WHERE BloodBagNumber LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, BloodBagNumber);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Blood> bloodLista = new ArrayList<>();
            while (rs.next()) {
                bloodLista.add(row2object(rs));
            }
            return bloodLista;
        } catch (SQLException e) {
            throw new BloodException(e.getMessage(), e);
        }
    }   @Override
    public List<Blood> searchByHospital(Hospital hospital_id) throws BloodException{
        return null;
    }
}

        /*String query = "SELECT * FROM Hospital WHERE Hospital_id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, Hospital.getId());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Blood> bloodLista = new ArrayList<>();
            while (rs.next()) {
                bloodLista.add(row2object(rs));
            }
            return bloodLista;
        } catch (SQLException e) {
            throw new BloodException(e.getMessage(), e);
        }
    }
}*/


    /*private Connection connection;

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
                category.setBloodType(rs.getString("BloodType"));
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
    public Blood add(Blood item) {
        return null;
    }

    @Override
    public List<Blood> searchByBagNumber(int BloodBagNumber) {
            //mora sa concat jer inace nece raditi jer radi sa key chars
            String query = "SELECT * FROM quotes WHERE quote LIKE concat('%', ?, '%')";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, query);
                ResultSet rs = stmt.executeQuery();
                ArrayList<Blood> BloodLista = new ArrayList<>();
                while (rs.next()) {
                    Blood b = new Blood();
                    b.setBloodType(rs.getString("BloodType"));
                    b.setBloodAmount(rs.getInt("BloodAmount"));
                    b.setDonateDate(rs.getDate("DonateDate"));
                    BloodLista.add(b);
                }
                return BloodLista;
            } catch (SQLException e) {
                e.printStackTrace();
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
   /* @Override
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

    @Override
    public void delete(int id) {

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
    }*/




