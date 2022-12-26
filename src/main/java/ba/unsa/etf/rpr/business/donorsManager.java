package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.exceptions.BloodException;

import java.util.List;

public class donorsManager {
    public Donor searchByDonorsName(String name) throws BloodException {
        return DaoFactory.donorDao().searchByDonorsName(name);
    }
    public Donor searchById(int Id) throws BloodException{
        return DaoFactory.donorDao().getById(Id);
    }
    public List<Donor> getAll() throws BloodException{
        return DaoFactory.donorDao().getAll();
    }
    public Donor add(Donor item) throws BloodException{
        return DaoFactory.donorDao().add(item);
    }
    public Donor getById(int Donor_id) throws BloodException {
        return DaoFactory.donorDao().getById(Donor_id);
    }
}
