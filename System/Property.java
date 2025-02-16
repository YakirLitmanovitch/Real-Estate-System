package System;
import Users.Broker;
/**
 * The Property interface defines the methods that must be implemented by any class
 * that represents a property in the system.
 */
public interface Property {
    double getSize();
    double getPrice();
    double getAdditionalServicesPrice();
    boolean isSold();
    Address getAddress();
    String getOwner();
    void setPrice(double price);
    void markAsSold(Broker broker);
    void setAddress(Address address);
    boolean canBeSold();
    void delete();
    void setAdditionalServicesPrice(double v);
}

