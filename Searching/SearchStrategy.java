package Searching;

import System.Property;

import java.util.List;
/**
 * The SearchStrategy interface provides a method for searching properties
 * based on different criteria.
 */
public interface SearchStrategy {
    List<Property> search(List<Property> properties);

}
