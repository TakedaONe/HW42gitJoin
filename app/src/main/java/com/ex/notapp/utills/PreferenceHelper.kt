package com.ex.notapp.utills

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private lateinit var sharedPreferences: SharedPreferences

    fun unit(context: Context) {
        sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    var firstEnter: Boolean
        get() = sharedPreferences.getBoolean("firstEnter", false)
        set(value) = sharedPreferences.edit().putBoolean("firstEnter", value).apply()

    var swap: Boolean
        get() = sharedPreferences.getBoolean("swapLayout", false)
        set(value) = sharedPreferences.edit().putBoolean("swapLayout", value).apply()

    fun customPrefs(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)
}



