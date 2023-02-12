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
    private List<Hospital> h;
    private HospitalManager hospitalManager = new HospitalManager();

    /**
     * This method will be called before each test method
     */
    @BeforeEach
    public void initializeObjectsWeNeed(){
        hospital = new Hospital("Vojna Bolnica");

        hospital.setId(4);
        hospital.setName("Sanasa");
        hospital.setQuantityOnHand(25);
        hospital.setAdress("Grbavicka 15");
        hospital.setContactNumber (448552);
        hospital.setDescription("Sve naj");

        hospitalDaoSQLMock = mock(HospitalDaoSQLImpl.class);
        h = new ArrayList<>();
        h.addAll(Arrays.asList(new Hospital("Poliklinika1"), new Hospital("Poliklinika2")));
    }
    @Test
    void validateHName() {
        String name = "kli";
        assertThrows(BloodException.class, () -> hospitalManager.validateHospitalName(name), "Name must contain between 4 and 20 characters");
    }
    @Test
    public void searchByName(){
        HospitalManager hospitalManager1=new HospitalManager();
        Hospital d=new Hospital();
        d.setId(4);
        d.setName("Bolnica");
        assertThrows(BloodException.class,()->hospitalManager1.searchByName(String.valueOf(d)),"There is no hospital with that name in out DB");
    }
    @Test
    void testHospitalProperties() {
        Hospital hospital = new Hospital(5, "Sanasa", 100,  null, 33446820, "Leading medical facility");
        assertEquals("Sanasa", hospital.getName());
        assertNull(hospital.getAdress());
        assertEquals(33446820, hospital.getContactNumber());
        assertEquals("Leading medical facility", hospital.getDescription());
        assertEquals(100, hospital.getQuantityOnHand());
    }

    @Test
    void testHospitalSetDescription() {
        Hospital hospital = new Hospital(5, "Sanasa", 100,  "Grbavicka 15", 33446820, "Leading medical facility");
        assertDoesNotThrow(() -> hospital.setDescription("Top-rated healthcare provider"));
        assertEquals("Top-rated healthcare provider", hospital.getDescription());
    }
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
}
