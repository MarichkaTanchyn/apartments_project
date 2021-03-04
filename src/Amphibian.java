public class Amphibian extends Vehicle{

    private String mixtureOf;

    public Amphibian(String name, double surface, double engineCapacity, String vehicleType, String engineType,String mixtureOf) {
    super(name,surface,engineCapacity,vehicleType,engineType);
        this.mixtureOf = mixtureOf;
    }


}
