package com.example.noteappgeeks

import android.app.Application
import androidx.room.Room
import com.example.noteappgeeks.data.db.AppDatabase
import com.example.noteappgeeks.utils.SharedPreference

class App : Application() {

    companion object {
        var appDatabase: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        val sharedPreference = SharedPreference()
        sharedPreference.unit(this)
        getInstance()
    }

    fun getInstance(): AppDatabase? {
        if (appDatabase == null) {
            appDatabase = applicationContext?.let {
                Room.databaseBuilder(
                    it,
                    AppDatabase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigrationFrom().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }
}