package System;
import Users.Seller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
/**
 * The PropertyLoader class provides a method to load properties from a file.
 */
public class PropertyLoader {
    /**
     * Loads properties from a specified file and adds them to the PropertySystem.
     *
     * @param filename the name of the file containing property data
     */
    public static void loadPropertiesFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into parts based on the specified delimiters:
                String[] parts = line.split(" Size:| Price:| Users\\.Seller:",4);
                String addressPart = parts[0].replace("System.Address: ", "").trim();
                double size = Double.parseDouble(parts[1].trim());
                double price = Double.parseDouble(parts[2].trim());
                String sellerName = parts[3].trim();
                // Remove parentheses from the address part
                addressPart = addressPart.replace("(", "").replace(")", "");
                String[] addressNumbers = addressPart.split(",");
                int street = Integer.parseInt(addressNumbers[0]);
                int avenue = Integer.parseInt(addressNumbers[1]);
                int[] subUnits = Arrays.stream(Arrays.copyOfRange(addressNumbers, 2, addressNumbers.length))
                        .mapToInt(Integer::parseInt).toArray();
                Seller owner;
                // Check if the seller exists in the system
                if (Seller.sellerExists(sellerName)) {
                    owner = Seller.getSellers(sellerName);
                }else {
                owner = new Seller(sellerName);}
                Address address = new Address(street, avenue, subUnits);
                // Create a new Apartment property with the specified size, price, address, and owner
                Property property = PropertyFactory.createApartment(size, price, address, owner);
                // Add the created property to the PropertySystem
                PropertySystem.getInstance().addProperty(property);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}