package entities;

public interface Logger {
    void debug(String logMessage);
    void info(String logMessage);
    void warn(String logMessage);
    void error(String logMessage);
    void fatal(String logMessage);
}
