package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Hospital;
import ba.unsa.etf.rpr.business.BloodManager;
import ba.unsa.etf.rpr.business.HospitalManager;
import org.apache.commons.cli.*;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

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
    private static final Option updateHospital = new Option("uH", "update", false, "Updating information about hospital");
    private static final Option deleteHospital = new Option("dH", "delete", false, "Deleting hospital");
    private static final Option name = new Option("n", "name", false, "Defining name of the hospital");
    private static final Option quantityOnHand = new Option("q", "quantity-on-hand", false, "Defining available quantity of blood");
    private static final Option address = new Option("ad", "address", false, "Defining the address of the hospital");
    private static final Option contactNumber = new Option("cn", "contact-number", false, "Defining the contact number of an institution");
   private static final Option description = new Option("d", "description", false, "Defining description of the hospital");
    private static final Option hospID = new Option("hId", "hospitalfk", false, "Defining the hospital where blood is stored");
    private static final Option bloodBagNumber = new Option("ba", "blood-bag-number", false, "Defining blood bag number");
    private static final Option bloodGroup = new Option("bg", "blood-group", false, "Defining blood type");

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
        options.addOption(getHospital);
        options.addOption(getBlood);
        options.addOption(updateHospital);
        options.addOption(deleteHospital);
        options.addOption(name);
        options.addOption(quantityOnHand);
        options.addOption(address);
        options.addOption(contactNumber);
        options.addOption(description);
        options.addOption(hospID);
        options.addOption(bloodBagNumber);
        options.addOption(bloodGroup);
        return options;
    }

    public static Hospital searchThroughHospitals(List<Hospital> listOfHosp, String eName) {
        Hospital e = listOfHosp.stream().filter(p -> p.getName().equals(eName.toLowerCase())).findAny().get();
        return e;
    }

    public static Blood searchThroughBlood(List<Blood> listOfDep, String h) {
        Blood e = listOfDep.stream().filter(d -> Objects.equals(d.getBloodGroup(), h)).findAny().orElse(null);
        return e;
    }

    public static void main(String[] args) throws Exception {
        Options options = addOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cl = commandLineParser.parse(options, args);
        if (cl.hasOption(addHospital.getOpt()) || cl.hasOption(addHospital.getLongOpt())) {
            HospitalManager hospitalManager = new HospitalManager();
           /* Hospital hosp = null;
            try {
               hosp = searchThroughHospitals(hospitalManager.getAll(), cl.getArgList().get(1));
            } catch (Exception e) {
                System.out.println("There is no hospital in the list! Try again.");
                System.exit(1);
            }*/
            Hospital hospital=new Hospital();


            hospital.setName(cl.getArgList().get(0));
            hospital.setQuantityOnHand(Integer.parseInt(cl.getArgList().get(1)));
            hospital.setAdress(cl.getArgList().get(2));
            hospital.setContactNumber(Integer.parseInt(cl.getArgList().get(3)));
            hospital.setDescription(cl.getArgList().get(4));

            hospitalManager.add(hospital);
            System.out.println("You successfully added new hospital to the DataBase!");
        }
//posto ne radim nista s hospital u gui ovdje sm to stavila al nez sad mozel to bit problem tamo za hospital...
        //ne moze to bit prob stani da izguglam
        else if (cl.hasOption(getHospital.getOpt()) || cl.hasOption(getHospital.getLongOpt())){
            HospitalManager hm=new HospitalManager();
            hm.getAll().forEach(q -> System.out.println(q.getName()));
        }

        else if (cl.hasOption(getBlood.getOpt()) || cl.hasOption(getBlood.getLongOpt())){
            BloodManager hm=new BloodManager();
            hm.getAll().forEach(q -> System.out.println(q.getBloodGroup()));
        }

        else if (cl.hasOption("uH")) {
            HospitalManager hospManager = new HospitalManager();
            String hname=cl.getArgList().get(0);
            hospManager.update(searchThroughHospitals(hospManager.getAll(),hname));
            System.out.println("Hospital successfully updated");
        }

        else if (cl.hasOption("dH")){
            int hid= Integer.parseInt(cl.getArgList().get(0));
            HospitalManager hoManager = new HospitalManager();
            hoManager.delete(hid);
            System.out.println("Hospital successfully deleted");
        }
        else {
            printFormattedOptions(options);
            System.exit(-1);
        }
    }
}










