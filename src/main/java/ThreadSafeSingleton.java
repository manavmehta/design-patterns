public class ThreadSafeSingleton {
    private ThreadSafeSingleton() {
    }
    public static class SingletonHelper {
        static ThreadSafeSingleton INSTANCE = new ThreadSafeSingleton();
        public static ThreadSafeSingleton getInstance() {
            return INSTANCE;
        }
    }

}
