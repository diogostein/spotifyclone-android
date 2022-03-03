package com.codelabs.spotifyclone.common.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDateTime(pattern: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"): Date? {
    return try {
        SimpleDateFormat(pattern, Locale.getDefault()).parse(this)
    } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
}