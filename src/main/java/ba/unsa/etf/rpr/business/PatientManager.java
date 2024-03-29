package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import java.util.List;
/**
 * Business Logic Layer for management of Patients
 * @author Azra Žunić
 */
public class PatientManager {


    /**
     * simple validation
     * @param correctName
     * @return boolean value true or false depending on whether the patient exists in db
     */
    public static boolean validatePat(String correctName) {
        try {
           Patient p= DaoFactory.patientDao().searchByPatientsName(correctName);
            if(p.getFull_Name()==correctName)
                return true;
        } catch (BloodException e) {
            return false;
        }
        return true;
    }

    /**
     * validation method used for tests
     * @param gen
     * @throws BloodException
     */
    public static void validateGender(String gen) throws BloodException{
        if(gen==null || gen.length()>1 || gen!="F" || gen!="M")
            throw new BloodException("Incorrect gender option!");
    }

    /**
     * add method
     * @param b
     * @return new patient
     * @throws BloodException
     */
    public Patient add(Patient b) throws BloodException {
        if (b.getId() != 0){
            throw new BloodException("Can't add patient with ID. ID is autogenerated");
        }
        try{
            return DaoFactory.patientDao().add(b);
        }catch (BloodException e){
            if (e.getMessage().contains("UQ_Full_Name")){
                throw new BloodException("Patient with the same name already exists");
            }
            throw e;
        }
    }
    /**
     * ordinary searching methods
     * @param text
     * @return information about patients whose name is sent
     * @throws BloodException
     */
    public  List<Patient> searchPatients(String text) throws BloodException {
    return (DaoFactory.patientDao().searchByFullName(text));
    }
    public  Patient searchPatientsByName(String text) throws BloodException {
        return (DaoFactory.patientDao().searchByPatientsName(text));
    }
/**
 * basic methods defined in Dao Factory and implemented in PatientDaoSqlImpl
 */

    /**
     * fetching all information about patients from db
     * @throws BloodException
     */
    public List<Patient> getAll() throws BloodException {
            return DaoFactory.patientDao().getAll();
        }

    /**
     * method used for deleting patients
     * @param id
     * @throws BloodException
     */
    public static void delete(int id) throws BloodException{
        DaoFactory.patientDao().delete(id);
    }

    /**
     * method used for retrieving patients by their id
     * @param Patient_id
     * @throws BloodException
     */
        public Patient getById(int Patient_id) throws BloodException{
            return DaoFactory.patientDao().getById(Patient_id);
        }

    /**
     * updating information about patients
     * @param q
     * @throws BloodException
     */
        public void update(Patient q) throws BloodException{
            DaoFactory.patientDao().update(q);
        }

    /**
     * searching patients by blood group
     * it is possible that this method is not going to be used
     * @param group
     * @throws BloodException
     */
    public List<Patient> searchByBloodGroup(Blood group) throws BloodException{
        return DaoFactory.patientDao().searchByBloodGroup(group);
    }

}
