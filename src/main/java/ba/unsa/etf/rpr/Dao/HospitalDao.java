package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Exceptions.BloodException;

import java.util.List;

/**
 * Dao interface for Hospital domain bean
 * classes: searchByName, getAll
 * @author Azra Žunić
 */
public interface HospitalDao extends Dao<Hospital>{
    Hospital searchByName(String name) throws BloodException;
    List<Hospital> getAll() throws BloodException;

}
