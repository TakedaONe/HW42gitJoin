package com.ex.notapp.utills

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ex.notapp.data.db.daos.AppDatabase
import com.ex.notapp.data.db.daos.MIGRATION_2_3


class App : Application() {


    companion object {
        var appDatabase: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceHelper
        sharedPreferences.unit(this)
        getInstance()

    }

    private fun getInstance(): AppDatabase? {
        if (appDatabase == null) {
            synchronized(App::class.java) {
                if (appDatabase == null) {
                    appDatabase = Room.databaseBuilder(
                        applicationContext, AppDatabase::class.java, "note.database"
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
        }
        return appDatabase
    }


}