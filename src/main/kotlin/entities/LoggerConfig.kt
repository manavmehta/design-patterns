package entities

import java.time.format.DateTimeFormatter

class LoggerConfig(
	var loggerName: String,
	var sinks: List<Sink>,
	var formatter: DateTimeFormatter,
	var bufferSize: Int,
	var loggerType: LoggerType,
)
