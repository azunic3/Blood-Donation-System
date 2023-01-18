package ba.unsa.etf.rpr;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.business.BloodManager;
import ba.unsa.etf.rpr.business.PatientManager;
import org.apache.commons.cli.*;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Azra Žunić
 * version 1.2
 * * CLI (Command Line Interface) implementation in following class
 */
public class App {
    /**
     * Defining final variables to describe all code having options
     */
    private static final Option addPatient = new Option("p", "add-patient", false, "Adding new patient to BloodDonationSystem database");
    private static final Option addBlood = new Option("b", "add-blood type", false, "Adding new blood group to BloodDonationSystem database");
    private static final Option getPatient = new Option("getP", "get-patient", false, "Printing all patients from BloodDonationSystem database");
    private static final Option getBlood = new Option("getB", "get-blood type", false, "Printing all blood groups from BloodDonationSystem database");
    private static final Option bloodtype = new Option(null, "BloodType", false, "Defining blood group for next added patient");

    /**
     * @param options
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar Projekat.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }
public static Options addOptions() {
        Options options = new Options();
        options.addOption(addPatient);
        options.addOption(addBlood);
        options.addOption(getPatient);
        options.addOption(getBlood);
        options.addOption(bloodtype);
        return options;
    }

    public static Blood searchThroughBloodTypes(List<Blood> listOfBlodTypes, String BloodGroup) {
        Blood blood = null;
        blood = listOfBlodTypes.stream().filter(p -> p.getBloodGroup().equals(BloodGroup)).findAny().get();
        return blood;
    }

    /**
     * @param args
     * @throws Exception
     * @author Azra Žunić
     */

    public static void main(String[] args) throws Exception {
        Options options = addOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cl = commandLineParser.parse(options, args);

        //ovaj dio je za pacijenta i fk blood type
      if ((cl.hasOption(addPatient.getOpt()) || cl.hasOption(addPatient.getLongOpt())) && cl.hasOption((bloodtype.getLongOpt()))) {
           PatientManager patientManager = new PatientManager();
           BloodManager bloodManager = new BloodManager();
           Blood blood = null;
             try {
                blood = searchThroughBloodTypes(bloodManager.getAll(), cl.getArgList().get(1));
            } catch (Exception e) {
                System.out.println("There is no blood group in the list! Try again.");
                System.exit(1);
            }

             //dodajem pacijenta u bazu
            Patient patient = new Patient();
             patient.setFk_BloodType(blood);
            patient.setFull_Name(cl.getArgList().get(0));
            patient.setDateOfBirth(Date.valueOf(LocalDate.of(2002,2,15)));
            patientManager.add(patient);
            System.out.println("You successfully added patient to database!");

        } else if(cl.hasOption(getPatient.getOpt()) || cl.hasOption(getPatient.getLongOpt())){
            PatientManager patientManager = new PatientManager();
            patientManager.getAll().forEach(q -> System.out.println(q.getFull_Name()));

        } else if(cl.hasOption(addBlood.getOpt()) || cl.hasOption(addBlood.getLongOpt())){
            try {
                BloodManager bloodManager = new BloodManager();
                Blood b = new Blood();
                b.setBloodGroup(cl.getArgList().get(0));
                bloodManager.add(b);
                System.out.println("Blood group has been added successfully");

            }catch(Exception e) {
                System.out.println("There is already the same blood group in database! Try again");
                System.exit(1);
            }

        } else if(cl.hasOption(getBlood.getOpt()) || cl.hasOption(getBlood.getLongOpt())){
          BloodManager bloodManager = new BloodManager();
          bloodManager.getAll().forEach(c -> System.out.println(c.getBloodGroup()));

        } else {
            printFormattedOptions(options);
            System.exit(-1);

        }

       }
        }










