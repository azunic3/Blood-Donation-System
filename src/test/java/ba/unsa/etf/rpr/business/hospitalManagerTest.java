package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Dao.HospitalDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @author Azra Žunić
 * version 1.0
 */
public class hospitalManagerTest {
    private HospitalManager hospitalManager;
    private Hospital hospital;
    private HospitalDaoSQLImpl hospitalDaoSQLMock;
    private List<Hospital> h;
    /**
     * This method will be called before each test method
     */
    @BeforeEach
    public void initializeObjectsWeNeed(){
        hospitalManager = Mockito.mock(HospitalManager.class);
        hospital = new Hospital();
        hospital.setId(50);
        hospital.setName("Sanasa");
        hospital.setQuantityOnHand(25);
        hospital.setAdress("Grbavicka 15");
        hospital.setContactNumber (448552);

        hospitalDaoSQLMock = Mockito.mock(HospitalDaoSQLImpl.class);
        h = new ArrayList<>();
        h.addAll(Arrays.asList(new Hospital("Poliklinika1"), new Hospital("Poliklinika2")));
    }

}
