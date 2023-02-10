package ba.unsa.etf.rpr;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.business.BloodManager;
import ba.unsa.etf.rpr.business.PatientManager;
import org.apache.commons.cli.*;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Azra Žunić
 * final version 1.3
 * CLI (Command Line Interface) implementation in following class
 */
public class App {
    /**
     * Defining final variables to describe all code having options
     */
    private static final Option addHospital = new Option("addH", "add-hospital", false, "Adding new hospital to database");
    private static final Option getHospital = new Option("getH", "get-hospital", false, "Printing all hospitals from database");
    private static final Option getBlood = new Option("getB", "get-blood", false, "Printing all attributes of table Blood");
    private static final Option updateHospital = new Option("uD", "update", false, "Updating information about hospital");
    private static final Option deleteHospital = new Option("d", "delete", false, "Deleting hospital");
    private static final Option name = new Option("n", "name", false, "Defining name of the hospital");
    private static final Option address = new Option("ad", "address", false, "Defining the address of the hospital");
    private static final Option contactNumber = new Option("cn", "contact-number", false, "Defining the contact number of an institution");
    private static final Option hospID = new Option("hId", "hospitalfk", false, "Defining the hospital where blood is stored");
    private static final Option quantityOnHand = new Option("q", "quantity-on-hand", false, "Defining available quantity of blood");
    private static final Option bloodBagNumber = new Option("ba", "blood-bag-number", false, "Defining blood bag number");

    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar Projekat.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addHospital);
        options.addOption(getHospital );
        options.addOption(getBlood );
        options.addOption(updateHospital);
        options.addOption(deleteHospital);
        options.addOption(name);
        options.addOption(address);
        options.addOption(contactNumber);
        options.addOption(hospID);
        options.addOption(quantityOnHand);
        options.addOption(bloodBagNumber);
        return options;
    }
    public static Hospital searchThroughHospitals(List<Hospital> listOfHosp, String eName) {
        Hospital e = listOfHosp.stream().filter(p -> p.getName().equals(eName.toLowerCase())).findAny().get();
        return e;
    }

}










