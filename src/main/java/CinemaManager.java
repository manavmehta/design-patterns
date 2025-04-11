public class CinemaManager {
    static CinemaManager INSTANCE = new CinemaManager();
    private CinemaManager() {
    }
    public static CinemaManager getInstance() {
        return INSTANCE;
    }
}
