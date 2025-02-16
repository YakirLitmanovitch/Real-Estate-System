package Users;
import System.Property;
/**
 * The User interface represents a user in the property system.
 * It extends the Observer interface and requires implementing the viewProperty method.
 */
public interface User extends Observer {
    void viewProperty(Property property);
}
