package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity

interface DogRepositoryInterface {
    fun getDogs() : List<DogModel>
    fun getBreedDogs(breed:String) : List<DogModel>

    /*
    Todas las operaciones con la BBDD, las haremos asíncronas.
    Por tanto le ponemos suspend.
     */
    suspend fun getDogsEntity() : List<DogModel>
    suspend fun getBreedDogsEntity(breed:String) : List<DogModel>


    suspend fun insertBreedEntitytoDatabase(listEntity : List<DogEntity>)

    suspend fun deleteDatabase()
}