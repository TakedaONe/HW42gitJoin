package com.ex.notapp.converter

import android.graphics.Color
import androidx.room.TypeConverter
import androidx.room.TypeConverters

class Converters {

    @TypeConverter
    fun fromColorString(colorString: String?): Int? {
        return colorString?.let {
            Color.parseColor(it)
        }
    }
    @TypeConverter
    fun toColorString(color: Int):String{
        return "#${Integer.toHexString(color)}"
    }

}