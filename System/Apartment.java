package System;

import Users.Broker;
import Users.Seller;
/**
 * The Apartment class extends the BaseProperty class
 * and represents an apartment property.
 */
public class Apartment extends BaseProperty {
    public Apartment(double size, double price, Address address, Seller owner) {
        super(size, price, address, owner);
    }
    @Override
    public void markAsSold(Broker broker) {
        super.markAsSold(broker);
    }
}