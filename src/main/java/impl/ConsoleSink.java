package impl;

import entities.LogLevel;
import entities.LogMessage;
import entities.Sink;

public class ConsoleSink implements Sink {
    LogLevel cutoffLogLevel;

    public ConsoleSink(LogLevel cutoffLogLevel){
        this.cutoffLogLevel = cutoffLogLevel;
    }
    @Override
    public void write(LogMessage message) {
        var logLevelComparison = message.level.compareTo(cutoffLogLevel);
        if (logLevelComparison >= 0){
            System.out.println(message.getLog());
        }
    }

    @Override
    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
