package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Exceptions.BloodException;

import java.util.List;

public class PatientManager {

//    public static List<Object> searchByFullName(String text) {
//        return searchByFullName(text);
//    }
    public Patient searchByPatientsName(String name) throws BloodException {
        return DaoFactory.patientDao().searchByPatientsName(name);
    }
    public List<Patient> getAll() throws BloodException {
            return DaoFactory.patientDao().getAll();
        }
    public static void delete(int id) throws BloodException{
        DaoFactory.patientDao().delete(id);
    }
        public Patient getById(int Patient_id) throws BloodException{
            return DaoFactory.patientDao().getById(Patient_id);
        }

        public void update(Patient q) throws BloodException{
            DaoFactory.patientDao().update(q);
        }

        public Patient add(Patient q) throws BloodException{
            return DaoFactory.patientDao().add(q);
        }
    public List<Patient> searchByBloodGroup(Blood group) throws BloodException{
        return DaoFactory.patientDao().searchByBloodGroup(group);
    }

}
