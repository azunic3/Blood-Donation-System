package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.Dao.BloodDaoSQLImpl;
import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Blood;
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
public class bloodManagerTest {
    private BloodManager bloodManager;
    private Blood blood;
    private BloodDaoSQLImpl bloodDaoSQLMock;
    private List<Blood> bl;
    /**
     * This method will be called before each test method
     */
    @BeforeEach
    public void initializeObjectsWeNeed(){
        bloodManager = Mockito.mock(BloodManager.class);
        blood = new Blood();
        blood.setId(50);
        blood.setBloodGroup("Blood Type");
        blood.setBloodAmount(500);
        blood.setBloodBagNumber("WER-7-GH");
        blood.setDonateDate(Date.valueOf(LocalDate.now()).toLocalDate());

        bloodDaoSQLMock = Mockito.mock(BloodDaoSQLImpl.class);
        bl = new ArrayList<>();
        bl.addAll(Arrays.asList(new Blood("A+"), new Blood("A-"), new Blood("B+"), new Blood("B-"),new Blood("AB-"),new Blood("AB+"),new Blood("0-"),new Blood("0+-")));
    }
    /**
     * In this method we will test validateBloodType(String group) for correct and incorrect passed parameters
     */
    @Test
    void validateBloodType() throws BloodException{
        String correctGroup = "B+";
        try {
            Mockito.doCallRealMethod().when(bloodManager).validateBloodType(correctGroup);
        } catch (BloodException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectNameShort = "A";
        Mockito.doCallRealMethod().when(bloodManager).validateBloodType(incorrectNameShort);
        BloodException bloodException1 = Assertions.assertThrows(BloodException.class, () -> {
            bloodManager.validateBloodType(incorrectNameShort);}, "Blood type must contain between 2 and 3 characters");
        Assertions.assertEquals("Blood type must contain between 2 and 3 characters", bloodException1.getMessage());

        String incorrectNameLong = RandomStringUtils.randomAlphabetic(50);
        Mockito.doCallRealMethod().when(bloodManager).validateBloodType(incorrectNameLong);
        BloodException bloodException2 = Assertions.assertThrows(BloodException.class, () -> {
            bloodManager.validateBloodType(incorrectNameLong);}, "Blood type must contain between 2 and 3 characters");
        Assertions.assertEquals("Blood type must contain between 2 and 3 characters", bloodException2.getMessage());
    }

    /**
     * We are testing add() method. Trying to add type that already exists
     * An exception will be thrown because our instance of Blood.java class has value for id
     */
    @Test
    void add() throws BloodException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::bloodDao).thenReturn(bloodDaoSQLMock);

        when(DaoFactory.bloodDao().getAll()).thenReturn(bl);
        Mockito.doCallRealMethod().when(bloodManager).add(blood);
        BloodException bloodException = Assertions.assertThrows(BloodException.class, () -> {
            bloodManager.add(blood);}, "Can't add information with ID. ID is autogenerated");

        Assertions.assertEquals("Can't add information with ID. ID is autogenerated", bloodException.getMessage());
        daoFactoryMockedStatic.verify(DaoFactory::bloodDao);
        Mockito.verify(bloodManager).add(blood);
        daoFactoryMockedStatic.close();
    }

    /**
     * Testing if we can add a blood id that is already in the table
     */
    @Test
    void addNewBloodType() throws BloodException {
        Blood newType = new Blood("Ca+");
        bloodManager.add(newType);

        Assertions.assertTrue(true);
        Mockito.verify(bloodManager).add(newType);
    }
}
