package impl

import entities.*
import java.time.LocalDateTime

class SyncLogger(private var config: LoggerConfig) : Logger {
    private var logSinks: List<Sink> = config.sinks

    private fun writeToSinks(message: LogMessage?) {
        for (sink in logSinks) {
            sink.write(message)
        }
    }

    override fun debug(logMessage: String?) {
        writeToSinks(logMessage?.let { LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.DEBUG, it) })
    }

    override fun info(logMessage: String?) {
        writeToSinks(logMessage?.let { LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.INFO, it) })
    }

    override fun warn(logMessage: String?) {
        writeToSinks(logMessage?.let { LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.WARN, it) })
    }

    override fun error(logMessage: String?) {
        writeToSinks(logMessage?.let { LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.ERROR, it) })
    }

    override fun fatal(logMessage: String?) {
        writeToSinks(logMessage?.let { LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.FATAL, it) })
    }
}
