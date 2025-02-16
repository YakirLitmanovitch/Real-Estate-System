package Users;

import Services.CleaningService;
import Services.DesignService;
import Services.GuarantorService;
import Services.MovingService;
import System.Address;
import System.Property;
import System.PropertySystem;
/**
 * The Broker class represents a broker user in the property system.
 * A broker can view, edit, sell properties, update addresses, and manage additional services.
 */
public class Broker implements User {
    /**
     * Constructs a Broker object and registers it as an observer in the PropertySystem.
     */
    public Broker() {
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
     * Edits the price of a specified property and notifies the seller.
     *
     * @param property the property to be edited
     * @param newPrice the new price of the property
     */
    public void editProperty(Property property, double newPrice) {
        property.setPrice(newPrice);
        PropertySystem.getNotifier().notifySeller("Property "+ property.getAddress()+
                " has been updated new price : "+ newPrice + "$", property.getOwner());
        System.out.println("Updated price: " + newPrice);
    }
    /**
     * Sells a specified property and notifies all observers.
     *
     * @param property the property to be sold
     */
    public void sellProperty(Property property) {
        try {
            if (property.isSold()) {
                throw new IllegalStateException("Property is already sold.");
            }
            if (property.canBeSold()) {
                property.markAsSold(this);
                PropertySystem.getNotifier().notifyAllObservers("Property "+ property.getAddress()+" has been sold.");
            }
            else {
                throw new IllegalStateException("Cannot sell property at " + property.getAddress() +
                        " because of conflicting status with parent/child properties.");
            }
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Updates the address of a specified property and notifies all observers.
     *
     * @param property the property to be updated
     * @param newAddress the new address of the property
     */
    public void updateAddress(Property property, Address newAddress) {
        property.setAddress(newAddress);
        PropertySystem.getNotifier().notifyAllObservers("Property "+ property.getAddress()+
                " has been updated new address: " + newAddress);
    }
    /**
     * Prints the total price of a sold property including additional services.
     *
     * @param property the property to be checked
     */
    public void soldFor(Property property) {
        System.out.println("Property sold for: " +
                (property.getPrice()+ property.getAdditionalServicesPrice()) + "$");
    }
    /**
     * Updates the broker with a given message.
     *
     * @param message the message to be received
     */
    @Override
    public void update(String message) {
        System.out.println("Broker notified: " + message);
    }
    /**
     * Adds a specified service to a sold property and notifies the buyer.
     *
     * @param property the property to which the service will be added
     * @param service the service to be added
     */
    public void addService(Property property, String service) {
        try {
            if (property.isSold()) {
                switch (service) {
                    case "design":{
                        new DesignService(property);
                        PropertySystem.getNotifier().notifyBuyer
                                ("Design service added to property "+ property.getAddress());
                        break;
                    }
                    case "moving" :{
                        new MovingService(property);
                        PropertySystem.getNotifier().notifyBuyer
                                ("Moving service added to property "+ property.getAddress());
                        break;
                    }
                    case "cleaning" :{
                        new CleaningService(property);
                        PropertySystem.getNotifier().notifyBuyer
                                ("Cleaning service added to property "+ property.getAddress());
                        break;
                    }
                    case "guarantor" :{
                        new GuarantorService(property);
                        PropertySystem.getNotifier().notifyBuyer
                                ("Guarantor service added to property "+ property.getAddress());
                        break;
                    }
                    default :
                        System.out.println("Service not available.");
                }
            } else {
                throw new IllegalStateException("Cannot add services to a property that is not sold.");
            }
        }
        catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Deletes a specified additional service from a property and notifies the buyer.
     *
     * @param property the property from which the service will be deleted
     * @param service the service to be deleted
     */
    public void deleteAdditionalService(Property property, String service) {
        switch (service) {
            case "design" :{
                new DesignService(property).deleteAdditionalServicesPrice
                        (DesignService.getAdditionalServicePrice());
                PropertySystem.getNotifier().notifyBuyer
                        ("Design service removed from property "+ property.getAddress());
                break;
            }
            case "moving" :{
                new MovingService(property).deleteAdditionalServicesPrice
                        (MovingService.getAdditionalServicePrice());
                PropertySystem.getNotifier().notifyBuyer
                        ("Moving service removed from property "+ property.getAddress());
                break;
            }
            case "cleaning" :{
                new CleaningService(property).deleteAdditionalServicesPrice
                        (CleaningService.getAdditionalServicePrice());
                PropertySystem.getNotifier().notifyBuyer
                        ("Cleaning service removed from property "+ property.getAddress());
                break;
            }
            case "guarantor" :{
                new GuarantorService(property).deleteAdditionalServicesPrice
                        (GuarantorService.getAdditionalServicePrice());
                PropertySystem.getNotifier().notifyBuyer
                        ("Guarantor service removed from property "+ property.getAddress());
                break;
            }
            default :
                System.out.println("Service not available.");
        }
    }
}