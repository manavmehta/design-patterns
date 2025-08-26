package entities

class LogMessage(private val timestamp: String, val level: LogLevel, private val content: String) {
	val log: String
		get() = java.lang.String.join(" ", timestamp, "[", level.name, "]", content)
}
