package ba.unsa.etf.rpr.Domain;
import ba.unsa.etf.rpr.Dao.BloodDaoSQLImpl;
import ba.unsa.etf.rpr.Exceptions.BloodException;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Date;
/**
 * java beans class for table Hospital
 * @author Azra Žunić
 * version 1.2
 */
public class Blood implements Idable {

    private int Id;
    private String BloodBagNumber;
    private String BloodGroup;
    private Date DonateDate;
    private int BloodAmount;
    private Hospital fk_hospital_id;

//    public Blood() {
//    }
//
//    public Blood(int id, String bloodBagNumber, String bloodGroup, Date donateDate, int bloodAmount, Hospital fk_hospital_id) {
//        Id = id;
//        BloodBagNumber = bloodBagNumber;
//        BloodGroup = bloodGroup;
//        DonateDate = donateDate;
//        BloodAmount = bloodAmount;
//        this.fk_hospital_id = fk_hospital_id;
//    }

    /**
     *getters and setter for each private atribute
     * constructor, hashcode, toString, equals method
     */

    @Override
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getBloodBagNumber() {
        return BloodBagNumber;
    }
    public void setBloodBagNumber(String bloodBagNumber) {
        BloodBagNumber = bloodBagNumber;
    }

    public Date getDonateDate() {
        return DonateDate;
    }

    public void setDonateDate(Date donateDate) {
        DonateDate = donateDate;
    }

    public int getBloodAmount() {
        return BloodAmount;
    }

    public void setBloodAmount(int bloodAmount) {
        BloodAmount = bloodAmount;
    }

    public Hospital getFk_hospital_id() {
        return fk_hospital_id;
    }

    public void setFk_hospital_id(Hospital fk_hospital_id) {
        this.fk_hospital_id = fk_hospital_id;
    }

    @Override
    public String toString() {
        return "Blood{" +
                "Id=" + Id +
                ", BloodBagNumber='" + BloodBagNumber + '\'' +
                ", BloodGroup='" + BloodGroup + '\'' +
                ", DonateDate=" + DonateDate +
                ", BloodAmount=" + BloodAmount +
                ", fk_Hospital_id=" + fk_hospital_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Blood)) return false;
        Blood blood = (Blood) o;
        return Id == blood.Id && BloodAmount == blood.BloodAmount && Objects.equals(BloodBagNumber, blood.BloodBagNumber) && Objects.equals(BloodGroup, blood.BloodGroup) && Objects.equals(DonateDate, blood.DonateDate) && Objects.equals(fk_hospital_id, blood.fk_hospital_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, BloodBagNumber, BloodGroup, DonateDate, BloodAmount, fk_hospital_id);
    }


//    public List<Blood> searchByBagNumber(int BloodBagNumber) throws BloodException {
//        return null;
//    }
}
