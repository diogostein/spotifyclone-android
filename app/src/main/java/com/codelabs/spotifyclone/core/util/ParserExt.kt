package com.codelabs.spotifyclone.core.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun String.toDateTime(pattern: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"): Date? {
    return try {
        SimpleDateFormat(pattern, Locale.getDefault()).parse(this)
    } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
}

fun Long.toMinutesAndSeconds(): String {
    val minutes = TimeUnit.MILLISECONDS.toMinutes(this)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(this)
    val secondsMinus = TimeUnit.MINUTES.toSeconds(minutes)

    return String.format("%d:%02d", minutes, seconds - secondsMinus)
}