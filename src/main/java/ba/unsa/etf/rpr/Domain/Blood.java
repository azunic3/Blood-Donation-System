package ba.unsa.etf.rpr.Domain;
import ba.unsa.etf.rpr.Dao.BloodDaoSQLImpl;
import ba.unsa.etf.rpr.exceptions.BloodException;

import java.util.List;
import java.util.Objects;
import java.util.Date;
/**
 * bean for hospital
 * @author Azra Žunić
 * version 1.0
 */
public class Blood extends BloodDaoSQLImpl implements Idable {
    private String BloodType;
    private String BloodBagNumber;
    private Date DonateDate;
    private int BloodAmount;
    private Hospital fk_Hospital_id;

    public Blood() {
    }

    public Blood(String bloodType, String bloodBagNumber, Date donateDate, String status, int bloodAmount, Hospital fk_Hospital_id) {
        BloodType = bloodType;
        BloodBagNumber = bloodBagNumber;
        DonateDate = donateDate;
        BloodAmount = bloodAmount;
        this.fk_Hospital_id = fk_Hospital_id;
    }

    public String getBloodType() {
        return BloodType;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;}
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

    public Hospital getFk_Hospital_id() {
        return fk_Hospital_id;
    }

    public void setFk_Hospital_id(Hospital fk_Hospital_id) {
        this.fk_Hospital_id = fk_Hospital_id;
    }
    @Override
    public String toString() {
        return "Blood{" +
                "BloodType=" + BloodType +
                ", BloodBagNumber=" + BloodBagNumber +
                ", Code="  +
                ", DonateDate=" + DonateDate +
                ", BloodAmount=" + BloodAmount +
                ", fk_Hospital_id=" + fk_Hospital_id +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Blood)) return false;
        Blood blood = (Blood) o;
        return BloodType == blood.BloodType && BloodBagNumber == blood.BloodBagNumber && BloodAmount == blood.BloodAmount && Objects.equals(DonateDate, blood.DonateDate) &&  Objects.equals(fk_Hospital_id, blood.fk_Hospital_id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(BloodType, BloodBagNumber, DonateDate, BloodAmount, fk_Hospital_id);
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public List<Blood> searchByBagNumber(int BloodBagNumber) throws BloodException {
        return null;
    }
}
