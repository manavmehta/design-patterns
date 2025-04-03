package factory;

import entities.LoggerConfig;
import impl.AsyncLogger;
import entities.Logger;
import entities.LoggerType;
import impl.SyncLogger;

public class LoggerFactory {

    public Logger getLogger(LoggerConfig config){
        return switch (config.loggerType){
            case LoggerType.SYNC -> new SyncLogger(config);
            case ASYNC -> new AsyncLogger(config);
        };
    }
}
