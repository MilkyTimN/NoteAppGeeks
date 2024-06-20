package com.example.noteappgeeks.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteappgeeks.data.models.enums.NoteColorEnum
import java.time.LocalDateTime

@Entity(tableName = "noteModel")
data class NoteModel(
    val title: String,
    val description: String,
    val noteColor : NoteColorEnum,
    val addDate: LocalDateTime

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
