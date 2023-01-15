package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Domain.Patient;
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
public class DonorDaoSQLImpl extends AbstractDao<Donor> implements DonorDao{
    private static  DonorDaoSQLImpl instance = null;
    public DonorDaoSQLImpl() {
        super("Donor");
    }

    public static DonorDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new DonorDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
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
        }
        catch (SQLException e) {
            throw new BloodException(e.getMessage(), e);
        }
    }
    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(Donor object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("Donor_id", object.getId());
        row.put("FullName", object.getFullName());
        row.put("Gender", object.getGender());
        row.put("PhoneNumber", object.getPhoneNumber());
        row.put("DateOfBirth", object.getDateOfBirth());
        row.put("Fk_Hospital", object.getFk_Hospital());
        row.put("BloodType_id_fk", object.getBloodType_id_fk());
        return row;
    }
    /**
     * @author Azra Žunić
     * method that searches donors by full name
     * @param name
     * @return name
     * @throws BloodException
     */
    @Override
    public Donor searchByDonorsName(String name) throws BloodException {
        return executeQueryUnique("SELECT * FROM Donor WHERE FullName = ?", new Object[]{name});
    }
    /**
     * searching donors by donor_id
     * @param Id
     * @return donors id
     * @throws BloodException
     */
    @Override
    public Donor searchById(int Id) throws BloodException{
        return executeQueryUnique("SELECT * FROM Donor WHERE Donor_id = ?", new Object[]{Id});
    }

    /**
     * checking if the donor has donated blood before
     * @param don
     * @return date
     * @throws BloodException
     */
    @Override
    public List<Donor> searchByDonated(String don) throws BloodException {
        return executeQuery("SELECT * FROM Donor WHERE AlreadyDonated LIKE concat('%', ?, '%')",new Object[]{don});
    }

    /**
     * searching donors by blood id
     * @param Id
     * @return ID
     * @throws BloodException
     */
    @Override
    public Donor searchByBloodId(int Id) throws BloodException{
        return executeQueryUnique("SELECT * FROM Donor WHERE Donor_id = ?", new Object[]{Id});
    }

    /**
     * returns list searched by name of the donor
     * @param text
     * @return list
     * @throws BloodException
     */
    @Override
    public List<Donor> searchByName(String text) throws BloodException{
        return executeQuery("SELECT * FROM Donor WHERE FullName LIKE concat('%', ?, '%')",new Object[]{text});
    }

}