package Services;

import System.Address;
import System.Property;
/**
 * The GuarantorService class extends the PropertyDecorator class
 * and represents an additional guarantor service for a property.
 */
public class GuarantorService extends PropertyDecorator {
    private static final double price = 1000;
    /**
     * Constructs a GuarantorService object with the specified property.
     *
     * @param property the property to which the guarantor service is added
     */
    public GuarantorService(Property property) {
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
        System.out.println("Irrelevant for guarantor service.");
    }

    @Override
    public boolean canBeSold() {
        return false;
    }

    @Override
    public void delete() {
        System.out.println("Irrelevant for guarantor service.");
    }
}
