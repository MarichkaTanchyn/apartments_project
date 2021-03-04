import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Person {
    private String name;
    private String surname;
    private long pesel;
    private final int id = (int) (Math.random() * 10000);
    private String address;
    private String dateOfBirth;
    private ArrayList<File> owns;
    private ArrayList<Apartment> currentRentingAps;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Apartment> currentLivingIn;
    private HashMap<Integer, Placeable> things;
    private String path;


    Person(String name, String surname, long pesel, String dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        owns = new ArrayList<>();
        currentRentingAps = new ArrayList<>();
        vehicles = new ArrayList<>();
        currentLivingIn = new ArrayList<>();
        things = new HashMap<>();
        this.dateOfBirth = dateOfBirth;
        path = "./src/" + id + "_" + name + surname;
        File ownDir = new File(path);
        ownDir.mkdir();
        ownDir.deleteOnExit();
    }


    Person rent(Apartment apartment, int numberOfDays) throws ProblematicTenantException {
        if (owns.size() >= 1) {
            String[] arr = new String[owns.size()];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = owns.get(i).toString().split("/")[3].replace(".txt", "").split("_")[1];
            }
            throw new ProblematicTenantException(this, arr);

        }

        if (!currentRentingAps.contains(apartment)) {
            currentRentingAps.add(apartment);
            address = apartment.getAddress();
        }
        if (!apartment.isBusy()) {
            apartment.rent(this, numberOfDays);
        }
        return this;
    }

    void continueRent(Apartment apartment, int numberOfDays) {
        currentRentingAps.add(apartment);
        apartment.setDaysOfRent(numberOfDays);
    }


    void cancelRent(Apartment apartment) {
        if (currentLivingIn.contains(apartment)) {
            apartment.cancelRent(true);
            currentLivingIn.remove(apartment);
            if (currentLivingIn.size() == 0) {
                address = null;
            } else address = currentLivingIn.get(0).getAddress();
        }
    }

    boolean checkIn(Apartment apartment) {
        if (apartment.isBusy() && !apartment.getCurrentlyLiving().contains(this)) {
            currentLivingIn.add(apartment);
            apartment.checkIn(this);
            return true;
        } else return false;

    }

    void checkOut(Apartment apartment) {
        if (currentLivingIn.contains(apartment)) {
            currentLivingIn.remove(apartment);
            apartment.checkOut(this);
            System.out.println("\n                                                 You are successfully checked out!");
        }
    }

    void rentParkingPlace(Apartment apartment) {
        if (currentRentingAps.contains(apartment)) {
            apartment.rentParkingPlace();
        }
    }

    void place(Placeable thing, Apartment ap) throws TooManyThingsException {
        ap.place(thing);
        things.put(thing.getId(), thing);
    }

    void delete(Placeable thing, Apartment ap) {
        ap.delete(thing);
        things.remove(thing.getId(), thing);
    }

    void saveCurrentInfo() {
        currentRentingAps.sort((Apartment a1, Apartment a2)->{
            if (a1.getSquare() > a2.getSquare()) return 1;
            else if (a1.getSquare() < a2.getSquare()) return -1;
            else return 0;
        });

        for (Apartment ap: currentRentingAps) {
            if (ap.isRentingPp() && things.size() > 1){
                ap.getParkingPlace().getThings().sort((Placeable t1, Placeable t2)->{
                    if (t1.getSquare() < t2.getSquare()) return 1;
                    else if (t1.getSquare() > t2.getSquare()) return -1;
                    else return t1.getName().compareTo(t2.getName());
                });
            }
        }
        try {
            File currentInfo = new File(path + LocalDate.now() + "_info.txt");
            if (currentInfo.createNewFile()) {

                BufferedWriter bw = new BufferedWriter(new FileWriter(currentInfo));
                bw.write("\n   INFO ABOUT " + name.toUpperCase() + " " + surname.toUpperCase() + "\n");

                currentRentingAps.forEach(apartment -> {

                        String info = "\n          " + apartment.getAddress() + ":\n\n          - " + apartment.getSquare() + " meters square," +
                                "\n          - Currently living: " + apartment.getCurrentlyLiving()  +
                                (apartment.isRentingPp() && things.size() != 0 ?
                                        "\n\n        Garage: " +
                                                "\n            - " +
                                                apartment
                                                        .getParkingPlace()
                                                        .getThings()
                                                         +
                                                "\n\n        Currently in use " + (10 - apartment.getParkingPlace().getSquare()) +
                                                " of 10\n" : "\n");

                    try {
                        bw.write(info);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });

                bw.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }


    public ArrayList<Apartment> getCurrentRentingAps() {
        return currentRentingAps;
    }

    void showParkingPlace(Apartment ap) {
        System.out.println("\n                                " + things);
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public HashMap<Integer, Placeable> getThings() {
        return things;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Apartment> getCurrentLivingIn() {
        return currentLivingIn;
    }


    String getData() {
        return "\n                                                 " + name + " " + surname + ":" +
                "\n\n                                                 pesel: " + pesel + " " +
                "\n\n                                                 address: " + (address != null ? address : " no address") +
                "\n\n                                                 Date of birth: " + dateOfBirth +
                "\n\n                                                 Currently renting: " + currentRentingAps +
                "\n\n                                                 Currently living in: " + currentLivingIn;

    }

    public String getPath() {
        return path;
    }

    public ArrayList<File> getOwns() {
        return owns;
    }


    @Override
    public String toString() {
        return name + " " + surname + " (" + id + ")";
    }
}
