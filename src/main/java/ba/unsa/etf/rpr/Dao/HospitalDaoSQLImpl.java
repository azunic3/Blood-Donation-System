package ba.unsa.etf.rpr.Dao;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.exceptions.BloodException;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class HospitalDaoSQLImpl extends AbstractDao<Hospital> implements HospitalDao {

    public HospitalDaoSQLImpl() {
        super("Hospital");
    }

    @Override
    public Hospital row2object(ResultSet rs) throws BloodException {
        try {
            Hospital h = new Hospital();
            h.setHospital_id(rs.getInt("id"));
            h.setName(rs.getString("Name"));
            return h;
        } catch (SQLException e) {
            throw new BloodException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Hospital object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("id", object.getId());
        item.put("Adresa", object.getAdress());
        item.put("Name", object.getName());
        return item;
    }

}

    /*private Connection connection;

    public HospitalDaoSQLImpl(){
        try {
            FileReader reader = new FileReader("src/main/resources/database.properties");
            Properties p = new Properties();
            p.load(reader);
            String url = p.getProperty("url");
            String user = p.getProperty("username");
            String password = p.getProperty("password");
            this.connection = DriverManager.getConnection(url,user, password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public Hospital getById(int id) {
        String query = "SELECT * FROM categories WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Hospital hospital = new Hospital();
                hospital.setHospital_id(rs.getInt("id"));
                hospital.setName(rs.getString("Name"));
                rs.close();
                return hospital;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }
    @Override
    public List<Hospital> getAll() {
        String query = "SELECT * FROM Patient";
        List<Hospital> Hospitals = new ArrayList<Hospital>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Hospital Hospital = new Hospital();
                Hospital.setHospital_id(rs.getInt("Patient_id"));
                Hospital.setAdress(rs.getString("Adress"));
                Hospital.setQuantityOnHand(rs.getInt("QuantityOnHand"));
                Hospital.setName(rs.getString("Name"));
                Hospital.setContactNumber(rs.getInt("ContactNumber"));
                Hospitals.add(Hospital);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return Hospitals;
    }
    @Override
    public Hospital add(Hospital item) {
        String insert = "INSERT INTO Hospital(name) VALUES(?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setHospital_id(rs.getInt(1)); //set id to return it back
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Hospital update(Hospital item) {
        String insert = "UPDATE Hospital SET Name = ? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getName());
            stmt.setObject(2, item.getHospital_id());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM Hospital WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}*/
