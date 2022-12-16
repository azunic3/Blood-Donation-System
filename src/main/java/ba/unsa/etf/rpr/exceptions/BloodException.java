package ba.unsa.etf.rpr.exceptions;

public class BloodException extends Exception{
    public BloodException(String msg, Exception reason){
        super(msg, reason);
    }

    public BloodException(String msg){
        super(msg);
    }
}
