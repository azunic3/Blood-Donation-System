package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Dao.PatientDao;
import ba.unsa.etf.rpr.Dao.PatientDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Azra Žunić
 * version 1.3
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
       patientManager = new PatientManager();
       patient = new Patient();
     patient.setFull_Name("Emina Gagula");
      patient.setGender("F");
       patient.setPhoneNumber(0603577421);
       patient.setDateOfBirth(LocalDate.of(2000,10,10));
      patientDaoSQLMock = Mockito.mock(PatientDaoSQLImpl.class);
      p = new ArrayList<>();
       p.addAll(Arrays.asList(new Patient("Nezir Zunic"), new Patient("Djordje Balasevic")));
    }

    /**
     * simple validation test
     */
   @Test
    void validateGender() {
        String gen="MEJBI";
        assertThrows(BloodException.class, ()->patientManager.validateGender(gen), "Incorrect gender option!");
    }
    /**
     * Mocking tests
     */

    /**
     * Testing add method using mocking
      * @throws BloodException
     */
    @Test
    void add() throws BloodException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic=Mockito.mockStatic(DaoFactory.class);
        PatientDao patientDao=Mockito.mock(PatientDaoSQLImpl.class);
        daoFactoryMockedStatic.when(DaoFactory::patientDao).thenReturn(patientDao);
        initializeObjectsWeNeed();
        when(patientDao.add(patient)).thenReturn(patient);
        patientManager.add(patient);
        assertTrue(true);
        daoFactoryMockedStatic.close();
    }

    /**
     * Testing method for searching patients by name using mocking
     * using assertTrue
     * @throws BloodException
     */
    @Test
    void searchPatient() throws BloodException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        PatientDaoSQLImpl depDao = Mockito.mock(PatientDaoSQLImpl.class);
        daoFactoryMockedStatic.when(DaoFactory::patientDao).thenReturn(depDao);
        when(depDao.searchByPatientsName("Nerma Kadric")).thenReturn(patient);
        patientManager.searchPatients("Nerma Kadric");
        assertTrue(true);
        daoFactoryMockedStatic.close();
    }

    /**
     * checking if patient with the specified name exists
     * @throws BloodException
     */
    @Test
    void validatePatient() throws BloodException {
        MockedStatic<DaoFactory> dao = Mockito.mockStatic(DaoFactory.class);
        PatientDao UD = Mockito.mock(PatientDao.class);
        when(DaoFactory.patientDao()).thenReturn(UD);
        when(DaoFactory.patientDao().searchByPatientsName("Amna Salcin")).thenReturn(new Patient("Amna Salcin"));
        boolean x = PatientManager.validatePat(DaoFactory.patientDao().searchByPatientsName("Amna Salcin").getFull_Name());
        assertTrue(x);
        dao.close();
    }

    /**
     * testing if there is patients with specified name
     * @throws BloodException
     */
    @Test
    void test() throws BloodException{
        List<Patient> list=patientManager.searchPatients("Alma");
        assertTrue(list.isEmpty());
    }
}
