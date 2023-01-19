package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.Dao.DonorDaoSQLImpl;
import ba.unsa.etf.rpr.Domain.Donor;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        donor.setFullName("Neko Nebitno");
        donor.setPassword("zaporka");
        donor.setAlreadyDonated("NO");
        donor.setDateOfBirth(Date.valueOf("2001-01-25 02:00:00"));

        donorDaoSQLMock = Mockito.mock(DonorDaoSQLImpl.class);
        d = new ArrayList<>();
        d.addAll(Arrays.asList(new Donor("Jedno Ime"), new Donor("Drugo Ime")));
    }
}