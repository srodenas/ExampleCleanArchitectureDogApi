package com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
Utilizaré la misma clase Dog, para que room la utilice y cree la tabla de la BBDD.
Añadimos un nuevo atributo específico para Room llamado id. La opción autoGenerate = true indica
que Room generará la clave primaria automáticamente. No hace falta que se lo insertemos nosotros.
*/
@Entity
data class DogEntity(
        @PrimaryKey(autoGenerate = true) val id: Int,
        val breed: String, val image: String
    )

