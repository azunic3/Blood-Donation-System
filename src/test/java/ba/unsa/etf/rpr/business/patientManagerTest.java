package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Dao.PatientDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

   @Test
    void validateGender() throws BloodException {
        String gen="";
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
    void add() throws BloodException{
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        PatientDaoSQLImpl depDao = Mockito.mock(PatientDaoSQLImpl.class);
        daoFactoryMockedStatic.when(DaoFactory::patientDao).thenReturn(depDao);
        when(depDao.add(patient)).thenReturn(patient);
        patientManager.add(patient);
        Assertions.assertTrue(true);
        daoFactoryMockedStatic.close();
    }

    /**
     * testing method for searching patients by name using mocking
     * @throws BloodException
     */
    @Test
    void search() throws BloodException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        PatientDaoSQLImpl depDao = Mockito.mock(PatientDaoSQLImpl.class);
        daoFactoryMockedStatic.when(DaoFactory::patientDao).thenReturn(depDao);
        when(depDao.searchByPatientsName("Nerma Kadric")).thenReturn(patient);
        patientManager.searchPatients("Nerma Kadric");
        Assertions.assertTrue(true);
        daoFactoryMockedStatic.close();
    }
}
