package com.example.noteappgeeks

import android.app.Application
import com.example.noteappgeeks.utils.SharedPreference

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val sharedPreference = SharedPreference()
        sharedPreference.unit(this)
    }
}