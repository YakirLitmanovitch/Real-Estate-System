package Services;

import System.Address;
import System.Property;
import Users.Broker;
/**
 * The MovingService class extends the PropertyDecorator class
 * and represents an additional moving service for a property.
 */
public class MovingService extends PropertyDecorator {
    private static final double price = 500;
    /**
     * Constructs a MovingService object with the specified property.
     *
     * @param property the property to which the moving service is added
     */
    public MovingService(Property property) {
        super(property);
        super.setAdditionalServicesPrice(price);
    }
    public static double getAdditionalServicePrice() {
        return price;
    }
    @Override
    public double getPrice() {
        return super.getPrice() + price; // תוספת של 500$
    }
    public void deleteAdditionalServicesPrice(double price) {
        super.deleteAdditionalServicesPrice(price);
    }

    @Override
    public String getOwner() {
        return null;
    }

    @Override
    public void setAddress(Address address) {
        System.out.println("Irrelevant for moving service.");
    }

    @Override
    public boolean canBeSold() {
        return false;
    }

    @Override
    public void delete() {
        System.out.println("System.Property has been deleted.");
    }
}
