package com.example.aarlibrary

import android.content.Context

object SdkApp {
    private var initialized = false

    fun init(context: Context) {
        if (initialized) return
        initialized = true

        Logger.init(context.applicationContext)
        LogSender.start(context.applicationContext)
    }

    fun log(event: String) {
        Logger.log(event)
    }

    fun stop() {
        LogSender.stop()
    }
}
