package com.example.myagenda.dao

import androidx.room.*

import com.example.myagenda.models.Personas


    @Dao
    interface PersonasDao {



        @Query("SELECT * FROM Personas")
        suspend fun getAll(): List<Personas>

        @Query("SELECT * FROM Personas WHERE idPersona = :id")
        suspend fun getById(id:Long):Personas

        @Query("SELECT * FROM Personas WHERE nombre LIKE '%' || :name || '%' OR apellidos LIKE '%' || :name || '%'")
        suspend fun getByName(name:String): List<Personas>

        @Insert
        suspend fun insert(persona: List<Personas>): List<Long>

        @Update
        suspend fun update(personas: Personas):Int

        @Delete
        suspend fun delete(personas: Personas):Int
    }
