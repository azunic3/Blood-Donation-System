package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Exceptions.BloodException;

import java.util.List;

/**
 * Dao interface for Donor domain bean
 * methods:getAll, searchByDonorsName, searchByName
 */
public interface DonorDao extends Dao<Donor>{

    /**
     * returns donor's name
     * @param name
     * @throws BloodException
     */
    Donor searchByDonorsName(String name) throws BloodException;

    /**
     * @return list of values when searching by name of the donor
     * @param text
     * @throws BloodException
     */
    List<Donor> searchByName(String text) throws BloodException;
    List<Donor> getAll() throws BloodException;
    Donor searchByDName(String name) throws BloodException;
}
