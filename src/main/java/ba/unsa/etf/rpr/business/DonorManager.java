package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Exceptions.BloodException;

import java.util.List;

public class DonorManager {
    public Donor searchByDonorsName(String name) throws BloodException {
        return DaoFactory.donorDao().searchByDonorsName(name);
    }
    public Donor searchById(int Id) throws BloodException{
        return DaoFactory.donorDao().getById(Id);
    }
    public List<Donor> searchByDonated(String don) throws BloodException{
        return DaoFactory.donorDao().searchByDonated(don);
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

    public void validateDonated(String name) throws BloodException{
        if (name == null || name!="YES" || name!="NO"){
            throw new BloodException("Describing field already donated only with values YES or NO");
        }
    }
}
