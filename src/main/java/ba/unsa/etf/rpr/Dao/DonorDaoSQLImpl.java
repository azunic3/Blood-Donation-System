package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Patient;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DonorDaoSQLImpl implements DonorDao{
    private Connection connection;
    public DonorDaoSQLImpl(){
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
    public Donor getById(int id) {
        String query = "SELECT * FROM categories WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Donor donors = new Donor();
                donors.setDonor_id(rs.getInt("id"));
                donors.setFullName(rs.getString("FullName"));
                rs.close();
                return donors;
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
        String insert = "DELETE FROM Donor WHERE Donor_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public Donor update(Donor item) {
        String insert = "UPDATE categories SET FullName = ? WHERE Patient_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getFullName());
            stmt.setObject(2, item.getDonor_id());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Donor> getAll() {
        String query = "SELECT * FROM Donor";
        List<Donor> Donors = new ArrayList<Donor>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Donor donor = new Donor();
                donor.setDonor_id(rs.getInt("Donor_id"));
                donor.setAdress(rs.getString("Adress"));
                donor.setGender(rs.getString("Gender"));
                donor.setDateOfBirth(rs.getDate("DateOfBirth"));
                donor.setFullName(rs.getString("FullName"));
                donor.setPhoneNumber(rs.getInt("PhoneNumber"));
                Donors.add(donor);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return Donors;
    }@Override
    public Donor add(Donor item) {
        String insert = "INSERT INTO Donor VALUES(?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getUsername());
            stmt.setString(2, item.getPassword());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setDonor_id(rs.getInt(1)); //set id to return it back
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }



}
