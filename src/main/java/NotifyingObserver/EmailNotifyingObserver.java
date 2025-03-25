package NotifyingObserver;

import observable.MobilePhoneObservable;
import observable.StockObservable;

import java.util.List;

import static NotifyingObserver.NotifyingObserver.content;

public class EmailNotifyingObserver implements NotifyingObserver {
    private final String email;
    private final StockObservable stock;

    public EmailNotifyingObserver(String email, StockObservable observable) {
        this.email = email;
        this.stock = observable;
    }

    private void sendEmail() {
        System.out.println("Email sent to: " + this.email + "\n" + content + "New stock count: " + stock.getStockCount());
    }

    public void sendNotification() {
        sendEmail();
    }
}
