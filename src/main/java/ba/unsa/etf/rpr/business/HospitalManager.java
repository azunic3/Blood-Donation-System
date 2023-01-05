package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Exceptions.BloodException;

import java.util.List;

public class HospitalManager {
    public List<Hospital> getAll() throws BloodException {
        return DaoFactory.hospitalDao().getAll();
    }
    public void delete(int id) throws BloodException{
        DaoFactory.hospitalDao().delete(id);
    }
    public Hospital getById(int Hospital_id) throws BloodException{
        return DaoFactory.hospitalDao().getById(Hospital_id);
    }

    public void update(Hospital q) throws BloodException{
        DaoFactory.hospitalDao().update(q);
    }

    public Hospital add(Hospital q) throws BloodException{
        return DaoFactory.hospitalDao().add(q);
    }
    public Hospital searchByName(String name) throws BloodException {
        return DaoFactory.hospitalDao().searchByName(name);
    }
    public List<Hospital> searchByQuantityOnHand(int q) throws BloodException{
        return DaoFactory.hospitalDao().searchByQuantityOnHand(q);
    }
}
