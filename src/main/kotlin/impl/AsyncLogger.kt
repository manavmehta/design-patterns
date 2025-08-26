package impl

import entities.LogLevel
import entities.LogMessage
import entities.Logger
import entities.LoggerConfig
import entities.Sink
import java.time.LocalDateTime
import java.util.*

class AsyncLogger(private var config: LoggerConfig) : Logger {
	private var logSinks: List<Sink> = config.sinks
	private var logMessageQueue: Deque<LogMessage> = LinkedList()

	private fun writeToSinks(message: LogMessage?) {
		for (sink in logSinks) {
			sink.write(message)
		}
	}

	private fun clearSinks() {
		for (sink in logSinks) {
			sink.clear()
		}
	}

	override fun debug(logMessage: String?) {
		logMessageQueue.addLast(
			logMessage?.let {
				LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.DEBUG, it)
			},
		)
	}

	override fun info(logMessage: String?) {
		logMessageQueue.addLast(
			logMessage?.let {
				LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.INFO, it)
			},
		)
	}

	override fun warn(logMessage: String?) {
		logMessageQueue.addLast(
			logMessage?.let {
				LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.WARN, it)
			},
		)
	}

	override fun error(logMessage: String?) {
		logMessageQueue.addLast(
			logMessage?.let {
				LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.ERROR, it)
			},
		)
	}

	override fun fatal(logMessage: String?) {
		logMessageQueue.addLast(
			logMessage?.let {
				LogMessage(LocalDateTime.now().format(config.formatter), LogLevel.FATAL, it)
			},
		)
	}

	@Throws(InterruptedException::class)
	fun printLogs() {
		while (!logMessageQueue.isEmpty()) {
			var buffer = config.bufferSize
			while (buffer > 0) {
				val frontMessage = logMessageQueue.first
				logMessageQueue.pop()
				writeToSinks(frontMessage)
				buffer--
			}
			Thread.sleep(2000)
			clearSinks()
		}
	}
}
