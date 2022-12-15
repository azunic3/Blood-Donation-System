package ba.unsa.etf.rpr.Domain;
import java.util.Objects;
import java.util.Date;
/**
 * bean for hospital
 * @author Azra Žunić
 * version 1.0
 */
public class Donor {
    private int Donor_id;
    private String FullName;
    private Date DateOfBirth;
    private String Gender;
    private String Adress;
    private int PhoneNumber;
    private Blood fk_BloodType_id;

    private String Password;
    public Donor(int donor_id, String fullName, Date dateOfBirth, String gender, String adress, int phoneNumber, Blood fk_BloodType_id, String password, String username) {
        Donor_id = donor_id;
        FullName = fullName;
        DateOfBirth = dateOfBirth;
        Gender = gender;
        Adress = adress;
        PhoneNumber = phoneNumber;
        this.fk_BloodType_id = fk_BloodType_id;
        Password = password;
        Username = username;
    }

    public Donor() {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    private String Username;
    @Override
    public String toString() {
        return "Donor{" +
                "Donor_id=" + Donor_id +
                ", FullName='" + FullName + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                ", Gender='" + Gender + '\'' +
                ", Adress='" + Adress + '\'' +
                ", PhoneNumber=" + PhoneNumber +
                ", fk_BloodType_id=" + fk_BloodType_id +
                ", Username=" + Username +
                '}';
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getDonor_id() {
        return Donor_id;
    }

    public void setDonor_id(int donor_id) {
        Donor_id = donor_id;
    }

    public String getFullName() {
        return FullName;
    }
    public void setFullName(String fullName) {
        FullName = fullName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Donor)) return false;
        Donor donor = (Donor) o;
        return Donor_id == donor.Donor_id && PhoneNumber == donor.PhoneNumber && Objects.equals(FullName, donor.FullName) && Objects.equals(DateOfBirth, donor.DateOfBirth) && Objects.equals(Gender, donor.Gender) && Objects.equals(Adress, donor.Adress) && Objects.equals(fk_BloodType_id, donor.fk_BloodType_id) && Objects.equals(Password, donor.Password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Donor_id, FullName, DateOfBirth, Gender, Adress, PhoneNumber, fk_BloodType_id, Password);
    }

}
