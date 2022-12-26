package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.exceptions.BloodException;

import java.util.Date;
import java.util.List;

public class patientsManager {

    public static List<Object> searchByFullName(String text) {
        return searchByFullName(text);
    }
    public List<Patient> getAll() throws BloodException {
            return DaoFactory.patientDao().getAll();
        }
        public void delete(int id) throws BloodException{
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

}
