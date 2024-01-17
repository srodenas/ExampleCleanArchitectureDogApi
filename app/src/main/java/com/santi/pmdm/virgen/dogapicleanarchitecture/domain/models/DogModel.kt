package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.mem.models.Dog

data class DogModel (val breed: String, val image: String){

    //funcion de extensión de Dog, que devolverá un DogModel para el dominio.
    fun Dog.toDomain() = DogModel(breed, image)

    //función de extensión de Dot, que devolverá un DogModel para el dominio.
    fun DogEntity.toDomain() = DogModel(breed, image)
}