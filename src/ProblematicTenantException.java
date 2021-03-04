
import java.util.Arrays;

public class ProblematicTenantException extends Exception {
    ProblematicTenantException(Person person, String[] owns) {
        super("Person " + person.getName() + " " + person.getSurname() + " already had rent of apartments: " + Arrays.toString(owns));
    }
}
