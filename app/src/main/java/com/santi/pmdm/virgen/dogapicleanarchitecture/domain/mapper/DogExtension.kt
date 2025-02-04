package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.mapper

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog


/*
Funciones de extensión, para convertir objetos de Tipo DogEntity/Dog a DogModel
 */


//Necesitamos mapear un DogEntity a DogModel BBDD  --> IU
fun DogEntity.toDogEntity(): Dog {
    return Dog(breed = this.breed, image = this.image)
}

/*
//Necesitamos mapear un Dog a DogModel   Memoria --> IU
fun Dog.toDogEntity(): DogModel {
    return DogModel(breed = this.breed, image = this.image)
}
*/
//Necesitamos mapear un DogModel a DogEntity para persistir  IU --> BBDD
fun Dog.toDogEntity(): DogEntity{
    return DogEntity(breed = breed, image = image)
}

