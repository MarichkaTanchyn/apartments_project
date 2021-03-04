class Placeable {

    private double square;
    private String name;
    private final int id = (int) (Math.random() * 10000.0);

    public Placeable(double square, String name) {
        this.square = square;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getSquare() {
        return square;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "(" + id + ") " + name + " " + square + "m";
    }
}
