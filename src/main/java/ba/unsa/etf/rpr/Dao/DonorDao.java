package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Exceptions.BloodException;

import java.util.List;

/**
 * Dao interface for Donor domain bean
 * methods:searchById,searchByDonorsName,searchByName
 */
public interface DonorDao extends Dao<Donor>{
    /**
     * searching by ID
     * @param Id
     * @return
     * @throws BloodException
     */
    public Donor searchById(int Id) throws BloodException;

    /**
     * returns donor's name
     * @param name
     * @return
     * @throws BloodException
     */
    public Donor searchByDonorsName(String name) throws BloodException;

    /**
     * returns list of values when searching by name of the donor
     * @param text
     * @return
     * @throws BloodException
     */
    List<Donor> searchByName(String text) throws BloodException;
    List<Donor> getAll() throws BloodException;
}
