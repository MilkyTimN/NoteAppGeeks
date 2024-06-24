package com.example.noteappgeeks.interfaces

import com.example.noteappgeeks.data.models.NoteModel

interface Clickable {

    fun onLongClick(noteModel: NoteModel)

    fun onClick(noteModel: NoteModel)
}