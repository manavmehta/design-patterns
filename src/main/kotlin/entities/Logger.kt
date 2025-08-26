package entities

interface Logger {
    fun debug(logMessage: String?)
    fun info(logMessage: String?)
    fun warn(logMessage: String?)
    fun error(logMessage: String?)
    fun fatal(logMessage: String?)
}
