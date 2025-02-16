package Services;

import System.Address;
import System.Property;
import Users.Broker;
/**
 * The PropertyDecorator class is an abstract class that implements the Property interface.
 * It serves as a base class for decorating properties with additional services.
 */
abstract class PropertyDecorator implements Property {
    protected Property property;
    protected double price;

    public PropertyDecorator(Property property) {
        this.property = property;
    }
    /**
     * Deletes the price of the additional services.
     *
     * @param price the price to be deleted
     * @throws IllegalArgumentException if the resulting price is negative
     */
    public void deleteAdditionalServicesPrice(double price) {
        if (property.getAdditionalServicesPrice() - price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        property.setAdditionalServicesPrice( (-2)*price);
    }
    public double getAdditionalServicesPrice() {
        return property.getAdditionalServicesPrice();
    }
    /**
     * Sets the price of the additional services.
     *
     * @param v the new price of the additional services
     */
    public void setAdditionalServicesPrice(double v) {
        property.setAdditionalServicesPrice(v);
    }
    @Override
    public double getSize() { return property.getSize(); }

    @Override
    public double getPrice() { return property.getPrice(); }

    @Override
    public boolean isSold() { return property.isSold(); }

    @Override
    public Address getAddress() { return property.getAddress(); }

    @Override
    public void setPrice(double price) { property.setPrice(price); }

    @Override
    public void markAsSold(Broker broker) { property.markAsSold(broker); }
}