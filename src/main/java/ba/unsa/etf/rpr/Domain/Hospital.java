package ba.unsa.etf.rpr.Domain;
import java.util.Objects;
/**
 * bean for hospital
 * @author Azra Žunić
 * version 1.0
 */
public class Hospital {
    private int id;
    private String Name;
    private String Adress;
    private int ContactNumber;
    private String description;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getAdress() {
        return Adress;
    }
    public void setAdress(String adress) {
        Adress = adress;
    }
    public int getContactNumber() {
        return ContactNumber;
    }
    public void setContactNumber(int contactNumber) {
        ContactNumber = contactNumber;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Adress='" + Adress + '\'' +
                ", ContactNumber=" + ContactNumber +
                ", description='" + description + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hospital)) return false;
        Hospital hospital = (Hospital) o;
        return id == hospital.id && ContactNumber == hospital.ContactNumber && Objects.equals(Name, hospital.Name) && Objects.equals(Adress, hospital.Adress) && Objects.equals(description, hospital.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name, Adress, ContactNumber, description);
    }
}