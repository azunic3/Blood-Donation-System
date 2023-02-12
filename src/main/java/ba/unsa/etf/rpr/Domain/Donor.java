package ba.unsa.etf.rpr.Domain;

import ba.unsa.etf.rpr.Dao.DonorDaoSQLImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
/**
 * java beans for Donor table
 * @author Azra Žunić
 * version 1.2
 */
public class Donor extends DonorDaoSQLImpl implements Idable, Serializable {
    private int Id;
    private String FullName;
    private String Password;
    private Date DateOfBirth;
    private String Gender;
    private int PhoneNumber;
    private Blood BloodType_id_fk;
    private String AlreadyDonated;
    private Hospital fk_Hospital;

    /**
     *getters and setter for each private atribute
     * constructor, hashcode, toString, equals method
     */
    public Donor() {
    }
    public Donor(String fullName, String password){
        this.FullName=fullName;
        this.Password=password;
    }
    public Donor(String FullName) {
        this.FullName=FullName;
    }
    public Donor(int id, String fullName, String password, Date dateOfBirth, String gender, int phoneNumber, Blood bloodType_id_fk, String alreadyDonated, Hospital fk_Hospital) {
        this.Id = id;
      this. FullName = fullName;
       this.Password = password;
       this.DateOfBirth = dateOfBirth;
      this.Gender = gender;
       this.PhoneNumber = phoneNumber;
      this.BloodType_id_fk = bloodType_id_fk;
      this.AlreadyDonated = alreadyDonated;
        this.fk_Hospital = fk_Hospital;
    }
    public Donor(String fullName, String password, Date dateOfBirth, String gender, int phoneNumber, Blood bloodType_id_fk, String alreadyDonated, Hospital fk_Hospital) {
        this. FullName = fullName;
        this.Password = password;
        this.DateOfBirth = dateOfBirth;
        this.Gender = gender;
        this.PhoneNumber = phoneNumber;
        this.BloodType_id_fk = bloodType_id_fk;
        this.AlreadyDonated = alreadyDonated;
        this.fk_Hospital = fk_Hospital;
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public void setId(int id) {
        this.Id = id;
    }
    public String getFullName() {
        return FullName;
    }
    public void setFullName(String fullName) {
        FullName = fullName;
    }
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
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
    public int getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Blood getBloodType_id_fk() {
        return BloodType_id_fk;
    }

    public void setBloodType_id_fk(Blood bloodType_id_fk) {
        BloodType_id_fk = bloodType_id_fk;
    }

    public String getAlreadyDonated() {
        return AlreadyDonated;
    }

    public void setAlreadyDonated(String alreadyDonated) {
        AlreadyDonated = alreadyDonated;
    }

    public Hospital getFk_Hospital() {
        return fk_Hospital;
    }

    public void setFk_Hospital(Hospital fk_Hospital) {
        this.fk_Hospital = fk_Hospital;
    }

    @Override
    public String toString() {
      return "Donor{" +
                "Id=" + Id +
                ", FullName='" + FullName + '\'' +
                ", Password='" + Password + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                ", Gender='" + Gender + '\'' +
                ", PhoneNumber=" + PhoneNumber +
                ", BloodType_id_fk=" + BloodType_id_fk.getId() +
                ", AlreadyDonated='" + AlreadyDonated + '\'' +
                ", fk_Hospital=" + fk_Hospital.getId() +
                '}';
        //return FullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Donor)) return false;
        Donor donor = (Donor) o;
        return Id == donor.Id && PhoneNumber == donor.PhoneNumber && Objects.equals(FullName, donor.FullName) && Objects.equals(Password, donor.Password) && Objects.equals(DateOfBirth, donor.DateOfBirth) && Objects.equals(Gender, donor.Gender) && Objects.equals(BloodType_id_fk, donor.BloodType_id_fk) && Objects.equals(AlreadyDonated, donor.AlreadyDonated) && Objects.equals(fk_Hospital, donor.fk_Hospital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, FullName, Password, DateOfBirth, Gender, PhoneNumber, BloodType_id_fk, AlreadyDonated, fk_Hospital);
    }

}
