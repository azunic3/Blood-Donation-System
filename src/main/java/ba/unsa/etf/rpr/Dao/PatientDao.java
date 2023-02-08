package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Exceptions.BloodException;

import java.util.List;

/**
 * Dao interface for Patient domain bean
 */
public interface PatientDao extends Dao<Patient>{
    /**
     * searching patients by full name
     * @param name
     * @throws BloodException
     */
    public Patient searchByPatientsName(String name) throws BloodException;

    /**
     * returns all patients with the specified blood type
     * @param group
     * @throws BloodException
     */
    List<Patient> searchByBloodGroup(Blood group) throws BloodException;

    /**
     * returns all patients with specified name
     * @param text
     * @throws BloodException
     */
    List<Patient> searchByFullName(String text) throws BloodException;
    List<Patient> getAll() throws BloodException;
}
