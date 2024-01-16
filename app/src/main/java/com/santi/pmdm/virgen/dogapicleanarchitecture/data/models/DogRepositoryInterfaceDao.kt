package com.santi.pmdm.virgen.dogapicleanarchitecture.data.models

interface DogRepositoryInterfaceDao {
    fun getDogs() : List<Dog>
    fun getBreedDogs(breed:String) : List<Dog>
}