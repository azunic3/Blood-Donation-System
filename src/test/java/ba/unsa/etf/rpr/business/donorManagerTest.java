package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Dao.DonorDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Exceptions.BloodException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @author Azra Žunić
 * version 1.0
 */
public class donorManagerTest {
    private DonorManager donorManager;
    private Donor donor;
    private DonorDaoSQLImpl donorDaoSQLMock;
    private List<Donor> d;

    @BeforeEach
    public void initializeObjectsWeNeed(){
        donorManager = Mockito.mock(DonorManager.class);
        donor = new Donor();
        donor.setId(50);
        donor.setFullName("Nađa Kovačević");
        donor.setPassword("nadjak");
        donor.setAlreadyDonated("NO");

        donorDaoSQLMock = Mockito.mock(DonorDaoSQLImpl.class);
        d = new ArrayList<>();
        d.addAll(Arrays.asList(new Donor("Jedno Ime"), new Donor("Drugo Ime")));
    }
    @Test
    void validateDonorsName() throws BloodException {
        String correctName = "Nadja Kovacevic";
        try {
            Mockito.doCallRealMethod().when(donorManager).validateDonorsName(correctName);
        } catch (BloodException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }


        String incorrectNameShort = "Na";
        Mockito.doCallRealMethod().when(donorManager).validateDonorsName(incorrectNameShort);
        BloodException donorException1 = Assertions.assertThrows(BloodException.class, () -> {
            donorManager.validateDonorsName(incorrectNameShort);}, "Name must contain between 3 and 30 characters");
        Assertions.assertEquals("Name must contain between 3 and 30 characters", donorException1.getMessage());

        String incorrectNameLong = RandomStringUtils.randomAlphabetic(50);
        Mockito.doCallRealMethod().when(donorManager).validateDonorsName(incorrectNameLong);
        BloodException donorException2 = Assertions.assertThrows(BloodException.class, () -> {
            donorManager.validateDonorsName(incorrectNameLong);}, "Name must contain between 3 and 30 characters");
        Assertions.assertEquals("Name must contain between 3 and 30 characters", donorException2.getMessage());
    }

    @Test
    void addNewDonor() throws BloodException {
        Donor newType = new Donor("D");
        donorManager.add(newType);

        Assertions.assertTrue(true);
        Mockito.verify(donorManager).add(newType);
    }
}
