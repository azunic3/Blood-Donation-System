package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.exceptions.BloodException;

import java.util.List;

public class hospitalsManager {
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
}
