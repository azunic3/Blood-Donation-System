package ba.unsa.etf.rpr.Exceptions;

/**
 * user defined exception
 * @author Azra Žunić
 */
public class BloodException extends Exception{
    public BloodException(String msg, Exception reason){
        super(msg, reason);
    }

    public BloodException(String msg){
        super(msg);
    }
}
