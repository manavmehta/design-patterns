package impl

import entities.LogLevel
import entities.LogMessage
import entities.Sink

class ConsoleSink(private var cutoffLogLevel: LogLevel) : Sink {
    override fun write(message: LogMessage?) {
        val logLevelComparison = message?.level?.compareTo(cutoffLogLevel)
        if (logLevelComparison != null) {
            if (logLevelComparison >= 0) {
                println(message.log)
            }
        }
    }

    override fun clear() {
        print("\u001b[H\u001b[2J")
        System.out.flush()
    }
}
