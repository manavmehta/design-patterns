import NotifyingObserver.EmailNotifyingObserver;
import NotifyingObserver.NotifyingObserver;
import NotifyingObserver.PushNotifyingObserver;
import observable.MobilePhoneObservable;
import observable.StockObservable;

public class Main {
    public static void main(String[] args){
        StockObservable iPhone20 = new MobilePhoneObservable();

        var observer1 = new EmailNotifyingObserver("chintu@gmail.com", iPhone20);
        var observer2 = new PushNotifyingObserver("mintu_singh", iPhone20);

        iPhone20.addObserver(observer1);
        iPhone20.addObserver(observer2);
        iPhone20.setStockCount(10);
    }
}