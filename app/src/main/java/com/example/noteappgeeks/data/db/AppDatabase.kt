package com.example.noteappgeeks.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteappgeeks.data.db.convertor.LocalDateTimeConvertor
import com.example.noteappgeeks.data.db.convertor.NoteColorEnumConvertor
import com.example.noteappgeeks.data.db.dao.NoteDao
import com.example.noteappgeeks.data.models.NoteModel

@Database(entities = [NoteModel::class], version = 2)
@TypeConverters(NoteColorEnumConvertor::class, LocalDateTimeConvertor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}