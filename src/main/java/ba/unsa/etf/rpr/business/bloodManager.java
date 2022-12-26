package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.exceptions.BloodException;

import java.util.List;

public class bloodManager {
    public List<Blood> getAll() throws BloodException{
        return DaoFactory.bloodDao().getAll();
    }
    public Blood add(Blood item) throws BloodException{
        return DaoFactory.bloodDao().add(item);
    }
    public Blood getById(int id) throws BloodException {
        return DaoFactory.bloodDao().getById(id);
    }
}
