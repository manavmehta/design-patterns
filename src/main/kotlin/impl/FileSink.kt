package impl

import entities.LogLevel
import entities.LogMessage
import entities.Sink
import java.io.File
import java.io.FileWriter
import java.io.IOException

// Import the File class
class FileSink(var cutoffLogLevel: LogLevel, var filePath: String) : Sink {
    init {
        createFileIfDoesntExist()
    }

    private fun createFileIfDoesntExist() {
        try {
            val logFile = File(filePath)
            logFile.createNewFile()
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    override fun write(message: LogMessage?) {
        createFileIfDoesntExist()
        try {
            val fileWriter = FileWriter(filePath)
            if (message != null) {
                fileWriter.write(message.log)
            }
            fileWriter.close()
        } catch (e: Exception) {
            println("Could not write to file $filePath")
        }
    }

    override fun clear() {
    }
}
