import entities.LogLevel
import entities.LoggerConfig
import entities.LoggerType
import factory.LoggerFactory
import impl.AsyncLogger
import impl.ConsoleSink
import impl.FileSink
import java.time.format.DateTimeFormatter

object Main {
    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        sync()
    }

    private fun sync() {
        val factory = LoggerFactory()
        val sinks = listOf(
            ConsoleSink(LogLevel.INFO),
            FileSink(LogLevel.ERROR, "/Users/manavmehta/logger/0.log")
        )
        val config = LoggerConfig("testLogger", sinks, DateTimeFormatter.ISO_DATE_TIME, 2, LoggerType.SYNC)
        val logger = factory.getLogger(config)
        logger.info("info01")
        logger.info("info2")
        logger.info("info3")
        logger.error("error1")
        logger.error("error2")
    }

    @Throws(InterruptedException::class)
    private fun async() {
        val factory = LoggerFactory()
        val sinks = listOf(ConsoleSink(LogLevel.INFO), FileSink(LogLevel.ERROR, "/Users/manavmehta/logger/0.log"))
        val config = LoggerConfig("testLogger", sinks, DateTimeFormatter.ISO_DATE_TIME, 2, LoggerType.ASYNC)
        val logger = factory.getLogger(config) as AsyncLogger
        logger.debug("debug")
        logger.info("info")
        logger.warn("warn")
        logger.error("error")
        logger.fatal("fatal")
        logger.printLogs()
    }
}
