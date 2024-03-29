package ba.unsa.etf.rpr.Domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * java beans for table Patient
 * @author Azra Žunić
 * version 1.2
 */
public class Patient implements Idable, Serializable {
    private int Id;
    private String Full_Name;
    private LocalDate DateOfBirth;
    private String Gender;
    private int PhoneNumber;
    private Blood fk_BloodType;
    private Hospital fk_Hospital_id;
    /**
     * getters and setter for each private atribute
     * constructor, hashcode, toString, equals method
     */
    public Patient() {
    }
    public Patient(String Full_Name) {
        this.Full_Name=Full_Name;
    }

    public Patient(int id, String fullName, LocalDate dateOfBirth, String gender, String adress, int phoneNumber, Blood fk_BloodType, Hospital fk_Hospital_id) {
        Id = id;
        Full_Name = fullName;
        DateOfBirth = dateOfBirth;
        Gender = gender;
        PhoneNumber = phoneNumber;
        this.fk_BloodType = fk_BloodType;
        this.fk_Hospital_id = fk_Hospital_id;
    }
    @Override
    public int getId() {
        return Id;
    }

    @Override
    public void setId(int id) {
        this.Id = id;
    }
    public String getFull_Name() {
        return Full_Name;
    }
    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }
    public LocalDate getDateOfBirth() {
        return DateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
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
        return Id == patient.Id && PhoneNumber == patient.PhoneNumber && Objects.equals(Full_Name, patient.Full_Name) && Objects.equals(DateOfBirth, patient.DateOfBirth) && Objects.equals(Gender, patient.Gender) && Objects.equals(fk_BloodType, patient.fk_BloodType) && Objects.equals(fk_Hospital_id, patient.fk_Hospital_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Full_Name, DateOfBirth, Gender, PhoneNumber, fk_BloodType, fk_Hospital_id);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "Id=" + Id +
                ", FullName='" + Full_Name + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                ", Gender='" + Gender + '\'' +
                ", PhoneNumber=" + PhoneNumber +
                ", fk_BloodType=" + fk_BloodType +
                ", fk_Hospital_id=" + fk_Hospital_id +
                '}';
    }
}