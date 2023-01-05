package ba.unsa.etf.rpr;
import ba.unsa.etf.rpr.Domain.Blood;
import ba.unsa.etf.rpr.Domain.Donor;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.business.BloodManager;
import ba.unsa.etf.rpr.business.DonorManager;
import ba.unsa.etf.rpr.business.PatientManager;


import net.bytebuddy.asm.Advice;
import org.apache.commons.cli.*;
import java.sql.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Azra Žunić
 * version 1.0
 * * CLI (Command Line Interface) implementation in following class
 */
public class App {
    private static final Option addPatient = new Option("p", "add-patient", false, "Adding new patient to BloodDonationSystem database");
    private static final Option addDonor = new Option("d", "add-donor", false, "Adding new donor to BloodDonationSystem database");
    private static final Option getPatient = new Option("getP", "get-patient", false, "Printing all patients from BloodDonationSystem database");
    private static final Option getDonor = new Option("getD", "get-donor", false, "Printing all donors from BloodDonationSystem database");

    private static final Option bloodtype = new Option(null, "BloodType", false, "Defining blood group for next added patient");

    /**
     * @param options
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar projekat.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addPatient);
        options.addOption(addDonor);
        options.addOption(getPatient);
        options.addOption(getDonor);
        return options;
    }

//    public static Donor searchThroughDonors(List<Donor> listOfDonors, String FullName) {
//        Donor donor = null;
//        donor = listOfDonors.stream().filter(d -> d.getFullName().toLowerCase().equals(FullName.toLowerCase())).findAny().get();
//        return donor;
//    }

    public static Patient searchThroughPatients(List<Patient> listOfPatients, String Full_Name) {
        Patient patient = null;
        patient = listOfPatients.stream().filter(p -> p.getFull_Name().toLowerCase().equals(Full_Name.toLowerCase())).findAny().get();
        return patient;
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
        //        while(true) {
        if ((cl.hasOption(addPatient.getOpt()) || cl.hasOption(addPatient.getLongOpt())) && cl.hasOption((bloodtype.getLongOpt()))) {
            PatientManager patientManager = new PatientManager();
            BloodManager bloodManager = new BloodManager();
            Blood blood = null;
            try {
                blood = searchThroughPatients(bloodManager.getAll(), cl.getArgList().get(1)).getFk_BloodType();
            } catch (Exception e) {
                System.out.println("There is no patient in the list! Try again.");
                System.exit(1);
            }
            Patient patient = new Patient();
            patient.setFull_Name(cl.getArgList().get(0));
            patient.setDateOfBirth(Date.valueOf(LocalDate.now())); //ne moze now
            patientManager.add(patient);
            System.out.println("You successfully added patient to database!");
//                break;
        } else if(cl.hasOption(getPatient.getOpt()) || cl.hasOption(getPatient.getLongOpt())){
            PatientManager patientManager = new PatientManager();
            patientManager.getAll().forEach(q -> System.out.println(q.getFull_Name()));
//                break;
        } else if(cl.hasOption(addDonor.getOpt()) || cl.hasOption(addDonor.getLongOpt())){
            try {
                DonorManager donorManager = new DonorManager();
                Donor d = new Donor();
                d.setFullName(cl.getArgList().get(0));
                donorManager.add(d);
                System.out.println("Donor has been added successfully");
//                    break;
            }catch(Exception e) {
                System.out.println("There is already donor with same name in database! Try again");
                System.exit(1);
//                   break;
            }

        } else if(cl.hasOption(getDonor.getOpt()) || cl.hasOption(getDonor.getLongOpt())){
            DonorManager categoryManager = new DonorManager();
            categoryManager.getAll().forEach(c -> System.out.println(c.getFullName()));
//                break;
        } else {
            printFormattedOptions(options);
            System.exit(-1);
//                break;
        }

        }
    }








        /*Blood obj=new Blood();
        try {
//          ArrayList<Blood> lista = new ArrayList<>((new BloodDaoSQLImpl()).getAll());
//            for(Blood clan : lista) {
//                System.out.println(clan.toString());
            }
            Patient h= DaoFactory.patientDao().getById(1);
            int i=h.getId();
            System.out.println(i);

        } catch (BloodException e) {
            System.out.println("Nesto nije u redu sa BloodDaoSQLImpl");
            throw new RuntimeException(e);
        }*/









