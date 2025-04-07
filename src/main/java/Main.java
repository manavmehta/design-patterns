import entities.*;
import factory.LoggerFactory;
import impl.AsyncLogger;
import impl.ConsoleSink;
import impl.FileSink;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        async();
    }

    public static void sync(){
        var factory = new LoggerFactory();
        List<Sink> sinks = List.of(new ConsoleSink(LogLevel.INFO));
        var config = new LoggerConfig("testLogger", sinks, DateTimeFormatter.ISO_DATE_TIME, 2, LoggerType.SYNC);
        var logger = factory.getLogger(config);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            int threadNo = i;
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    logger.info("Thread " + threadNo + " - Log message " + j);
                }
            });
            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Multithreading test completed.");
    }

    public static void async() {
        var factory = new LoggerFactory();
        List<Sink> sinks = List.of(new ConsoleSink(LogLevel.INFO), new FileSink(LogLevel.ERROR, "0.log"));
        var config = new LoggerConfig("testLogger", sinks, DateTimeFormatter.ISO_DATE_TIME, 2, LoggerType.ASYNC);
        var logger = factory.getLogger(config);
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int threadNo = i;
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    logger.info("Thread " + threadNo + " - Log message " + j);
                }
            });
            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Multithreading test completed.");
    }
}
