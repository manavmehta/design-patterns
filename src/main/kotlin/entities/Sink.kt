package entities

interface Sink {
	fun write(message: LogMessage?)

	fun clear()
}
