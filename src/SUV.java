public class SUV extends Vehicle {

    private boolean sentDriving;

    public SUV(String name, double surface, double engineCapacity, String engineType,String vehicleType, boolean sentDriving) {
        super(name, surface, engineCapacity, engineType,vehicleType);
        this.sentDriving = sentDriving;
    }


    @Override
    public String toString() {
        return super.toString() +
                ". sentDriving=" + sentDriving +
                '}';
    }
}
