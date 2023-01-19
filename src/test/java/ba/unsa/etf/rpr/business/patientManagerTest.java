package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.Dao.BloodDaoSQLImpl;
import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Dao.DonorDaoSQLImpl;
import ba.unsa.etf.rpr.Dao.PatientDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
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
public class patientManagerTest {
    private PatientManager patientManager;
    private Patient patient;
    private PatientDaoSQLImpl patientDaoSQLMock;
    private List<Patient> p;
    /**
     * This method will be called before each test method
     */
    @BeforeEach
    public void initializeObjectsWeNeed(){
        patientManager = Mockito.mock(PatientManager.class);
        patient = new Patient();
        patient.setId(50);
        patient.setFull_Name("Emina Gagula");
        patient.setGender("F");
        patient.setPhoneNumber(0603577421);

        patientDaoSQLMock = Mockito.mock(PatientDaoSQLImpl.class);
        p = new ArrayList<>();
        p.addAll(Arrays.asList(new Patient("Novi Pacijent"), new Patient("Drugi Pacijent")));
    }
    /**
     * In this method we will test validatePatientsName(String name) for correct and incorrect passed parameters
     */
    @Test
    void validatePatientsName() throws BloodException {
        String correctName = "Emina Gagula";
        try {
            Mockito.doCallRealMethod().when(patientManager).validatePatientsName(correctName);
        } catch (BloodException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectNameShort = "Em";
        Mockito.doCallRealMethod().when(patientManager).validatePatientsName(incorrectNameShort);
        BloodException bloodException1 = Assertions.assertThrows(BloodException.class, () -> {
            patientManager.validatePatientsName(incorrectNameShort);}, "Name must contain between 3 and 30 characters");
        Assertions.assertEquals("Name must contain between 3 and 30 characters", bloodException1.getMessage());

        String incorrectNameLong = RandomStringUtils.randomAlphabetic(50);
        Mockito.doCallRealMethod().when(patientManager).validatePatientsName(incorrectNameLong);
        BloodException bloodException2 = Assertions.assertThrows(BloodException.class, () -> {
            patientManager.validatePatientsName(incorrectNameLong);}, "Name must contain between 3 and 30 characters");
        Assertions.assertEquals("Name must contain between 3 and 30 characters", bloodException2.getMessage());
    }
    /**
     * Testing if we can add a new patient
     */
    @Test
    void addNewPatient() throws BloodException {
        Patient newType = new Patient("P");
        patientManager.add(newType);

        Assertions.assertTrue(true);
        Mockito.verify(patientManager).add(newType);
    }


}
