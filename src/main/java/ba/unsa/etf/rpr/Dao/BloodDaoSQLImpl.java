package ba.unsa.etf.rpr.Dao;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Exceptions.BloodException;

import java.sql.*;
import java.util.*;

/**
 * MySQL Implementation of DAO
 * @author Azra Žunić
 */
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
     * @param object - object to be mapped
     * @return map representation of object
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

    /**
     * searching method
     * @param BloodBagNumber - unique number
     * @return BloodBagNumber
     * @throws BloodException
     */
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





