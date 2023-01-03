package ba.unsa.etf.rpr.Dao;

/**
 * Factory method for singleton implementation of DAOs
 * copied form GitHub and adjusted
 * @author Azra Žunić
 */
public class DaoFactory {
    private static final BloodDao bloodDao =  BloodDaoSQLImpl.getInstance();
    private static final DonorDao donorDao = DonorDaoSQLImpl.getInstance();
    private static final PatientDao patientDao = PatientDaoSQLImpl.getInstance();
    private static final HospitalDao hospitalDao = HospitalDaoSQLImpl.getInstance();

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
