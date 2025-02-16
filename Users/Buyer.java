package Users;
import System.Property;
import System.PropertySystem;

/**
 * The Buyer class represents a buyer user in the property system.
 * A buyer can view properties and receive notifications.
 */
public class Buyer implements User {
    /**
     * Constructs a Buyer object and registers it as an observer in the PropertySystem.
     */
    public Buyer() {
        PropertySystem.getInstance();
        PropertySystem.setNotifier(this);
    }
    /**
     * Views the details of a specified property.
     *
     * @param property the property to be viewed
     */
    @Override
    public void viewProperty(Property property) {
        try {
            if (PropertySystem.getProperties().contains(property)) {
                System.out.println("Viewing property: " + property.getAddress() +
                        " | Property price: " + property.getPrice() + "$ |" +
                        " The size is: " + property.getSize() + " square meters" +
                        " |" + " Property is sold: " + property.isSold() +
                        " |" + " Property can be sold: " + property.canBeSold() + " | " +
                        " Property owner: " + property.getOwner());
            }
            else {
                throw new IllegalStateException("Property not found.");
            }
        }
        catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Updates the buyer with a given message.
     *
     * @param message the message to be received
     */
    @Override
    public void update(String message) {
        System.out.println("Buyer notified: " + message);
    }
}
