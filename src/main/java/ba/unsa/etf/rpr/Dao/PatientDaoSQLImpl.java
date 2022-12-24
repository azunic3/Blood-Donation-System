package ba.unsa.etf.rpr.Dao;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.exceptions.BloodException;

import java.sql.*;
import java.util.*;

public class PatientDaoSQLImpl extends AbstractDao<Patient> implements PatientDao {

    public PatientDaoSQLImpl(){
    super("Patient");
    }

    @Override
    public Patient row2object(ResultSet rs) throws BloodException {
        try{
            Patient history = new Patient();
            history.setPatient_id(rs.getInt("patient_id"));
            history.setFullName(rs.getString("Full name"));
            history.setFk_Hospital_id(DaoFactory.hospitalDao().getById(rs.getInt("BloodType")));
            return history;
        }catch (SQLException e){
            throw new BloodException(e.getMessage(), e);
        }

    }

    @Override
    public Map<String, Object> object2row(Patient object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("id", object.getPatient_id());
        item.put("", object.getId());
        item.put("Full Name", object.getFullName());
        return item;
    }


    public List<Patient> searchByFullName(String user) {
        String query = "SELECT * FROM Patient WHERE FullName = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // result set is iterator.
                return (List<Patient>) row2object(rs);
            } else {
                return null; // if there is no elements in the result set return null
            }
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        } catch (BloodException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

    /*private Connection connection;
    public PatientDaoSQLImpl(){
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
    public Patient getById(int id) {
        String query = "SELECT * FROM categories WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Patient patient = new Patient();
                patient.setPatient_id(rs.getInt("id"));
                patient.setFullName(rs.getString("FullName"));
                rs.close();
                return patient;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }
    @Override
    public void delete(int id) {
        String insert = "DELETE FROM Patient WHERE Patient_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public Patient update(Patient item) {
        String insert = "UPDATE categories SET FullName = ? WHERE Patient_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getFullName());
            stmt.setObject(2, item.getPatient_id());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Patient> getAll() {
        String query = "SELECT * FROM Patient";
        List<Patient> Patients = new ArrayList<Patient>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Patient patients = new Patient();
                patients.setPatient_id(rs.getInt("Patient_id"));
                patients.setAdress(rs.getString("Adress"));
                patients.setGender(rs.getString("Gender"));
                patients.setDateOfBirth(rs.getDate("DateOfBirth"));
                patients.setFullName(rs.getString("FullName"));
                patients.setPhoneNumber(rs.getInt("PhoneNumber"));
                Patients.add(patients);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return Patients;
    }
    @Override
    public Patient add(Patient item) {
        String insert = "INSERT INTO Patient(FullName) VALUES(?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getFullName());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setPatient_id(rs.getInt(1)); //set id to return it back
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}*/
