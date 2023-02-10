package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/**
 * MySQL Implementation of DAO
 * @author Azra Žunić
 */
public class BloodDaoSQLImpl extends AbstractDao<Blood> implements BloodDao {
    /**
     * creating and removing instance typed as BloodDaoSQLImpl
     */
    private static  BloodDaoSQLImpl instance = null;
    public BloodDaoSQLImpl() {
        super("Blood");
    }

    public static BloodDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new BloodDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    /**
     * method same as the one on github
     * @param rs - result set from database
     * @throws BloodException
     */
    @Override
    public Blood row2object(ResultSet rs) throws BloodException {
        try {
            Blood b = new Blood();
            b.setId(rs.getInt("Blood_id"));
            b.setBloodGroup(rs.getString("BloodGroup"));
            b.setBloodBagNumber(rs.getString("BloodBagNumber"));
            b.setDonateDate( rs.getDate("DonateDate"));
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
    }

    /**
     * @author Azra Žunić
     * method that is used for searching infos by hospital id
     * @param hospital
     * @return list of attributes connected with a specified name
     * @throws BloodException
     */
    @Override
    public List<Blood> searchByHospital(Hospital hospital) throws BloodException{
        return executeQuery("SELECT * FROM Blood WHERE fk_hospital_id LIKE concat('%', ?, '%')",new Object[]{hospital.getId()});
    }

    /**
     * used to search by blood type
     * @param group
     * @throws BloodException
     */
    @Override
    public Blood searchByBloodGroup(String group) throws BloodException {
        return executeQueryUnique("SELECT * FROM Blood WHERE BloodGroup LIKE concat('%', ?, '%')",new Object[]{group});
    }
}