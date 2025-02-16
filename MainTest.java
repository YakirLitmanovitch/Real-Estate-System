import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import System.Property;
import System.PropertyFactory;
import System.PropertySystem;
import System.Address;
import Users.Seller;
import Users.Buyer;
import Users.Broker;
import System.PropertyLoader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {

    private static PropertySystem system;
    private static Seller yakir;
    private static Seller tal;
    private static Seller oded;
    private static Buyer buyer;
    private static Broker broker;
    private static Property apt1;
    private static Property apt2;
    private static Property apt3;

    @BeforeAll
    public static void setUp() {
        system = PropertySystem.getInstance();
        yakir = new Seller("Yakir");
        tal = new Seller("Tal");
        oded = new Seller("Oded");
        buyer = new Buyer();
        broker = new Broker();

        Address address1 = new Address(1, 1);
        Address address2 = new Address(2, 2);
        Address address3 = new Address(3, 3);

        apt1 = PropertyFactory.createApartment(80, 300000, address1, oded);
        apt2 = PropertyFactory.createApartment(100, 400000, address2, tal);
        apt3 = PropertyFactory.createApartment(120, 500000, address3, yakir);

        system.addProperty(apt1);
        system.addProperty(apt2);
        system.addProperty(apt3);
    }

    @Test
    public void sellerCanViewOwnProperty() {
        assertDoesNotThrow(() -> oded.viewProperty(apt1));
    }

    @Test
    public void sellerCannotViewOthersProperty() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        yakir.viewProperty(apt1);
        String output = outContent.toString();
        assertTrue(output.contains("Cannot view property: You are not the owner."));

    }

    @Test
    public void buyerCanViewAnyProperty() {
        assertDoesNotThrow(() -> buyer.viewProperty(apt2));
    }

    @Test
    public void brokerCanViewAnyProperty() {
        assertDoesNotThrow(() -> broker.viewProperty(apt1));
    }

    @Test
    public void brokerCanEditProperty() {
        broker.editProperty(apt1, 320000);
        assertEquals(320000, apt1.getPrice());
    }

    @Test
    public void brokerCanSellProperty() {
        broker.sellProperty(apt1);
        assertTrue(apt1.isSold());
    }

    @Test
    public void brokerCanAddService() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        broker.addService(apt1, "cleaning");
        String output = outContent.toString();
        assertTrue(output.contains("Cleaning service added to property"));
    }

    @Test
    public void brokerCanDeleteService() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        broker.addService(apt1, "cleaning");
        broker.deleteAdditionalService(apt1, "cleaning");        String output = outContent.toString();
        assertTrue(output.contains("Cleaning service removed from property"));
    }

    @Test
    public void sellerCanDeleteOwnProperty() {
        assertDoesNotThrow(() -> tal.deleteProperty(apt2));
    }

    @Test
    public void sellerCannotDeleteOthersProperty() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        yakir.deleteProperty(apt2);
        String output = outContent.toString();
        assertTrue(output.contains("Cannot delete property: You are not the owner."));
    }

    @Test
    public void getPropertiesByPriceLessThan() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        system.getPropertiesByPrice(apt1.getAddress(), 1, 15000, "less");
        String output = outContent.toString();
        assertTrue(output.contains("(1,1)"));
    }
    @Test
    public void propertiesLoadedFromFile() {
        PropertyLoader.loadPropertiesFromFile("C:\\Users\\yakir\\IdeaProjects\\RealEstateSystem\\src\\properties.txt");
        assertFalse(PropertySystem.getProperties().isEmpty());
    }
    @Test
    public void getAveragePriceInRadius() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        system.getAveragePriceInRadius(apt1.getAddress(), 3);
        String output = outContent.toString();
        assertTrue(output.contains("7222"));
    }
    @Test
    public void getSoldPropertiesInRadius() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        broker.sellProperty(apt1);
        String output = outContent.toString();
        assertTrue(output.contains("(1,1)"));
    }

}