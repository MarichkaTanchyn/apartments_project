public interface Rentable {

    void rent(Person p, int numberOfDays);
    void cancelRent(boolean rightNow);

}
