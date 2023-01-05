package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Exceptions.BloodException;

import java.util.List;

/**
 * Dao interface for Donor domain bean
 * methods:searchById,searchByDonorsName
 */
public interface DonorDao extends Dao<Donor>{
    public Donor searchById(int Id) throws BloodException;

    public Donor searchByDonorsName(String name) throws BloodException;
    List<Donor> searchByDonated(String don) throws BloodException;

}
