package ba.unsa.etf.rpr.Dao;

/**
 * Factory method for singleton implementation of DAOs
 *copied form GitHub and adjusted
 * @author Azra Žunić
 */
public class DaoFactory {
    private static final BloodDao bloodDao = new BloodDaoSQLImpl();
    private static final DonorDao donorDao = new DonorDaoSQLImpl();
    private static final PatientDao patientDao = new PatientDaoSQLImpl();
    private static final HospitalDao hospitalDao = new HospitalDaoSQLImpl();

    public DaoFactory(){
    }
    public static BloodDao bloodDao(){
        return bloodDao;
    }

    public static DonorDao donorDao(){ return donorDao;
    }
    public static PatientDao patientDao(){
        return patientDao;
    }
    public static HospitalDao hospitalDao(){
        return hospitalDao;
    }
}
