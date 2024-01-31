package com.example.myapplication.utils

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    // Convertir une valeur Long en Date. Peut renvoyer null si la valeur Long est null.
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    // Convertir une Date en valeur Long. Peut renvoyer null si la Date est null.
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}
