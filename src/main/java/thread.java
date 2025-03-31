public class thread extends Thread {
    @Override
    public void run() {
        var instance1 = ThreadSafeSingleton.SingletonHelper.getInstance();
        System.out.println("Thread: " + this.threadId() + " => " + instance1);
    }
}