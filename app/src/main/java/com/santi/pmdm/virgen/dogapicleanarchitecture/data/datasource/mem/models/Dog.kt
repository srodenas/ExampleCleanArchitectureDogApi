package com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.mem.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
Dog será el modelo de datos en la DATA. Imaginamos que es el mapping que hiciera el servicio.
 */

data class Dog(

    val breed: String, val image: String
)
