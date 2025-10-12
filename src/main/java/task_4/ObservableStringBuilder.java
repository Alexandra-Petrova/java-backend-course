package task_4;

import java.util.ArrayList;

public class ObservableStringBuilder implements Subject {
    private final StringBuilder stringBuilder;
    private final ArrayList<Observer> observers;

    public ObservableStringBuilder() {
        stringBuilder = new StringBuilder();
        observers = new ArrayList<>();
    }

    public ObservableStringBuilder append(String userString) {
        stringBuilder.append(userString);
        notifyObservers();
        return this;
    }

    public ObservableStringBuilder insert(int index, String userString) {
        stringBuilder.insert(index, userString);
        notifyObservers();
        return this;
    }

    public ObservableStringBuilder delete(int start, int end) {
        stringBuilder.delete(start, end);
        notifyObservers();
        return this;
    }

    public ObservableStringBuilder replace(int start, int end, String newString) {
        stringBuilder.replace(start, end, newString);
        notifyObservers();
        return this;
    }

    public int length() {
        return stringBuilder.length();
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        String currentString = stringBuilder.toString();
        for (Observer o : observers) {
            o.update(currentString);
        }
    }
}

