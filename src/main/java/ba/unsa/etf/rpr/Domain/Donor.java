package ba.unsa.etf.rpr.Domain;
import java.util.Objects;
import java.util.Date;
/**
 * bean for hospital
 * @author Azra Žunić
 * version 1.0
 */
public class Donor {
    private int id;
    private String FullName;
    private Date DateOfDonation;
    private Date DateOfBirth;
    private String Gender;
    private String Adress;
    private int PhoneNumber;
    private Blood fk_BloodType_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public Date getDateOfDonation() {
        return DateOfDonation;
    }

    public void setDateOfDonation(Date dateOfDonation) {
        DateOfDonation = dateOfDonation;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Blood getFk_BloodType_id() {
        return fk_BloodType_id;
    }

    public void setFk_BloodType_id(Blood fk_BloodType_id) {
        this.fk_BloodType_id = fk_BloodType_id;
    }

}
