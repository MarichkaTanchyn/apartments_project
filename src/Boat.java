public class Boat extends Vehicle {

    private String typeOfTree;

    public Boat(String name, double surface, double engineCapacity, String vehicleType, String engineType, String typeOfTree) {
        super(name, surface, engineCapacity, engineType,vehicleType);
        this.typeOfTree = typeOfTree;
    }



}
