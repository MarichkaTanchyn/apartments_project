public class Motorcycle extends Vehicle{
    private double wheelDiagonal;

    public Motorcycle(String name, double surface, double engineCapacity, String vehicleType, String engineType, double wheelDiagonal) {
        super(name,surface,engineCapacity,vehicleType,engineType);
        this.wheelDiagonal = wheelDiagonal;
    }


}
