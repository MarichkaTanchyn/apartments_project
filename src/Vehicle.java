abstract class Vehicle extends Placeable {

    private String vehicleType;
    private double engineCapacity;
    private String engineType;


    public Vehicle(String name, double surface, double engineCapacity, String vehicleType, String engineType) {
        super(surface, name);
        this.vehicleType = vehicleType;
        this.engineCapacity = engineCapacity;
        this.engineType = engineType;
    }


    @Override
    public String toString() {
        return vehicleType + " (" + getId() + ") {" +
                "name='" + getName() + '\'' +
                ", surface=" + getSquare() +
                ", engineCapacity=" + engineCapacity +
                ", engineType='" + engineType + '\'';
    }
}
