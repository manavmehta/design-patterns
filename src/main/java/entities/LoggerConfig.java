package entities;

import abstracts.Sink;
import enums.LoggerType;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class LoggerConfig {
    public String loggerName;
    public List<Sink> sinks;
    public DateTimeFormatter formatter;
    public int bufferSize;
    public LoggerType loggerType;

    public LoggerConfig(String name, List<Sink> sinks, DateTimeFormatter formatter, int bufferSize, LoggerType type){
        this.loggerName = name;
        this.sinks = sinks;
        this.formatter = formatter;
        this.bufferSize = bufferSize;
        this.loggerType = type;
    }
}
