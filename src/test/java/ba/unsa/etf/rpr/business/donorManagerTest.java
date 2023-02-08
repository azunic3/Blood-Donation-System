package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Dao.DonorDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Azra Žunić
 * version 1.0
 */
public class donorManagerTest {
    private DonorManager donorManager = new DonorManager();
    private Donor donor;
    private DonorDaoSQLImpl donorDaoSQLMock;
    private List<Donor> d;
    /**
     * This method will be called before each test method
     */
    @BeforeEach
    public void initializeObjectsWeNeed(){
        donorManager = Mockito.mock(DonorManager.class);
        donor = new Donor();
        donor.setId(50);
        donor.setFullName("Nađa Kovačević");
        donor.setPassword("nadjak");
        donor.setAlreadyDonated("YES");

        donorDaoSQLMock = Mockito.mock(DonorDaoSQLImpl.class);
        d = new ArrayList<>();
        d.addAll(Arrays.asList(new Donor("Jedno Ime"), new Donor("Drugo Ime")));
    }

    /**
     * Testing if we can add a donor
     */
    @Test
    void addNewDonor() throws BloodException {
        Donor newType = new Donor("D");
        donorManager.add(newType);

        Assertions.assertTrue(true);
        Mockito.verify(donorManager).add(newType);
    }
    @Test
    void validatePassword(){
        String pass = "";
        assertThrows( BloodException.class, ()->donorManager.validatePassword(pass), "Password cannot be shorter than 6 signs nor longer than 20 signs!");
    }
    @Test
    void validateGender() throws BloodException {
        String gen="";
        assertThrows(BloodException.class, ()->donorManager.validateGender(gen), "Incorrect gender option!");
    }
    @Test
    void validateADonated() throws BloodException {
        String don="";
        assertThrows(BloodException.class, ()->donorManager.validateADonated(don), "An answer must be either 'YES' or 'NO'");
    }
    @Test
    void testing () throws BloodException, ParseException {
        String donated = "YES";
        Donor dd=new Donor();
        dd.setAlreadyDonated(donated);
//        dd.setId(10);
        dd.setFullName("Lejla Jakupovic");
//        dd.setPassword("lejlaj");
//        dd.setAlreadyDonated("YES");
//        Calendar.getInstance().set(2002,3,14);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date d=Calendar.getInstance().getTime();
//        String d1= sdf.format(d);
//        d=sdf.parse(d1);
//        dd.setDateOfBirth(d);
//        dd.setPhoneNumber(62830333);
//        Hospital hod=new Hospital();
//        hod.setId(2);
//        dd.setFk_Hospital(hod);
//        dd.setGender("F");
//        Blood bl = new Blood();
//        bl.setId(2);
//        dd.setBloodType_id_fk(bl);
        assertEquals(dd.getFullName(),donorManager.searchByDonorsName("Lejla Jakupovic").getFullName(),"Something is wrong");

    }
    }
