package Services;
import System.Address;
import System.Property;
/**
 * The CleaningService class extends the PropertyDecorator class
 * and represents an additional cleaning service for a property.
 */
public class CleaningService extends PropertyDecorator {
    private static final double price = 200;
    /**
     * The CleaningService class extends the PropertyDecorator class
     * and represents an additional cleaning service for a property.
     */
    public CleaningService(Property property) {
        super(property);
        super.setAdditionalServicesPrice(price);
    }
    public static double getAdditionalServicePrice() {
        return price;
    }
    @Override
    public double getPrice() {
        return super.getPrice() + price;
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
        System.out.println("Property address updated to: " + address);
    }

    @Override
    public boolean canBeSold() {
        return false;
    }

    @Override
    public void delete() {
        System.out.println("Irrelevant for cleaning service.");
    }
}
