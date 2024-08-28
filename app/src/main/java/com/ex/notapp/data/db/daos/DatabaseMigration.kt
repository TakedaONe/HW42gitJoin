package com.ex.notapp.data.db.daos

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_3 = object : Migration(2,3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE noteModel ADD COLUMN color TEXT DEFAULT ''")

    }


}