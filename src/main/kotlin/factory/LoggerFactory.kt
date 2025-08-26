package factory

import entities.Logger
import entities.LoggerConfig
import entities.LoggerType
import impl.AsyncLogger
import impl.SyncLogger

class LoggerFactory {
    fun getLogger(config: LoggerConfig): Logger {
        return when (config.loggerType) {
            LoggerType.SYNC -> SyncLogger(config)
            LoggerType.ASYNC -> AsyncLogger(config)
        }
    }
}
