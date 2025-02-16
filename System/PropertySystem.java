package System;

import Searching.SearchByPrice;
import Searching.SearchBySold;
import Searching.SearchByStatus;
import Searching.SearchStrategy;
import Users.User;

import java.util.ArrayList;
import java.util.List;
/**
 * The PropertySystem class manages a collection of properties and provides methods to interact with them.
 */
public class PropertySystem {
    private static PropertySystem instance;
    private static PropertyNotifier notifier;
    private static List<Property> properties;
    /**
     * Private constructor to prevent instantiation.
     * Initializes the properties list.
     */
    private PropertySystem() {
        properties = new ArrayList<>();
    }
    /**
     * Returns the singleton instance of the PropertySystem.
     * Initializes the notifier if the instance is created.
     *
     * @return the singleton instance of the PropertySystem
     */
    public static PropertySystem getInstance() {
        if (instance == null) {
            instance = new PropertySystem();
            notifier = new PropertyNotifier();
        }
        return instance;
    }
    public static PropertyNotifier getNotifier() {
        return notifier;
    }
    public static void setNotifier(User user) {
        PropertySystem.notifier.registerObserver(user);
    }
    public static List<Property> getProperties() {
        return properties;
    }
    public void addProperty(Property property) {
        properties.add(property);
    }
    public void removeProperty(Property property) {
        properties.remove(property);
    }
    /**
     * Calculates and prints the average price per square meter of properties within a given radius.
     *
     * @param center the center address
     * @param radius the radius in which to search for properties
     */
    public void getAveragePriceInRadius(Address center, int radius) {
        double total = 0;
        double count = 0;
        for (Property property : properties) {
            if (isWithinRadius(center, property.getAddress(), radius)) {
                total += property.getPrice();
                count += property.getSize();
            }
        }
        System.out.println("Average price for square meter in this radius: " +
                (count == 0 ? 0 : total / count) +"$");
    }
    /**
     * Finds and prints available properties within a given radius.
     *
     * @param center the center address
     * @param radius the radius in which to search for properties
     */
    public void getAvailablePropertiesInRadius(Address center, int radius) {
        List<Property> result = new ArrayList<>();
        for (Property property : properties) {
            if (isWithinRadius(center, property.getAddress(), radius)) {
                result.add(property);
            }
        }
        result = new SearchByStatus(true).search(result);
        System.out.println("Available Properties:");
        for (Property p : result) {
            System.out.println(p.getAddress());
        }
    }
    /**
     * Finds and prints sold properties within a given radius.
     *
     * @param center the center address
     * @param radius the radius in which to search for properties
     */
    public void getSoldPropertiesInRadius(Address center, int radius) {
        List<Property> result = new ArrayList<>();
        for (Property property : properties) {
            if (isWithinRadius(center, property.getAddress(), radius)) {
                result.add(property);
            }
        }
        result = new SearchBySold(true).search(result);
        System.out.println("Sold Properties:");
        for (Property p : result) {
            System.out.println(p.getAddress());
        }
    }
    /**
     * Finds and prints properties within a given radius that match the specified price criteria.
     *
     * @param center the center address
     * @param radius the radius in which to search for properties
     * @param price the price to compare
     * @param type the type of comparison (e.g., "greater", "less")
     */
    public void getPropertiesByPrice(Address center, int radius, double price, String type) {
        List<Property> result = new ArrayList<>();
        for (Property property : properties) {
            if (isWithinRadius(center, property.getAddress(), radius)) {
                result.add(property);
            }
        }
        result = new SearchByPrice(price, type).search(result);
        System.out.println("Properties " + type +" than " + price + "$ per square meter:");
        for (Property p : result) {
            System.out.println(p.getAddress() + " | Price: " + p.getPrice() + "$ |" +
                    " Price for square meter: " + p.getPrice()/p.getSize() + "$");
        }
    }
    /**
     * Checks if a target address is within a given radius of a center address.
     *
     * @param center the center address
     * @param target the target address
     * @param radius the radius to check
     * @return true if the target address is within the radius, false otherwise
     */
    private boolean isWithinRadius(Address center, Address target, int radius) {
        int dx = Math.abs(center.getStreet() - target.getStreet());
        int dy = Math.abs(center.getAvenue() - target.getAvenue());
        return dx <= radius && dy <= radius;
    }
    public List<Property> searchProperties(SearchStrategy strategy) {
        return strategy.search(properties);
    }
}