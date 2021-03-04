public class CityCar extends Vehicle {
    private String carcassType;


    public CityCar(String name, double surface, double engineCapacity,String vehicleType, String engineType,String carcassType) {
        super(name, surface, engineCapacity, engineType,vehicleType);
        this.carcassType =carcassType;
    }
}
