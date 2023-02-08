package ba.unsa.etf.rpr.Dao;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 * @author Azra Žunić
 */
public class PatientDaoSQLImpl extends AbstractDao<Patient> implements PatientDao {

    private static  PatientDaoSQLImpl instance = null;
    public PatientDaoSQLImpl(){
        super("Patient");
    }

    public static PatientDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new PatientDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }
    @Override
    public Patient row2object(ResultSet rs) throws BloodException {
        try{
            Patient pat = new Patient();
            pat.setId(rs.getInt("Patient_id"));
            pat.setFull_Name(rs.getString("Full_Name"));
            pat.setAdress(rs.getString("Adress"));
            pat.setGender(rs.getString("Gender"));
            pat.setDateOfBirth(rs.getDate("DateOfBirth").toLocalDate());
            pat.setPhoneNumber(rs.getInt("PhoneNumber"));
            pat.setFk_Hospital_id(DaoFactory.hospitalDao().getById(rs.getInt("fk_Hospital_id")));
            pat.setFk_BloodType(DaoFactory.bloodDao().getById(rs.getInt("fk_BloodType")));
            return pat;
        }catch (SQLException e){
            throw new BloodException(e.getMessage(), e);
        }
    }
    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(Patient object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("Patient_id", object.getId());
        item.put("Full_Name", object.getFull_Name());
        item.put("Gender", object.getGender());
        item.put("PhoneNumber", object.getPhoneNumber());
        item.put("DateOfBirth", object.getDateOfBirth());
        item.put("Adress", object.getAdress());
        item.put("fk_Hospital_id", object.getFk_Hospital_id().getId());
        item.put("fk_BloodType", object.getFk_BloodType().getId());
        return item;
    }

    /**
     * method is used for searching patients by their name
     * @param name
     * @return patients whose name is sent as parameter
     * @throws BloodException
     */
    @Override
    public Patient searchByPatientsName(String name) throws BloodException {
        return executeQueryUnique("SELECT * FROM Patient WHERE Full_Name = ?", new Object[]{name});
    }

    /**
     * checking blood type for patients
     * @param group
     * @throws BloodException
     */
    @Override
    public List<Patient> searchByBloodGroup(Blood group) throws BloodException{
        return executeQuery("SELECT * FROM Patient WHERE fk_BloodType LIKE concat('%', ?, '%')",new Object[]{group.getId()});
    }

    /**
     * searching patients by name
     * @param text
     * @return list
     * @throws BloodException
     */
    @Override
    public List<Patient> searchByFullName(String text) throws BloodException{
        return executeQuery("SELECT * FROM Patient WHERE Full_Name LIKE concat('%', ?, '%')",new Object[]{text});
    }
}
