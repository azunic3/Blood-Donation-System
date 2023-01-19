package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Exceptions.BloodException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * MySQL Implementation of DAO
 * @author Azra Žunić
 */
public class HospitalDaoSQLImpl extends AbstractDao<Hospital> implements HospitalDao {
    private static HospitalDaoSQLImpl instance = null;

    public HospitalDaoSQLImpl() {
        super("Hospital");
    }

    public static HospitalDaoSQLImpl getInstance() {
        if (instance == null)
            instance = new HospitalDaoSQLImpl();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null)
            instance = null;
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

    /**
     * @param name
     * @return
     * @throws BloodException
     * @author Azra Žunić
     * searching for a hospital by its name
     */
    @Override
    public Hospital searchByName(String name) throws BloodException {
        return executeQueryUnique("SELECT * FROM Hospital WHERE Name = ?", new Object[]{name});
    }

    /**
     * method used for searching by quantity of certain blood group that is available at the moment
     *
     * @param q
     * @return
     * @throws BloodException
     */
    @Override
    public List<Hospital> searchByQuantityOnHand(int q) throws BloodException {
        return executeQuery("SELECT * FROM Hospital WHERE QuantityOnHand LIKE concat('%', ?, '%')", new Object[]{q});
    }
}