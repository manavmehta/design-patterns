package impl;

import entities.*;

import java.time.LocalDateTime;
import java.util.List;

public class SyncLogger implements Logger {
    LoggerConfig config;
    List<Sink> logSinks;

    public SyncLogger (LoggerConfig config){
        this.config = config;
        logSinks = config.sinks;
    }

    public synchronized void writeToSinks(LogMessage message){
        for (var sink: logSinks){
            sink.write(message);
        }

    }
    @Override
    public void debug(String logMessage) {
        writeToSinks(new LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.DEBUG, logMessage));
    }

    @Override
    public void info(String logMessage) {
        writeToSinks(new LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.INFO, logMessage));
    }

    @Override
    public void warn(String logMessage) {
        writeToSinks(new LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.WARN, logMessage));
    }

    @Override
    public void error(String logMessage) {
        writeToSinks(new LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.ERROR, logMessage));
    }

    @Override
    public void fatal(String logMessage) {
        writeToSinks(new LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.FATAL, logMessage));
    }
}
