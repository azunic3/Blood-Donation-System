package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Patient;

import java.util.List;

public interface PatientDao extends Dao<Patient>{

    List<Patient> searchByFullName(String text);
}
