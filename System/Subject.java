package System;

import Users.Observer;
/**
 * The Subject interface defines the methods that a subject (observable) must implement
 * to allow observers to register, deregister, and receive notifications.
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyAllObservers(String message);
}
