package Searching;

import System.Property;

import java.util.ArrayList;
import java.util.List;
/**
 * The SearchBySold class implements the SearchStrategy interface
 * and provides a way to search for properties based on their sold status.
 */
public class SearchBySold implements SearchStrategy {
    private boolean isSold;

    public SearchBySold(boolean isSold) {
        this.isSold = isSold;
    }
    /**
     * Searches for properties in the given list that match the sold status criteria.
     *
     * @param properties the list of properties to search
     * @return a list of properties that match the sold status criteria
     */
    @Override
    public List<Property> search(List<Property> properties) {
        List<Property> result = new ArrayList<>();
        for (Property property : properties) {
            if (property.isSold() == isSold) {
                result.add(property);
            }
        }
        return result;
    }
}