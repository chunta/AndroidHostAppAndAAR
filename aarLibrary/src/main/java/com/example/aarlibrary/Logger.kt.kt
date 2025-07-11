package com.example.aarlibrary
import android.content.Context

object Logger {
    private val logBuffer = mutableListOf<String>()
    private var fetchedLogs: List<String> = emptyList()
    private var hasFetched = false
    private val lock = Any()
    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    fun log(event: String) {
        synchronized(lock) {
            logBuffer.add("[${System.currentTimeMillis()}] $event")
        }
    }

    fun fetchLogsAndClear(): List<String> {
        synchronized(lock) {
            if (hasFetched) {
                // Already fetched once, block further fetches
                return emptyList()
            }

            // Fetch first 5 logs only
            fetchedLogs = logBuffer.take(5)
            hasFetched = true
            return fetchedLogs
        }
    }

    fun fetchDone() {
        synchronized(lock) {
            // Remove the logs that were returned by the last fetch
            logBuffer.removeAll(fetchedLogs)
            fetchedLogs = emptyList()
            hasFetched = false
        }
    }
}
