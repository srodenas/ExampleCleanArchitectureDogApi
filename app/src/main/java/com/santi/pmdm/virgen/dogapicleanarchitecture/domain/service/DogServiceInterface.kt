package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.service


/*
Interface que deben de implementar todos los servicios.
 */
interface DogServiceInterface {
    fun getDogs(): List<Pair<String,String>>
    fun getBreedDogs(breed: String) : List<Pair<String,String>>
}