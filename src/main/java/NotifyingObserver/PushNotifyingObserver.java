package NotifyingObserver;

import observable.StockObservable;

import static NotifyingObserver.NotifyingObserver.content;

public class PushNotifyingObserver implements NotifyingObserver {
    private final String name;
    private final StockObservable stock;

    public PushNotifyingObserver(String name, StockObservable observable) {
        this.name = name;
        this.stock = observable;
    }

    private void sendEmail() {
        System.out.println("Push sent to: " + this.name + "\n" + content + "New stock count: " + stock.getStockCount());
    }

    public void sendNotification() {
        sendEmail();
    }
}
