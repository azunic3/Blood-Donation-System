package ba.unsa.etf.rpr.Domain;

/**
 * Interface that forces all POJO beans to have ID field
 */
public interface Idable {
    void setId(int Id);
    int getId();

}
