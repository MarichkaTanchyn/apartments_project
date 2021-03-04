import java.util.ArrayList;

public class ParkingPlace {

    private double square;
    private final int id = (int) (Math.random() * 10000.0);
    private ArrayList<Placeable> things;

    public ParkingPlace() {
        this.square = 10;
        things = new ArrayList<>();

    }

    void place(Placeable thing) throws TooManyThingsException {
        if (square - thing.getSquare() < 0){
            throw new TooManyThingsException();
        }

        square -= thing.getSquare();
        things.add(thing);

    }

    void delete(Placeable thing){

        square += thing.getSquare();
        things.remove(thing);
    }

    ArrayList<Placeable> getThings() {
        return things;
    }

    public double getSquare() {
        return square;
    }
}
