package Users;
import System.Property;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import System.PropertySystem;
/**
 * The Seller class represents a seller user in the property system.
 * A seller can view and delete properties they own, and receive notifications.
 */
public class Seller implements User {
    private static final List<Seller> sellers = new ArrayList<>();
    private final String name;
    /**
     * Checks if a seller with the given name already exists.
     *
     * @param name the name of the seller to check
     * @return true if the seller exists, false otherwise
     */
    public static boolean sellerExists(String name) {
        for (Seller seller : sellers) {
            if (seller.name.equals(name)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Constructs a Seller object and registers it as an observer in the PropertySystem.
     *
     * @param name the name of the seller
     * @throws IllegalArgumentException if a seller with the given name already exists
     */
    public Seller(String name) {
        if (sellerExists(name)) {
            throw new IllegalArgumentException("Seller with name " + name + " already exists");
        }
        this.name = name;
        sellers.add(this);
        PropertySystem.getInstance();
        PropertySystem.setNotifier(this);
    }
    public static Seller getSellers(String name) {
        for (Seller seller : sellers) {
            if (Objects.equals(seller.getName(), name)) {
                return seller;
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }
    /**
     * Views the details of a specified property if the seller is the owner.
     *
     * @param property the property to be viewed
     */
    @Override
    public void viewProperty(Property property) {
        try {
            if (Objects.equals(property.getOwner(), this.name)&&
                PropertySystem.getProperties().contains(property)) {
                System.out.println("Viewing property: " + property.getAddress() +
                        " | Property price: " + property.getPrice() + "$ |" +
                        " The size is: "+property.getSize() + " square meters" +
                        " |" + " Property is sold: " + property.isSold());
            }
            else {
                throw new IllegalStateException("Cannot view property: You are not the owner.");
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Deletes a specified property if the seller is the owner and notifies the broker.
     *
     * @param property the property to be deleted
     */
    public void deleteProperty(Property property) {
        try {
            if (Objects.equals(property.getOwner(), this.name)) {
                property.delete();
                PropertySystem.getNotifier().notifyBroker("Property "+ property.getAddress()+" has been deleted.");
            }
            else {
                throw new IllegalStateException("Cannot delete property: You are not the owner.");
                  }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Updates the seller with a given message.
     *
     * @param message the message to be received
     */
    @Override
    public void update(String message) {
        System.out.println("Seller notified: " + message);
    }
}