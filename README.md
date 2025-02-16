# Real Estate System

## Overview
The Real Estate System is a Java-based application that simulates a property management system. It allows different types of users (buyers, sellers, brokers) to interact with properties, view details, edit information, and manage additional services.

## Functionality
- **Property Management**: Create, view, edit, and delete properties.
- **User Management**: Different user roles (Buyer, Seller, Broker) with specific permissions.
- **Notification System**: Observers are notified of changes in the property system.
- **Additional Services**: Manage additional services (design, moving, cleaning, guarantor) for properties.

## Object-Oriented Programming (OOP) Principles

1. **Encapsulation**:
   - Properties and methods are encapsulated within classes like `Property`, `Buyer`, `Seller`, and `Broker`.
      This ensures that the internal state of objects is protected and can only be modified through well-defined interfaces.

2. **Inheritance**:
   - Classes such as `Buyer`, `Seller`, and `Broker` implement the `User` interface.
      This allows these classes to inherit common behavior defined in the `User` interface while also providing their specific implementations.

3. **Polymorphism**:
   - Methods like `viewProperty` are overridden in different user classes (`Buyer`, `Seller`, `Broker`).
      This allows different types of users to interact with properties in a way that is specific to their role, while still using a common interface.

4. **Abstraction**:
   - Interfaces like `User` and `Observer` provide abstract methods that are implemented by concrete classes.
      This allows the system to define common behaviors that must be implemented by any class that adheres to these interfaces,
         promoting a clear contract for what functionalities are expected.

## SOLID Principles

1. **Single Responsibility Principle**: Each class in the project has a single responsibility.
      For example, the `Seller` class manages seller-specific actions,
      ensuring that each class has a clear and focused purpose.

2. **Open/Closed Principle**: The classes are designed to be open for extension but closed for modification.
      This means you can add new functionalities, such as new services, without modifying existing code.
      For instance, you can add new services to properties without changing the core property management logic.

3. **Liskov Substitution Principle**: Subtypes in the project can replace their base types without altering the correctness of the program.
      For example, classes like `Buyer`, `Seller`, and `Broker` implement the `User` interface,
      allowing them to be used interchangeably where a `User` type is expected.

4. **Interface Segregation Principle**: Interfaces in the project are specific to what clients need.
      For example, the `User` and `Observer` interfaces define specific methods that are implemented by concrete classes,
      ensuring that classes only implement methods they actually use.

5. **Dependency Inversion Principle**: High-level modules in your project do not depend on low-level modules but on abstractions.
      For example, the `PropertySystem` class depends on the `SearchStrategy` interface rather than concrete search implementations,
      allowing for flexible and interchangeable search strategies.

## Design Patterns
1. **Observer Pattern**: Used for the notification system where `User` classes implement the `Observer` interface to receive updates.
   - **Example**: The `Seller` class implements the `Observer` interface and overrides the `update` method to receive notifications.
   ```java
   public class Seller implements Observer {
       @Override
       public void update(String message) {
           System.out.println("Seller notified: " + message);
       }
   }
   
2. **Factory Pattern**:
   PropertyFactory is used to create property objects.  
   **Example**: The `PropertyFactory` class provides a method to create an `Apartment` object.
      ```java
      public class PropertyFactory {
          public static Property createApartment(double size, double price, Address address, Seller owner) {
              PropertySystem.getNotifier().notifyBroker("Apartment created");
              return new Apartment(size, price, address, owner);
          }
      }

3. **Singleton Pattern**:
   PropertySystem ensures a single instance of the property system. 
   **Example**: The `PropertySystem` class uses a private constructor and a static method to ensure only one instance exists.
      ```java
      public class PropertySystem {
          private static PropertySystem instance;
   
          private PropertySystem() {}
   
          public static PropertySystem getInstance() {
              if (instance == null) {
                  instance = new PropertySystem();
              }
              return instance;
          }
      }

4. **Strategy Pattern**:
   Used in PropertySystem for different property search strategies. 
   Example: The `PropertySystem` class can use different strategies to search for properties based on price or location.
      ```java
      public class PropertySystem {
          public List<Property> getPropertiesByPrice(String address, int radius, double price, String comparison) {
              // Implementation of strategy to get properties by price
          }
   
          public List<Property> getAvailablePropertiesInRadius(String address, int radius) {
              // Implementation of strategy to get available properties in radius
          }
      }

5. **Decorator Pattern**:
   Additional services (design, moving, cleaning, guarantor) can be added to properties dynamically. 
   Example: The `Broker` class can add or remove additional services to a property.
      ```java
      public class Broker {
          public void addService(Property property, String service) {
              // Implementation to add service to property
          }
   
          public void deleteAdditionalService(Property property, String service) {
              // Implementation to remove service from property
          }
      }

### Code Structure and Functionality
- **Main.java**:  Entry point of the application. Initializes the system, loads properties, and demonstrates various user interactions.
- **Users Package**: Contains user-related classes (`Buyer`, `Seller`, `Broker`) and interfaces (`User`, `Observer`).
- **System Package**: Contains system-related classes (`Property`, `PropertySystem`, `PropertyFactory`, `Address`).
- **Services Package**: Contains classes for additional services (`DesignService`, `MovingService`, `CleaningService`, `GuarantorService`).
- **MainTest.java**: Unit tests for the main functionalities of the system.

### Key Classes and Methods
**PropertySystem**: Manages properties and notifications.
      *`getInstance()`: Returns the singleton instance.
      *`setNotifier()`: Registers an observer.
      *`notifyAllObservers()`: Notifies all registered observers.
      *`addProperty()`: Adds a property manually to the system.
      
**Property**: Represents a property with attributes like size, price, address, owner.
      *`setPrice()`: Sets the property price.
      *`markAsSold()`: Marks the property as sold.

**Buyer**: Implements `User` interface, can view properties.

**Seller**: Implements `User` interface, can view and delete owned properties.
      *`deleteProperty()`: Deletes a property if the seller is the owner.

**Broker**: Implements `User` interface, can view, edit, sell properties, and manage additional services.
      *`editProperty()`: Edits the property price.
      *`sellProperty()`: Sells a property.
      *`addService()`: Adds a service to a sold property.
      *`deleteAdditionalService()`: Deletes an additional service from a property.
