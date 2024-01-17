package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models

interface DogRepositoryInterface {
    fun getDogs() : List<DogModel>
    fun getBreedDogs(breed:String) : List<DogModel>
    fun getDogsEntity() : List<DogModel>
    fun getBreedDogsEntity(breed:String) : List<DogModel>


}