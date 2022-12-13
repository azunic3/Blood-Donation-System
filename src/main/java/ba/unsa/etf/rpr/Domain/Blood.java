package ba.unsa.etf.rpr.Domain;
import java.util.Objects;
import java.util.Date;
/**
 * bean for hospital
 * @author Azra Žunić
 * version 1.0
 */
public class Blood {
    private String BloodType;
    private int BloodBagNumber;
    private Date DonateDate;
    private String Status;
    private int BloodAmount;

    private Hospital fk_Hospital_id;

    public String getBloodType() {
        return BloodType;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;}
    public int getBloodBagNumber() {
        return BloodBagNumber;
    }

    public void setBloodBagNumber(int bloodBagNumber) {
        BloodBagNumber = bloodBagNumber;
    }

    public Date getDonateDate() {
        return DonateDate;
    }

    public void setDonateDate(Date donateDate) {
        DonateDate = donateDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
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
                ", Status='" + Status + '\'' +
                ", BloodAmount=" + BloodAmount +
                ", fk_Hospital_id=" + fk_Hospital_id +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Blood)) return false;
        Blood blood = (Blood) o;
        return BloodType == blood.BloodType && BloodBagNumber == blood.BloodBagNumber && BloodAmount == blood.BloodAmount && Objects.equals(DonateDate, blood.DonateDate) && Objects.equals(Status, blood.Status) && Objects.equals(fk_Hospital_id, blood.fk_Hospital_id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(BloodType, BloodBagNumber, DonateDate, Status, BloodAmount, fk_Hospital_id);
    }
}
