package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Exceptions.BloodException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;
/**
 * MySQL Implementation of DAO
 * @author Azra Žunić
 */
public class HospitalDaoSQLImpl extends AbstractDao<Hospital> implements HospitalDao {

    public HospitalDaoSQLImpl() {
        super("Hospital");
    }

    @Override
    public Hospital row2object(ResultSet rs) throws BloodException {
        try {
            Hospital h = new Hospital();
            h.setId(rs.getInt("Hospital_id"));
            h.setName(rs.getString("Name"));
            h.setAdress(rs.getString("Adress"));
            h.setContactNumber(rs.getInt("ContactNumber"));
            h.setQuantityOnHand(rs.getInt("QuantityOnHand"));
            h.setDescription(rs.getString("Description"));
            return h;
        } catch (SQLException e) {
            throw new BloodException(e.getMessage(), e);
        }
    }
    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(Hospital object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("Hospital_id", object.getId());
        item.put("Adress", object.getAdress());
        item.put("Name", object.getName());
        item.put("ContactNumber", object.getContactNumber());
        item.put("Description", object.getDescription());
        item.put("QuantityOnHand", object.getQuantityOnHand());
        return item;
    }

}

