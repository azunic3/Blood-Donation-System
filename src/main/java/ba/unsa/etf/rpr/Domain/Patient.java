package ba.unsa.etf.rpr.Domain;
import java.util.Date;
import java.util.Objects;

/**
 * bean for patient
 * @author Azra Žunić
 * version 1.0
 */
public class Patient {
    private int id;
    private String FullName;
    private Date DateOfBirth;
    private String Gender;
    private int PhoneNumber;
    private Blood fk_BloodType;
    private Hospital fk_Hospital_id;

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
    public Blood getFk_BloodType() {
        return fk_BloodType;
    }
    public void setFk_BloodType(Blood fk_BloodType) {
        this.fk_BloodType = fk_BloodType;
    }
    public Hospital getFk_Hospital_id() {
        return fk_Hospital_id;
    }
    public void setFk_Hospital_id(Hospital fk_Hospital_id) {
        this.fk_Hospital_id = fk_Hospital_id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return id == patient.id && PhoneNumber == patient.PhoneNumber && Objects.equals(FullName, patient.FullName) && Objects.equals(DateOfBirth, patient.DateOfBirth) && Objects.equals(Gender, patient.Gender) && Objects.equals(fk_BloodType, patient.fk_BloodType) && Objects.equals(fk_Hospital_id, patient.fk_Hospital_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, FullName, DateOfBirth, Gender, PhoneNumber, fk_BloodType, fk_Hospital_id);
    }
    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", FullName='" + FullName + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                ", Gender='" + Gender + '\'' +
                ", PhoneNumber=" + PhoneNumber +
                ", fk_BloodType=" + fk_BloodType +
                ", fk_Hospital_id=" + fk_Hospital_id +
                '}';
    }
}
