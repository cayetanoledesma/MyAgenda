package com.example.myagenda.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Personas(

    @PrimaryKey(autoGenerate = true)
    var idPersona: Long,
    var nombre: String,
    var apellidos: String,
    var telefono: String,
    var email: String,
    var edad: String

    )




