import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, Apartment> apartments = getApartment();
        HashMap<Integer, Person> people = getPeople();
        printGreeting();

        Scanner scInt = new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);


        int numInput = 0;
        String strInput = "";
        Person currentPerson = null;

        while (true) {

            printMenu();
            strInput = scStr.nextLine();
            if (strInput.equals("0"))System.exit(1);

            switch (strInput) {
                case "1":

                    try {
                        System.out.println();
                        people.forEach((k, v) -> System.out.println("                                                 (" + k + ") " + v.getName() + " " + v.getSurname()));
                        System.out.print("\n                                                 Enter id of person: ");
                        numInput = scInt.nextInt();

                        if (numInput == 9) break;
                        if (numInput == 0) System.exit(1);

                        if (!people.containsKey(numInput)) {
                            printError();
                            break;
                        }

                        currentPerson = people.get(numInput);
                    } catch (Exception e) {
                        printError();
                    }
                    break;
                case "2":
                    try {
                        System.out.println(currentPerson.getData());
                        waitFor();
                    } catch (Exception e) {
                        printError();
                    }

                    break;

                case "3":
                    try {
                        System.out.println();
                        apartments.forEach((k, v) -> {
                            System.out.println("                                                 (" + k + ") " + v.getAddress() + " Square: " + v.getSquare());
                        });
                        waitFor();
                    } catch (Exception e) {
                        printError();
                    }
                    break;

                case "4":
                    rentMenu();
                    if (numInput == 0) System.exit(1);
                    strInput = scStr.nextLine();
                    switch (strInput) {
                        case "1":
                            try {
                                apartments.forEach((k, v) -> {
                                    System.out.println("                                                 (" + k + ") " + v.getAddress() + " Square: " + v.getSquare());
                                });
                                System.out.print("\n                                                 Enter id of apartment: ");
                                numInput = scInt.nextInt();

                                if (numInput == 9) break;
                                if (numInput == 0) System.exit(1);


                                if (!apartments.containsKey(numInput)) {
                                    printError();
                                    break;
                                }


                                Apartment currentAp = apartments.get(numInput);

                                System.out.print("\n                                                 Enter number of days: ");
                                numInput = scInt.nextInt();

                                if (numInput <= 0) {
                                    printError();
                                    break;
                                }

                                if (currentPerson.getCurrentLivingIn().contains(currentAp)) {
                                    currentPerson.continueRent(currentAp, numInput);
                                } else currentPerson.rent(currentAp, numInput);

                            } catch (ProblematicTenantException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                printError();
                            }
                            break;

                        case "2":
                            try {
                                currentPerson.getCurrentRentingAps().forEach(v -> {
                                    System.out.println("                                                 (" + v.getID() + ") " + v.getAddress() + " Square: " + v.getSquare());
                                });
                                System.out.print("\n                                                 Enter id of apartment: ");
                                numInput = scInt.nextInt();
                                if (numInput == 9) break;
                                if (numInput == 0) System.exit(1);

                                Apartment currentAp = apartments.get(numInput);
                                if (!currentPerson.getCurrentRentingAps().contains(currentAp)) {
                                    printError();
                                    break;
                                }
                                currentPerson.rentParkingPlace(currentAp);

                            } catch (Exception e) {
                                printError();
                            }

                            break;

                        case "3":
                            try {
                                currentPerson.getCurrentRentingAps().forEach(v -> {
                                    if (v.isRentingPp())
                                        System.out.println("                                                 (" + v.getID() + ") " + v.getAddress() + " Square: " + v.getSquare());
                                });
                                System.out.print("\n                                                 Enter id: ");
                                numInput = scInt.nextInt();
                                if (numInput == 9) break;
                                if (numInput == 0) System.exit(1);

                                Apartment currentAp = apartments.get(numInput);
                                if (!currentPerson.getCurrentRentingAps().contains(currentAp)) {
                                    printError();
                                    break;
                                }

                                currentPerson.showParkingPlace(currentAp);
                            } catch (Exception e) {
                                printError();
                            }
                            break;

                        case "4":
                            try {
                                apartments.forEach((k, v) -> {
                                    if (v.isBusy())
                                        System.out.println("                                                 (" + k + ") " + v.getAddress() + " Square: " + v.getSquare());
                                });
                                System.out.print("\n                                                 Enter id of apartment: ");
                                numInput = scInt.nextInt();
                                if (numInput == 9) break;
                                if (numInput == 0) System.exit(1);

                                Apartment currentAp = apartments.get(numInput);
                                if (!apartments.containsKey(numInput)) {
                                    printError();
                                    break;
                                }
                                if (!currentPerson.checkIn(currentAp)) {
                                    printError();
                                }

                            } catch (Exception e) {
                                printMenu();
                            }
                            break;

                        case "5":
                            try {
                                currentPerson.getCurrentLivingIn().forEach(v -> {
                                    System.out.println("                                                 (" + v.getID() + ") " + v.getAddress() + " Square: " + v.getSquare());
                                });
                                System.out.print("\n                                                 Enter id : ");
                                numInput = scInt.nextInt();
                                if (numInput == 9) break;
                                if (numInput == 0) System.exit(1);

                                Apartment currentAp = apartments.get(numInput);
                                if (!apartments.containsKey(numInput)) {
                                    printError();
                                    break;
                                }
                                if (currentPerson.getCurrentLivingIn().contains(currentAp)) {
                                    currentPerson.checkOut(currentAp);
                                }
                            } catch (Exception e) {
                                printError();
                            }
                            break;
                        case "6":
                            try {
                                currentPerson.getCurrentLivingIn().forEach(v -> {
                                    System.out.println("                                                 (" + v.getID() + ") " + v.getAddress() + " Square: " + v.getSquare());
                                });

                                System.out.print("\n                                                 Enter id: ");
                                numInput = scInt.nextInt();
                                if (numInput == 9) break;
                                if (numInput == 0) System.exit(1);

                                Apartment currentAp = apartments.get(numInput);
                                if (!currentPerson.getCurrentLivingIn().contains(currentAp) || currentAp.getCurrentRenter() != currentPerson) {
                                    printError();
                                    break;
                                }

                                currentPerson.cancelRent(currentAp);


                            } catch (Exception e) {
                                printError();
                            }
                            break;
                    }

                    break;

                case "5":
                    try {
                        currentPerson.getCurrentRentingAps().forEach(v -> {
                            System.out.println("                                                 (" + v.getID() + ") " + v.getAddress() + " Square: " + v.getSquare());
                        });

                        System.out.print("\n                                                 Enter id: ");
                        numInput = scInt.nextInt();
                        if (numInput == 9) break;
                        if (numInput == 0) System.exit(1);

                        Apartment currentAp = apartments.get(numInput);
                        if (!currentPerson.getCurrentLivingIn().contains(currentAp) || currentAp.getCurrentRenter() != currentPerson) {
                            printError();
                            break;
                        }

                        System.out.println("\n                                                 "+currentAp.getInfo());



                    } catch (Exception e) {
                        printError();
                    }
                    break;

                case "6":
                    try {
                        currentPerson.getCurrentRentingAps().forEach(v -> {
                            if (v.isRentingPp())
                                System.out.println("                                                 (" + v.getID() + ") " + v.getAddress() + " Square: " + v.getSquare());
                        });
                        System.out.print("\n                                                 Enter id: ");
                        numInput = scInt.nextInt();
                        if (numInput == 9) break;
                        if (numInput == 0) System.exit(1);

                        Apartment currentAp = apartments.get(numInput);
                        if (!currentPerson.getCurrentRentingAps().contains(currentAp) || currentAp.getCurrentRenter() != currentPerson) {
                            printError();
                            break;
                        }
                        System.out.print("\n                                                 What type of thing do you want to put?" +
                                "\n\n                                                 (1) SUV" +
                                "\n                                                 (2) City Car" +
                                "\n                                                 (3) Boat" +
                                "\n                                                 (4) Amfibia" +
                                "\n                                                 (5) Motobike" +
                                "\n\n                                                 (6) Another");
                        System.out.print("\n                                                 Enter id: ");

                        int choice = scInt.nextInt();
                        if ((choice >= 1) && (choice <= 5)) {
                            String name;
                            double surface;
                            double engineCapacity;
                            String engineType;
                            String vehicleType;
                            System.out.print("\n\n                                                 Enter a name of your Vehicle: ");
                            name = scStr.nextLine();
                            System.out.print("\n                                                 Enter a surface of your Vehicle: ");
                            surface = scInt.nextDouble();
                            System.out.print("\n                                                 Enter a engine Capacity of your Vehicle: ");
                            engineCapacity = scInt.nextDouble();
                            System.out.print("\n                                                 Enter a engine type of your Vehicle: ");
                            engineType = scStr.nextLine();
                            System.out.print("\n                                                 Enter a engine type of your Vehicle: ");
                            vehicleType = scStr.nextLine();


                            switch (String.valueOf(choice)) {
                                case "1":
                                    boolean sentDriving;
                                    System.out.print("\n\n                                                 Can your SUV drive on send?(yes/no): ");
                                    strInput = scStr.nextLine();
                                    if (strInput.equals("yes")) {
                                        sentDriving = true;
                                    } else sentDriving = false;
                                    currentPerson.place(new SUV(name, surface, engineCapacity, engineType, vehicleType, sentDriving), currentAp);
                                    break;
                                case "2":
                                    String carcassType;
                                    System.out.print("\n\n                                                 Enter a carcass type of your Car: ");
                                    carcassType = scStr.nextLine();
                                    currentPerson.place(new CityCar(name, surface, engineCapacity, engineType, vehicleType, carcassType), currentAp);
                                    break;

                                case "3":
                                    String typeOfTree;
                                    System.out.print("\n\n                                                 Enter a type of tree by your Boat: ");
                                    typeOfTree = scStr.nextLine();
                                    currentPerson.place(new Boat(name, surface, engineCapacity, engineType, vehicleType, typeOfTree), currentAp);
                                    break;

                                case "4":
                                    String mixtureOf;
                                    System.out.print("\n\n                                                 Enter a mixture of your Amphibian: ");
                                    mixtureOf = scStr.nextLine();
                                    currentPerson.place(new Amphibian(name, surface, engineCapacity, engineType, vehicleType, mixtureOf), currentAp);
                                    break;

                                case "5":
                                    double wheelDiagonal;
                                    System.out.print("\n\n                                                 Enter a diagonal of wheel in your Motorcycle: ");
                                    wheelDiagonal = scStr.nextDouble();
                                    currentPerson.place(new Motorcycle(name, surface, engineCapacity, engineType, vehicleType, wheelDiagonal), currentAp);
                                    break;
                            }
                        } else if (choice == 6) {
                            String name;
                            double square;
                            System.out.print("\n\n                                                 Enter a name of your thing: ");
                            name = scStr.nextLine();
                            System.out.print("\n\n                                                 Enter a square of your thing: ");
                            square = scInt.nextDouble();
                            currentPerson.place(new Placeable(square, name), currentAp);
                            break;
                        }


                    } catch (Exception e) {
                        printError();
                    }

                    break;
                case "7":
                    currentPerson.getCurrentRentingAps().forEach(v -> {
                        if (v.isRentingPp() && v.getParkingPlace().getThings().size() > 0)
                            System.out.println("                                                 (" + v.getID() + ") " + v.getAddress() + " Square: " + v.getSquare());
                    });
                    System.out.print("\n                                                 Enter id: ");
                    numInput = scInt.nextInt();
                    if (numInput == 9) break;
                    if (numInput == 0) System.exit(1);

                    Apartment currentAp = apartments.get(numInput);
                    if (!currentPerson.getCurrentRentingAps().contains(currentAp) || currentAp.getCurrentRenter() != currentPerson) {
                        printError();
                        break;
                    }
                    System.out.println("\n                                                 "+currentAp.getParkingPlace().getThings());
                    System.out.print("\n                                                 Enter id: ");
                    numInput = scInt.nextInt();

                    if (numInput == 9) break;
                    if (numInput == 0) System.exit(1);

                    currentPerson.delete(currentPerson.getThings().get(numInput), currentAp);
                    break;
                case "8":
                    try {

                        currentPerson.saveCurrentInfo();
                        System.out.print("\n                                                 Your info is successfully saved!");


                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    break;

            }
        }
    }


    static void printError() {
        System.out.println("\n\n                                                 Error occurred! Please, try again.\n\n ");
    }

    static void rentMenu() {
        System.out.print("\n\n                                                 (1) Rent or continue renting a flat" +
                "\n                                                 (2) Start renting Parking Place" +
                "\n                                                 (3) Show Parking Place" +
                "\n                                                 (4) Check In" +
                "\n                                                 (5) Check Out" +
                "\n                                                 (6) Cancel renting" +
                "\n\n\n                                                 Input: ");
    }


    static void printMenu() {
        System.out.print(
                "\n\n                                                 Main Menu" +
                        "\n                                                 --------------" +
                        "\n                                                 (1) Pick a person" +
                        "\n                                                 (2) Show personal info" +
                        "\n                                                 (3) Show available aps" +
                        "\n                                                 (4) Rent menu" +
                        "\n                                                 (5) Show aps info" +
                        "\n                                                 (6) Place in garage" +
                        "\n                                                 (7) Delete from garage" +
                        "\n                                                 (8) Save" +
                        "\n                                                 (9) Main menu" +
                        "\n                                                 (0) Quit" +
                        "\n\n                                                 Input: ");
    }


    static HashMap<Integer, Apartment> getApartment() {
        HashMap<Integer, Apartment> apartments = new HashMap<>();
        Apartment ap = new Apartment(43, "Konduktorska 26-43");
        apartments.put(ap.getID(), ap);
        ap = new Apartment(54, "Elektoralna 26-62");
        apartments.put(ap.getID(), ap);
        ap = new Apartment(68, "Okopowa 29-25");
        apartments.put(ap.getID(), ap);
        ap = new Apartment(70, "Skisna 1-32");
        apartments.put(ap.getID(), ap);
        ap = new Apartment(35, "Kulparkiwska 95-1");
        apartments.put(ap.getID(), ap);
        ap = new Apartment(22, "Jaracza 11-28");
        apartments.put(ap.getID(), ap);
        ap = new Apartment(40, "Krochmalna 15-25");
        apartments.put(ap.getID(), ap);
        ap = new Apartment(100, "Panska 11-2");
        apartments.put(ap.getID(), ap);
        ap = new Apartment(85, "Zlota 21-15");
        apartments.put(ap.getID(), ap);
        ap = new Apartment(90, "Briuchowycka 192-1");
        apartments.put(ap.getID(), ap);
        return apartments;
    }

    static HashMap<Integer, Person> getPeople() {

        HashMap<Integer, Person> people = new HashMap<>();
        Person p = new Person("Maria", "Tanchyn", 12345678912L, "24/09/2002");
        people.put(p.getId(), p);
        p = new Person("Max", "Dubakov", 42838239238L, "01/02/2002");
        people.put(p.getId(), p);
        p = new Person("Emilia", "Miler", 28387482829L, "13/04/1996");
        people.put(p.getId(), p);
        p = new Person("David", "Known", 27378474598L, "25/10/2000");
        people.put(p.getId(), p);
        p = new Person("Patrycja", "Owczarczyk", 3859726969L, "12/12/2012");
        people.put(p.getId(), p);

        return people;
    }


    static void waitFor() {
        Scanner sc = new Scanner(System.in);

        System.out.print("                                                 ----------------------" +
                "\n                                                 Enter any key to continue: ");
        sc.nextLine();
        System.out.println();
    }


    static void printGreeting() {
        System.out.println(
                "\n\n\n\n\n                     |----------------------------------------------------------------------------|\n" +
                        "                     |                                                                            |" +
                        "\n                     | Hello! Here is my program devoted to house keeping and how YOU can use it. |" +
                        "\n                     |                                                                            |");
        System.out.println(
                "                     | Here are the functions you have:                                           |" +
                        "\n                     |                                                                            |" +
                        "\n" +
                        "                     | 1.     You can choose any person out of these people (worth noting, that   |\n" +
                        "                     |        you will be able to rent, things and so one only if you are         |\n" +
                        "                     |        registered as one of the shown people)                              |" +
                        "\n                     |                                                                            |" +
                        "\n" +
                        "                     | 2.     Get current data for picked user                                    |" +
                        "\n                     |                                                                            |" +
                        "\n" +
                        "                     | 3.     Get currently available apartments                                  |" +
                        "\n                     |                                                                            |" +
                        "\n" +
                        "                     | 4.     Rent a new flat                                                     |" +
                        "\n                     |                                                                            |" +
                        "\n" +
                        "                     | 5.     Pick already rented flat and get info about it                      |" +
                        "\n                     |                                                                            |" +
                        "\n" +
                        "                     | 6.     Place your staff in the flat of parking place if you have one for   |\n" +
                        "                     |        this flat (remember, any flat has its own limited space!)           |" +
                        "\n                     |                                                                            |" +
                        "\n" +
                        "                     | 7.     Delete your staff from the flat and parking places                  |" +
                        "\n                     |                                                                            |" +
                        "\n" +
                        "                     | 8.     Save current state of the property to the file                      |" +
                        "\n                     |                                                                            |" +
                        "\n" +
                        "                     | 9.     Quit the program                                                    |" +
                        "\n                     |                                                                            |" +
                        "\n                     |----------------------------------------------------------------------------|\n\n\n\n");
    }
}
