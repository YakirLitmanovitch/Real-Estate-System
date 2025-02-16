import Users.Broker;
import Users.Buyer;
import Users.Seller;
import System.Property;
import System.PropertyLoader;
import System.PropertySystem;

public class Main {
    public static void main(String[] args) {
        PropertySystem system = PropertySystem.getInstance();

        Buyer buyer = new Buyer();
        Broker broker = new Broker();

        //New sellers
        Seller John = new Seller("John");
        Seller Alise = new Seller("Alise");
        Seller Abram = new Seller("Abram");
        Seller Ben = new Seller("Ben");

        PropertyLoader.loadPropertiesFromFile
                ("C:\\Users\\yakir\\IdeaProjects\\RealEstateSystem\\src\\properties.txt");

        for (Property property : PropertySystem.getProperties()) {
            System.out.println("Loaded property: " + property.getAddress() + ", Price: " + property.getPrice());
        }
        //New properties from the file
        Property apt1 = PropertySystem.getProperties().get(0);
        Property apt2 = PropertySystem.getProperties().get(1);
        Property apt3 = PropertySystem.getProperties().get(2);

        Abram.viewProperty(apt1); //Abram is trying to view the property - successfully because it is his property
        John.viewProperty(apt1); //John is trying to view the property - unsuccessfully because it is not his property
        buyer.viewProperty(apt2); //Buyer is trying to view the property - successfully because buyer can view any property
        broker.viewProperty(apt1); //Broker is trying to view the property - successfully because broker can view any property

        broker.editProperty(apt1, 320000); //Broker is trying to edit the property - successfully because he is the broker

        broker.sellProperty(apt1);
        broker.addService(apt1, "cleaning");//Broker is trying to add a service - successfully because he is the broker
        broker.addService(apt1, "design");
        broker.deleteAdditionalService(apt1, "cleaning");//Broker is trying to delete a service - successfully
        broker.soldFor(apt1);//Broker is trying to see the price of the property after the service was added
        broker.sellProperty(apt1);//Broker is trying to sell the property - unsuccessfully because it is already sold

        broker.addService(apt2, "cleaning");//Broker is trying to add a service - unsuccessfully because the property is not sold

        buyer.viewProperty(apt3);
        Alise.deleteProperty(apt3); // successfully because it is her property
        John.deleteProperty(apt3); // unsuccessfully because it is not his property
        buyer.viewProperty(apt3);

        broker.viewProperty(apt1);
        Alise.deleteProperty(apt1); // unsuccessfully because it is not her property

//      // Strategy Pattern examples for the PropertySystem
        system.getPropertiesByPrice(apt1.getAddress(), 1, 15000, "less");

        system.getPropertiesByPrice(apt1.getAddress(), 2, 1000, "greater");

        system.getAvailablePropertiesInRadius(apt1.getAddress(), 1);

        system.getSoldPropertiesInRadius(apt1.getAddress(), 3);

        system.getAveragePriceInRadius(apt1.getAddress(), 3);

    }
}
