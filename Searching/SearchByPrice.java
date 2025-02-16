package Searching;

import System.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * The SearchByPrice class implements the SearchStrategy interface
 * and provides a way to search for properties based on their price per size.
 */
public class SearchByPrice implements SearchStrategy {
    private double price;
    private String type;


    public SearchByPrice(double price,String type) {
        this.price = price;
        this.type = type;
    }
    /**
     * Searches for properties in the given list that match the price criteria.
     *
     * @param properties the list of properties to search
     * @return a list of properties that match the price criteria("greater", "less", or "equal")
     */
    @Override
    public List<Property> search(List<Property> properties) {
        List<Property> result = new ArrayList<>();
        for (Property property : properties) {
            if (type.equals("greater") && property.getPrice()/property.getSize() >= price) {
                result.add(property);
            } else if (type.equals("less") && property.getPrice()/property.getSize() <= price) {
                result.add(property);
            } else if (type.equals("equal") && property.getPrice()/property.getSize() == price) {
                result.add(property);
            }
        }
        return result;
    }
}