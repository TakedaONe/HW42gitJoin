package com.ex.notapp.data.db.daos

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.ex.notapp.converter.Converters
import com.ex.notapp.data.models.NoteModel

@Database(entities = [NoteModel::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

}