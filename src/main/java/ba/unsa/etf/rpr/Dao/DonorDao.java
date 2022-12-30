package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Exceptions.BloodException;

/**
 * Dao interface for Donor domain bean
 * methods:searchById,searchByDonorsName
 */
public interface DonorDao extends Dao<Donor>{
    public Donor searchById(int Id) throws BloodException;

    public Donor searchByDonorsName(String name) throws BloodException;


}
