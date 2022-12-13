package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Blood;

import java.util.List;

/**
 * DAO interface for Blood domain bean
 * @author Azra Žunić
 * version 1.0
 */
public interface BloodDao extends Dao<Blood> {

    /**
     * @param searcing doses of blood by bag number
     * @return list of doses
     */

    List<Blood> searchByBagNumber(int BloodBagNumber);
    public List<Blood> getAll();
    public List<Blood> searchByText(String text);
}
