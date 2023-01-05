package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Exceptions.BloodException;

import java.util.Date;
import java.util.List;

/**
 * DAO interface for Blood domain bean
 * @author Azra Žunić
 * version 1.0
 */
public interface BloodDao extends Dao<Blood> {

    /**
     * methods: getAll, searchByBagNumber, searchByHospital
     * @return lists
     */
    List<Blood> getAll() throws BloodException;

    List<Blood> searchByHospital(Hospital hospital) throws BloodException;
    List<Blood> searchByBloodGroup(String group) throws BloodException;
    List<Blood> searchByDonateDate(Date date) throws BloodException;
    List<Blood> searchByBagNumber(String bagnum) throws BloodException;
    List<Blood> searchByBloodAmount(int am) throws BloodException;

}
