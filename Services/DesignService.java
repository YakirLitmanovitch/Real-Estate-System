package Services;
import System.Address;
import System.Property;
/**
 * The CleaningService class extends the PropertyDecorator class
 * and represents an additional cleaning service for a property.
 */
public class DesignService extends PropertyDecorator {
    private static final double price = 1000;
    /**
     * Constructs a DesignService object with the specified property.
     *
     * @param property the property to which the design service is added
     */
    public DesignService(Property property) {
        super(property);
        super.setAdditionalServicesPrice(price);
    }
    public static double getAdditionalServicePrice() {
        return price;
    }
    @Override
    public double getPrice() {
        return price;
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
        System.out.println("Irrelevant for design service.");
    }

    @Override
    public boolean canBeSold() {
        return false;
    }

    @Override
    public void delete() {
        System.out.println("Irrelevant for design service.");
    }
}


