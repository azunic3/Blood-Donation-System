package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Exceptions.BloodException;

import java.util.List;

/**
 * Dao interface for Patient domain bean
 * methods:searchByPatientsName,searchById,searchByFullName
 */
public interface PatientDao extends Dao<Patient>{

    public Patient searchById(int Id) throws BloodException;
    public Patient searchByPatientsName(String name) throws BloodException;

    List<Patient> searchByBloodGroup(Blood group) throws BloodException;
}
