package at.ac.fhcampuswien.fhmdb.observer;

import java.util.ArrayList;
import java.util.List;

public interface Observable {
    // all observables implement same list
    List<Observer> observers = new ArrayList<>();
    void addObserver(Observer observer);
    void updateObserver(ObservableMessages message);
}
