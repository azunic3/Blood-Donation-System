package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.Dao.BloodDaoSQLImpl;
import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Dao.HospitalDaoSQLImpl;
import ba.unsa.etf.rpr.Dao.PatientDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Date;
import java.time.LocalDate;
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

    @Test
    void validateHospitalName() throws BloodException {
        String correctName = "Sunce";
        try {
            Mockito.doCallRealMethod().when(hospitalManager).validateHospitalName(correctName);
        } catch (BloodException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectNameShort = "Sun";
        Mockito.doCallRealMethod().when(hospitalManager).validateHospitalName(incorrectNameShort);
        BloodException bloodException1 = Assertions.assertThrows(BloodException.class, () -> {
            hospitalManager.validateHospitalName(incorrectNameShort);}, "Name must contain between 4 and 20 characters");
        Assertions.assertEquals("Name must contain between 4 and 20 characters", bloodException1.getMessage());

        String incorrectNameLong = RandomStringUtils.randomAlphabetic(50);
        Mockito.doCallRealMethod().when(hospitalManager).validateHospitalName(incorrectNameLong);
        BloodException bloodException2 = Assertions.assertThrows(BloodException.class, () -> {
            hospitalManager.validateHospitalName(incorrectNameLong);}, "Name must contain between 4 and 20 characters");
        Assertions.assertEquals("Name must contain between 4 and 20 characters", bloodException2.getMessage());
    }

}
