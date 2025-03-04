package Searching;

import System.Property;

import java.util.ArrayList;
import java.util.List;
/**
 * The SearchByStatus class implements the SearchStrategy interface
 * and provides a way to search for properties based on their sellable status.
 */
public class SearchByStatus implements SearchStrategy {
    private boolean canBeSold;

    public SearchByStatus(boolean canBeSold) {
        this.canBeSold = canBeSold;
    }
    /**
     * Searches for properties in the given list that match the sellable status criteria.
     *
     * @param properties the list of properties to search
     * @return a list of properties that match the sellable status criteria
     */
    @Override
    public List<Property> search(List<Property> properties) {
        List<Property> result = new ArrayList<>();
        for (Property property : properties) {
            if (property.canBeSold() == canBeSold && !property.isSold()) {
                result.add(property);
            }
        }
        return result;
    }
}
