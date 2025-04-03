package entities;

public interface Sink {
    void write(LogMessage message);
    void clear();
}
