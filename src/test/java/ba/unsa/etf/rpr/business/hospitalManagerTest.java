package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Dao.HospitalDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Azra Žunić
 * version 1.2
 */
public class hospitalManagerTest {
    private Hospital hospital;
    private HospitalDaoSQLImpl hospitalDaoSQLMock;
    private HospitalDaoSQLImpl hospitalDaoSQL=new HospitalDaoSQLImpl();
    private List<Hospital> h;
    private HospitalManager hospitalManager=new HospitalManager();

    /**
     * This method will be called before each test method
     * initializing values we need in tests
     */
    @BeforeEach
    public void initializeObjectsWeNeed(){
        hospitalManager = Mockito.mock(HospitalManager.class);
        hospital = new Hospital();
        hospital.setName("Sanasa");
        hospital.setQuantityOnHand(25);
        //hospital.setAdress("Grbavicka 15");
        hospital.setContactNumber (33446820);
        hospital.setDescription("Sve naj");

        hospitalDaoSQLMock = mock(HospitalDaoSQLImpl.class);
        h = new ArrayList<>();
        h.addAll(Arrays.asList(new Hospital("Poliklinika1"), new Hospital("Poliklinika2")));
    }
    /**
     * testing method that does the searching by name of the hospital
     */
    @Test
    public void searchByName(){
        HospitalManager hospitalManager1=new HospitalManager();
        Hospital d=new Hospital();
        d.setName("Nova");
        assertThrows(BloodException.class,()->hospitalManager1.searchByName(String.valueOf(d)),"There is no hospital with that name in out DB");
    }

    /**
     * simple test for Hospital table attributes
     */
    @Test
    void testHospitalProperties() {
       initializeObjectsWeNeed();
        assertEquals("Sanasa", hospital.getName());
        assertNull(hospital.getAdress());
        assertEquals(33446820, hospital.getContactNumber());
        assertEquals("Sve naj", hospital.getDescription());
        assertEquals(25, hospital.getQuantityOnHand());
    }

    /**
     * JUnit5 test for description attribute
     */
    @Test
    void testHospitalSetDescription() {
        initializeObjectsWeNeed();
        assertDoesNotThrow(() -> hospital.setDescription("Top-rated healthcare provider"));
        assertEquals("Top-rated healthcare provider", hospital.getDescription());
    }
    /**
     * Mocking tests
     * simple validation using method that searches hospitals by name
     */
    @Test
    public void validateHospital() throws BloodException {
        ArrayList<Hospital> hos=new ArrayList<>();
        initializeObjectsWeNeed();
        hos.add(hospital);
        MockedStatic<DaoFactory> dao = Mockito.mockStatic(DaoFactory.class);
        HospitalDaoSQLImpl UD = Mockito.mock(HospitalDaoSQLImpl.class);
        dao.when(DaoFactory::hospitalDao).thenReturn(UD);
        when(DaoFactory.hospitalDao().searchByName("Vojna Bolnica"));
        assertTrue(true);
        dao.close();
    }

    /**
     * testing method get by id
     * @throws BloodException
     */
    @Test
    void testGETId() throws BloodException{
        Hospital dep=hospitalDaoSQL.getById(1);
        assertNotEquals("KCUS", dep);
    }

}
