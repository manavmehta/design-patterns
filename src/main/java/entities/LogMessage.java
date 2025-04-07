package entities;

import enums.LogLevel;

public class LogMessage {
    public String timestamp;
    public LogLevel level;
    public String content;

    public LogMessage(String timestamp, LogLevel logLevel, String content) {
        this.timestamp = timestamp;
        this.level = logLevel;
        this.content = content;
    }

    public String getLog(){
        return String.join(" ", timestamp, "[", level.name(), "]", content);
    }
}
