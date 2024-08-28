package com.ex.notapp.interfaces

import com.ex.notapp.data.models.NoteModel

interface OnClickItem {
    fun onLongClickItem(noteModel: NoteModel)
    fun onClick(noteModel: NoteModel)
}