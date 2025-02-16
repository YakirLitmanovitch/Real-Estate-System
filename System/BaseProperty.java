package System;
import Users.Broker;
import Users.Seller;
/**
 * The BaseProperty class is an abstract class that implements the Property interface.
 * It serves as a base class for different types of properties.
 */
public abstract class BaseProperty implements Property {
    private double size;
    private double price;
    private boolean isSold;
    private Address address;
    private final Seller owner;
    private double additionalServicesPrice;
    public BaseProperty(double size, double price, Address address, Seller owner) {
        this.size = size;
        this.price = price;
        this.address = address;
        this.isSold = false;
        this.owner = owner;
        this.additionalServicesPrice = 0;
    }
    /**
     * Sets the price of the additional services.
     *
     * @param additionalServicesPrice the new price of the additional services
     */
    public void setAdditionalServicesPrice(double additionalServicesPrice) {
        this.additionalServicesPrice += additionalServicesPrice;
    }
    public double getAdditionalServicesPrice() {
        return additionalServicesPrice;
    }
    @Override
    public double getSize() { return size; }
    @Override
    public double getPrice() { return price; }
    @Override
    public boolean isSold() { return isSold; }
    @Override
    public Address getAddress() { return address; }
    @Override
    public String getOwner() { return owner.getName(); }
    @Override
    public void setPrice(double price) { this.price = price; }
    /**
     * Marks the property as sold by the specified broker.
     *
     * @param broker the broker who sold the property
     */
    @Override
    public void markAsSold(Broker broker) {
        if (canBeSold()) {
            this.isSold = true;
            System.out.println("Property at " + address + " has been sold.");
        } else {
            System.out.println("Cannot sell property at " + address + " because of conflicting status with parent/child properties.");
        }
    }
    @Override
    public void setAddress(Address address) { this.address = address; }
    /**
     * Checks if the property can be sold.
     *
     * @return true if the property can be sold, false otherwise
     */
    @Override
    public boolean canBeSold() {
        PropertySystem.getInstance();
        for (Property property : PropertySystem.getProperties()) {
            if (property.isSold()) {
                if (this.getAddress().isSubAddressOf(property.getAddress()) ||
                        property.getAddress().isSubAddressOf(this.getAddress())) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Deletes the property from the property system.
     */
    @Override
    public void delete() {
        PropertySystem.getInstance().removeProperty(this);
        System.out.println("Property at " + address + " has been deleted.");
    }
}
