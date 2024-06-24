package com.example.noteappgeeks.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteappgeeks.data.models.NoteModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteModel)

    @Query("SELECT * FROM noteModel")
    fun getAll(): LiveData<List<NoteModel>>

    @Query("SELECT * from noteModel WHERE id = :id")
    fun getNoteModel(id: Int): NoteModel

    @Delete
    fun deleteNoteModel(note: NoteModel)

    @Update
    fun updateNoteModel(note: NoteModel)
}