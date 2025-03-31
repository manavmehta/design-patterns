public class Main {
    public static void main(String[] args){
        var t1 = new thread();
        var t2 = new thread();
        t1.start();
        t2.start();
    }
}