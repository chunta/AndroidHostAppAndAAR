package com.example.aarlibrary

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * LogSender 負責定時從 Logger 取出日誌並送往伺服器。
 *
 * 使用 Coroutine 在 IO 執行緒中持續運行，週期性拉取日誌並發送。
 */
object LogSender {
    private var job: Job? = null // 保存定時任務的 Job，方便停止
    private val scope = CoroutineScope(Dispatchers.IO) // 使用 IO 執行緒的 CoroutineScope

    /**
     * 啟動 LogSender，預設每 10 秒拉取一次日誌。
     * 若已經啟動過，則不重複啟動。
     *
     * @param context 用於傳遞上下文 (目前未使用，但可擴充)
     * @param intervalMillis 拉取日誌的間隔時間，單位毫秒
     */
    fun start(
        context: Context,
        intervalMillis: Long = 10_000L,
    ) {
        if (job != null) return // 已在執行中，跳過

        job =
            scope.launch {
                while (isActive) {
                    delay(intervalMillis) // 延遲指定時間
                    val logs = Logger.fetchLogsAndClear() // 取出最多 5 筆 log，並設定旗標
                    Log.d("LogSender", "Fetched logs: ${logs.size}")
                    if (logs.isNotEmpty()) {
                        sendToServer(context, logs) // 將日誌送至伺服器
                    }
                }
            }
    }

    /**
     * 停止 LogSender 任務，取消 Coroutine Job。
     */
    fun stop() {
        job?.cancel()
        job = null
    }

    /**
     * 模擬將日誌送到伺服器的行為。
     * 送出成功後呼叫 Logger.fetchDone()，清除已取出的 log 並重置旗標。
     *
     * @param context Android Context (目前未使用)
     * @param logs 欲送出的日誌列表
     */
    private fun sendToServer(
        context: Context,
        logs: List<String>,
    ) {
        Log.d("LogSender", "Sending logs: $logs")
        Logger.fetchDone() // 清除剛剛取出的日誌，允許下次 fetch
    }
}
