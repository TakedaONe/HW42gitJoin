package com.ex.notapp.data.models

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class NoteModel(

    val title: String,
    val description: String,
    @ColumnInfo(name = "color")
    val color: String

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
