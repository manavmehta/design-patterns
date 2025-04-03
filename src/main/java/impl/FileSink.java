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
    public void write(LogMessage message) {
        createFileIfDoesntExist();
        try{
            var fileWriter = new FileWriter(filePath);
            fileWriter.write(message.getLog());
            fileWriter.close();
        }
        catch (Exception e){
            System.out.println("Could not write to file " + filePath);
        }
    }

    @Override
    public void clear() {

    }
}
