package com.example.myagenda.configuraciones

import android.app.Application
import androidx.room.Room


class PersonasApp: Application() {

    companion object{
        lateinit var db:PersonasDB
    }

    override fun onCreate(){
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            PersonasDB::class.java,
            "personas"
        ).build()
    }

}