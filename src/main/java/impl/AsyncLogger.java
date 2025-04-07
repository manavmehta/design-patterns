package impl;

import abstracts.Logger;
import abstracts.Sink;
import entities.*;
import enums.LogLevel;

import java.time.LocalDateTime;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class AsyncLogger implements Logger {
    List<Sink> logSinks;
    LoggerConfig config;
    Deque<LogMessage> logMessageQueue;
    private final Object lock = new Object();
    private volatile boolean running = true;

    public AsyncLogger(LoggerConfig config) {
        this.config = config;
        logSinks = config.sinks;
        logMessageQueue = new LinkedList<>();

        // Start a separate thread for writing logs
        new Thread(this::processLogQueue).start();
    }

    private void processLogQueue() {
        while (running) {
            while (!logMessageQueue.isEmpty() && logMessageQueue.size() >= config.bufferSize) {
                for (int i = 0; i < config.bufferSize && !logMessageQueue.isEmpty(); i++) {
                    var message = logMessageQueue.poll();
                    if (message != null) {
                        writeToSinks(message);
                    }
                }
                try {
                    Thread.sleep(2000);  // Adjust sleep time as needed
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void writeToSinks(LogMessage message) {
        for (var sink : logSinks) {
            sink.write(message);
        }
    }

    public void clearSinks() {
        for (var sink : logSinks) {
            sink.clear();
        }
    }

    @Override
    public void debug(String logMessage) {
        addLogMessage(new LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.DEBUG, logMessage));
    }

    @Override
    public void info(String logMessage) {
        addLogMessage(new LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.INFO, logMessage));
    }

    @Override
    public void warn(String logMessage) {
        addLogMessage(new LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.WARN, logMessage));
    }

    @Override
    public void error(String logMessage) {
        addLogMessage(new LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.ERROR, logMessage));
    }

    @Override
    public void fatal(String logMessage) {
        addLogMessage(new LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.FATAL, logMessage));
    }

    private void addLogMessage(LogMessage message) {
        synchronized (lock) {
            logMessageQueue.addLast(message);
        }
    }

    public void stop() {
        running = false;
    }
}