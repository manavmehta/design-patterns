import entities.*;
import factory.LoggerFactory;
import impl.AsyncLogger;
import impl.ConsoleSink;
import impl.FileSink;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        sync();
    }

    public static void sync(){
        var factory = new LoggerFactory();
        List<Sink> sinks = List.of(new FileSink(LogLevel.ERROR, "/Users/manavmehta/logger/0.log"));
        var config = new LoggerConfig("testLogger", sinks, DateTimeFormatter.ISO_DATE_TIME, 2, LoggerType.ASYNC);
        var logger = factory.getLogger(config);
        logger.info("info1");
        logger.info("info2");
        logger.info("info3");
        logger.error("error1");
        logger.error("error2");
    }

    public static void async() throws InterruptedException {
        var factory = new LoggerFactory();
        List<Sink> sinks = List.of(new ConsoleSink(LogLevel.INFO), new FileSink(LogLevel.ERROR, "/Users/manavmehta/logger/0.log"));
        var config = new LoggerConfig("testLogger", sinks, DateTimeFormatter.ISO_DATE_TIME, 2, LoggerType.ASYNC);
        AsyncLogger logger = (AsyncLogger) factory.getLogger(config);
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");
        logger.printLogs();
    }
}
