package main.java.factory;


import main.java.entities.Logger;
import main.java.entities.LoggerConfig;
import main.java.entities.LoggerType;
import main.java.impl.AsyncLogger;
import main.java.impl.SyncLogger;

public class LoggerFactory {

    public Logger getLogger(LoggerConfig config){
        return switch (config.loggerType){
            case LoggerType.SYNC -> new SyncLogger(config);
            case ASYNC -> new AsyncLogger(config);
        };
    }
}
