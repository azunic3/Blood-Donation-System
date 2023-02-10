package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Dao.HospitalDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Azra Žunić
 * version 1.2
 */
public class hospitalManagerTest {
    private Hospital hospital;
    private HospitalDaoSQLImpl hospitalDaoSQLMock;
    private List<Hospital> h;
    /**
     * This method will be called before each test method
     */
    @BeforeEach
    public void initializeObjectsWeNeed(){
        HospitalManager hospitalManager = new HospitalManager();
        hospital = new Hospital();
        hospital.setId(4);
        hospital.setName("Sanasa");
        hospital.setQuantityOnHand(25);
        hospital.setAdress("Grbavicka 15");
        hospital.setContactNumber (448552);

        hospitalDaoSQLMock = Mockito.mock(HospitalDaoSQLImpl.class);
        h = new ArrayList<>();
        h.addAll(Arrays.asList(new Hospital("Poliklinika1"), new Hospital("Poliklinika2")));
    }


}
