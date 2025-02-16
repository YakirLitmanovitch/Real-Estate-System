package System;
import Users.Broker;
import Users.Buyer;
import Users.Observer;
import Users.Seller;

import java.util.ArrayList;
import java.util.List;
/**
 * The PropertyNotifier class implements the Subject interface and is responsible for notifying observers
 * about property-related events.
 */
public class PropertyNotifier implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    /**
     * Registers an observer to receive notifications.
     *
     * @param observer the observer to be registered
     */
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyAllObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
    /**
     * Notifies all registered brokers with a given message.
     *
     * @param message the message to be sent to brokers
     */
    public void notifyBroker(String message) {
        for (Observer observer : observers) {
            if (observer instanceof Broker) {
                observer.update(message);
            }
        }
    }
    /**
     * Notifies a specific seller with a given message.
     *
     * @param message the message to be sent to the seller
     * @param seller the name of the seller to be notified
     */
    public void notifySeller(String message, String seller) {
        for (Observer observer : observers) {
            if (observer instanceof Seller && ((Seller) observer).getName().equals(seller)) {
                observer.update(message);
            }
        }
    }
    /**
     * Notifies all registered buyers with a given message.
     *
     * @param message the message to be sent to buyers
     */
    public void notifyBuyer(String message) {
        for (Observer observer : observers) {
            if (observer instanceof Buyer) {
                observer.update(message);
            }
        }
    }
}