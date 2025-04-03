package impl;

import entities.*;

import java.time.LocalDateTime;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class AsyncLogger implements Logger {
    List<Sink> logSinks;
    LoggerConfig config;
    Deque<LogMessage> logMessageQueue;

    public AsyncLogger (LoggerConfig config){
        this.config = config;
        logSinks = config.sinks;
        logMessageQueue = new LinkedList<>();
    }

    public void writeToSinks(LogMessage message) {
        for (var sink : logSinks) {
            sink.write(message);
        }
    }


    public void ClearSinks() {
        for (var sink : logSinks) {
            sink.clear();
        }
    }

    @Override
    public void debug(String logMessage) {
        logMessageQueue.addLast(new LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.DEBUG, logMessage));
    }

    @Override
    public void info(String logMessage) {
        logMessageQueue.addLast(new LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.INFO, logMessage));
    }

    @Override
    public void warn(String logMessage) {
        logMessageQueue.addLast(new LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.WARN, logMessage));
    }

    @Override
    public void error(String logMessage) {
        logMessageQueue.addLast(new LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.ERROR, logMessage));
    }

    @Override
    public void fatal(String logMessage) {
        logMessageQueue.addLast(new LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.FATAL, logMessage));
    }

    public void printLogs() throws InterruptedException {
        while (!logMessageQueue.isEmpty()) {
            var buffer = config.bufferSize;
            while( buffer > 0){
                var frontMessage = logMessageQueue.getFirst();
                logMessageQueue.pop();
                writeToSinks(frontMessage);
                buffer--;
            }
            Thread.sleep(2000);
            ClearSinks();
        }
    }
}
