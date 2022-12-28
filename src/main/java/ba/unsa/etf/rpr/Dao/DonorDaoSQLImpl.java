package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.exceptions.BloodException;

import java.sql.*;
import java.util.*;

public class DonorDaoSQLImpl extends AbstractDao<Donor> implements DonorDao{
    public DonorDaoSQLImpl() {
        super("donors");
    }
    @Override
    public Donor row2object(ResultSet rs) throws BloodException {
        try {
            Donor d = new Donor();
            d.setId(rs.getInt("Donor_id"));
            d.setFullName(rs.getString("FullName"));
            d.setPassword(rs.getString("Password"));
            d.setAlreadyDonated(rs.getString("AlreadyDonated"));
            d.setGender(rs.getString("Gender"));
            d.setDateOfBirth(rs.getDate("DateOfBirth"));
            d.setPhoneNumber(rs.getInt("PhoneNumber"));
            d.setFk_Hospital(DaoFactory.hospitalDao().getById(rs.getInt("fk_Hospital")));
            d.setBloodType_id_fk(DaoFactory.bloodDao().getById(rs.getInt("BloodType_id_fk")));
            return d;
        } catch (SQLException e) {
            throw new BloodException(e.getMessage(), e);
        }
    }
    @Override
    public Map<String, Object> object2row(Donor object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("Donor_id", object.getId());
        row.put("FullName", object.getFullName());
        row.put("Gender", object.getGender());
        row.put("PhoneNumber", object.getPhoneNumber());
        row.put("DateOfBirth", object.getDateOfBirth());
        row.put("fk_Hospital_id", object.getFk_Hospital());
        row.put("fk_BloodType", object.getBloodType_id_fk());
        return row;
    }
    public Connection getConnection() {
        return super.getConnection();
    }
    @Override
    public Donor searchByDonorsName(String name) throws BloodException {
        return executeQueryUnique("SELECT * FROM Donor WHERE FullName = ?",new Object[]{name});
    }
    @Override
    public Donor searchById(int Id) throws BloodException{
        return getById(Id);
    }
}




