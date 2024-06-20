package com.example.noteappgeeks.data.db.convertor

import androidx.room.TypeConverter
import com.example.noteappgeeks.data.models.enums.NoteColorEnum

class NoteColorEnumConvertor {

    @TypeConverter
    fun fromNoteColorEnum(value: NoteColorEnum): String {
        return value.name
    }

    @TypeConverter
    fun toNoteColorEnum(value: String): NoteColorEnum {
        return NoteColorEnum.valueOf(value)
    }
}