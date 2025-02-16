package System;
import Users.Seller;
/**
 * The PropertyFactory class provides a method to create different types of properties.
 */
public class PropertyFactory {
    /**
     * Creates an Apartment object with the specified size, price, address, and owner.
     * Notifies the broker that an apartment has been created.
     *
     * @param size the size of the apartment
     * @param price the price of the apartment
     * @param address the address of the apartment
     * @param owner the owner of the apartment
     * @return the created Apartment object
     */
    public static Property createApartment(double size, double price, Address address, Seller owner) {
        PropertySystem.getNotifier().notifyBroker("Apartment created");
        return new Apartment(size, price, address, owner);
    }
}