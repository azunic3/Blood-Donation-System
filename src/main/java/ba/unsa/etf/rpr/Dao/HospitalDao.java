package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Exceptions.BloodException;

import java.util.List;

/**
 * Dao interface for Hospital domain bean
 * no special classes except for those in Abstract Dao
 * @author Azra Žunić
 */
public interface HospitalDao extends Dao<Hospital>{
    public Hospital searchByName(String name) throws BloodException;
    List<Hospital> getAll() throws BloodException;

}
