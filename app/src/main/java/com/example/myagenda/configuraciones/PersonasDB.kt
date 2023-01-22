package com.example.myagenda.configuraciones

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myagenda.dao.PersonasDao
import com.example.myagenda.models.Personas

@Database(
    entities =[Personas::class],
    version = 1
)
abstract class PersonasDB:RoomDatabase() {
    abstract fun personasDao(): PersonasDao

}