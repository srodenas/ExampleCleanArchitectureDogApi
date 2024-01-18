package com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
Utilizaré la misma clase Dog, para que room la utilice y cree la tabla de la BBDD.
Añadimos un nuevo atributo específico para Room llamado id. La opción autoGenerate = true indica
que Room generará la clave primaria automáticamente. No hace falta que se lo insertemos nosotros.
*/
@Entity
data class DogEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id") val id: Int=0,  //por defecto ponemos el id a 0.
        @ColumnInfo(name = "breed") val breed: String,
        @ColumnInfo (name="image") val image: String
    )

