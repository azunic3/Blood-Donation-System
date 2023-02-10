package ba.unsa.etf.rpr.Domain;

import java.util.Objects;
/**
 * java beans for hospital
 * @author Azra Žunić
 * version 1.2
 */
public class Hospital implements Idable {

    private int Id;
    private String Name;
    private int QuantityOnHand;
    private String Adress;
    private int ContactNumber;
    private String Description;

    /**
     * getters and setter for each private atribute
     * constructor, hashcode, toString, equals method
     */
    public Hospital() {
    }
    public Hospital(String Name) {
        this.Name=Name;
    }
    public Hospital(int id, String Name){
        this.Id=id;
        this.Name=Name;
    }
    public Hospital(int id, String name, int quantityOnHand, String adress, int contactNumber, String description) {
        Id = id;
        Name = name;
        QuantityOnHand = quantityOnHand;
        Adress = adress;
        ContactNumber = contactNumber;
        Description = description;
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public void setId(int id) {
        this.Id = id;
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


    public int getQuantityOnHand() {
        return QuantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        QuantityOnHand = quantityOnHand;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return Name ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hospital)) return false;
        Hospital hospital = (Hospital) o;
        return Id == hospital.Id && QuantityOnHand == hospital.QuantityOnHand && ContactNumber == hospital.ContactNumber && Objects.equals(Name, hospital.Name) && Objects.equals(Adress, hospital.Adress) && Objects.equals(Description, hospital.Description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Name, QuantityOnHand, Adress, ContactNumber, Description);
    }
}


