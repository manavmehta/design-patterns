package observable;

import NotifyingObserver.NotifyingObserver;

public interface StockObservable {
    void addObserver(NotifyingObserver observer);
    void removeObserver(NotifyingObserver observer);
    void notifyObservers();
    void setStockCount(int newStockCount);
    int getStockCount();
}
