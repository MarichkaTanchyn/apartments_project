public class TooManyThingsException extends Exception {
    TooManyThingsException(){
        super("Remove some old items to insert a new item");
    }
}
