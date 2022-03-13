package com.codelabs.spotifyclone.core.util

import android.os.SystemClock
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProgressTimeTicker(
    private val refreshRateInMs: Long = 200,
    private val precision: Int = 1000,
) {
    private var job: Job? = null
    private var onProgressTick: ((Double, Long, Long) -> Unit)? = null

    fun setOnProgressTickListener(onProgressTick: (Double, Long, Long) -> Unit) {
        this.onProgressTick = onProgressTick
    }

    fun start(durationInMs: Long, initialPositionInMs: Long) {
        job?.cancel()

        if (durationInMs <= 0 || durationInMs < initialPositionInMs || onProgressTick == null) return

        val startInMs = SystemClock.elapsedRealtime()
        var positionInMs: Long

        job = tickerFlow(refreshRateInMs).onEach {
            positionInMs = SystemClock.elapsedRealtime() - startInMs + initialPositionInMs
            val progress = (positionInMs.toDouble() / durationInMs.toDouble()) * precision
            Log.d("DS", "duration: $durationInMs, pos: $positionInMs, progress: $progress, ${progress.toInt()}")
            withContext(Main) {
                onProgressTick?.invoke(progress, positionInMs, durationInMs)
            }
        }.launchIn(CoroutineScope(IO))
    }

    fun stop() {
        job?.cancel()
    }

    fun dispose() {
        job?.cancel()
        job = null
    }

    private fun tickerFlow(period: Long) = flow {
        while (true) {
            emit(Unit)
            delay(period)
        }
    }

}