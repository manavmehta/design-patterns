package impl;

import entities.LogLevel;
import entities.LogMessage;
import entities.Sink;

import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;

public class FileSink implements Sink {
    String filePath;
    LogLevel cutoffLogLevel;

    public FileSink (LogLevel cutoffLogLevel, String filePath){
        this.cutoffLogLevel = cutoffLogLevel;
        this.filePath = filePath;
        createFileIfDoesntExist();
    }

    void createFileIfDoesntExist(){
        try {
            var logFile = new File(filePath);
            logFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void write(LogMessage message) {  // Synchronized method
        createFileIfDoesntExist();
        try (var fileWriter = new FileWriter(filePath, true)) {  // Enable append mode and use try-with-resources
            fileWriter.write(message.getLog() + System.lineSeparator());
        } catch (Exception e) {
            System.out.println("Could not write to file " + filePath);
        }
    }

    @Override
    public void clear() {
        // Implementation for clearing the log file if needed
    }
}