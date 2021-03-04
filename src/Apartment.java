import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Apartment implements Rentable {

    private int daysOfRent;
    private int square;
    private final int ID = (int) (Math.random() * 10000);
    private boolean isBusy;
    private Person currentRenter;
    private String address;
    private ArrayList<Person> currentlyLiving;
    private int daysForWait;
    private Apartment addressOfObj;
    private ParkingPlace parkingPlace;
    private boolean isRentingPp;
    private File ownedFile;

    public Apartment(int square, String address) {
        this.square = square;
        isBusy = false;
        daysForWait = 30;
        isRentingPp = false;
        addressOfObj = this;
        currentlyLiving = new ArrayList<>();
        this.address = address;
        ownedFile = null;
    }


    @Override
    public void rent(Person p, int numberOfDays) {
        System.out.println("\n                                                 You started rent!");
        isBusy = true;
        currentRenter = p;
        daysOfRent = numberOfDays;
        p.checkIn(this);
        decreaseDays();
        checkRent();
    }

    @Override
    public void cancelRent(boolean rightNow) {
        System.out.print("\n                                                 Your rent is done, you can continue that!");
        currentRenter.getCurrentRentingAps().remove(this);
        System.out.println("wg");
        if (rightNow) {
            if (ownedFile != null) {
                ownedFile.delete();
            }
            currentRenter.getOwns().remove(ownedFile);
            clearApartment();

        } else {
            isBusy = false;
            waitFor();

        }
    }

    void decreaseDays() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (daysOfRent <= 0)
                    timer.cancel();
                else {
                    daysOfRent--;
                }

            }
        }, 0, 5000);
    }


    void checkRent() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (daysOfRent <= 0) {
                    if (isBusy) {
                        cancelRent(false);
                    }
                    timer.cancel();
                }
            }
        }, 0, 10000);
    }

    void waitFor() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (currentlyLiving.size() == 0) {
                    timer.cancel();
                    return;
                }

                if (daysForWait == 30) {
                    ownedFile = new File(currentRenter.getPath() + "/" + getID() + "_" + getAddress() + ".txt");
                    try {
                        if (ownedFile.createNewFile()) {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(ownedFile));
                            bw.write("You have owned this apartment: " + getAddress() + "!!!");
                            currentRenter.getOwns().add(ownedFile);
                            bw.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                if (currentRenter.getCurrentRentingAps().contains(addressOfObj)) {
                    try {
                        System.out.println("\n                                                 You continued renting!");
                        currentRenter.getOwns().remove(ownedFile);
                        ownedFile.delete();
                        currentRenter.rent(addressOfObj, daysOfRent);
                        timer.cancel();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                if (daysForWait <= 0) {
                    currentRenter.checkOut(addressOfObj);
                    clearApartment();
                }
                daysForWait--;
            }
        }, 0, 5000);
    }


    void checkIn(Person p) {
        if (isBusy) {
            currentlyLiving.add(p);
        }
    }

    void checkOut(Person p) {
        currentlyLiving.remove(p);
    }

    void clearApartment() {
        currentRenter.getCurrentRentingAps().remove(this);
        isBusy = false;
        daysOfRent = 0;
        currentRenter = null;
        currentlyLiving.clear();
        isRentingPp = false;
        parkingPlace = null;
    }

    void rentParkingPlace() {
        System.out.println("\n                                                 You started rent a Parking Place!");
        isRentingPp = true;
        parkingPlace = new ParkingPlace();

    }

    void place(Placeable thing) throws TooManyThingsException {
        if (isRentingPp) {
            parkingPlace.place(thing);
        } else System.out.println("\n                                                 Rent parking place first!!!!");
    }

    void delete(Placeable thing) {
        if (isRentingPp && parkingPlace.getThings().contains(thing)) {
            parkingPlace.delete(thing);
        }
    }

    boolean isBusy() {
        return isBusy;
    }

    public void setDaysOfRent(int daysOfRent) {
        this.daysOfRent = daysOfRent;
    }


    public int getID() {
        return ID;
    }

    public ParkingPlace getParkingPlace() {
        return parkingPlace;
    }

    public String getAddress() {
        return address;
    }

    public int getSquare() {
        return square;
    }


    public Person getCurrentRenter() {
        return currentRenter;
    }

    public ArrayList<Person> getCurrentlyLiving() {
        return currentlyLiving;
    }

    public boolean isRentingPp() {
        return isRentingPp;
    }

    public File getOwnedFile() {
        return ownedFile;
    }

    public String getInfo() {
        return "\n                                                  " + address + ", days left: " + daysOfRent + ", currently living: " + currentlyLiving.size() + " " +
                "\n                                                 " + currentlyLiving;
    }

    @Override
    public String toString() {
        return " " + address + ", days left: " + daysOfRent + ", currently living: " + currentlyLiving.size() + " ";
    }


}
