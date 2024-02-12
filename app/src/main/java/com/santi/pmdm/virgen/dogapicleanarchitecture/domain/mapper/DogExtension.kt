package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.mapper

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.mem.models.Dog
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.network.models.DogsResponse
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.DogModel


/*
Funciones de extensión, para convertir objetos de Tipo DogEntity/Dog a DogModel
 */
fun DogEntity.toDomain(): DogModel {
    return DogModel(breed = this.breed, image = this.image)
}

fun Dog.toDomain(): DogModel {
    return DogModel(breed = this.breed, image = this.image)
}

fun DogModel.toDomain(): DogEntity{
    return DogEntity(breed = breed, image = image)
}


