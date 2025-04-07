package abstracts;

import entities.LogMessage;

public interface Sink {
    void write(LogMessage message);
    void clear();
}
