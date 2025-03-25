package observable;

import NotifyingObserver.NotifyingObserver;

import java.util.ArrayList;
import java.util.List;

public class MobilePhoneObservable implements StockObservable{
    List<NotifyingObserver> observers = new ArrayList<>();
    int stockCount = 0;

    @Override
    public void addObserver(NotifyingObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (var observer: observers) {
            observer.sendNotification();
        }
    }

    @Override
    public void setStockCount(int newStockCount) {
        var oldStockCount = this.stockCount;
        this.stockCount += newStockCount;
        if (oldStockCount == 0) {
            notifyObservers();
        }
    }

    @Override
    public int getStockCount() {
        return this.stockCount;
    }

    @Override
    public void removeObserver(NotifyingObserver observer) {
        observers.remove(observer);
    }
}
