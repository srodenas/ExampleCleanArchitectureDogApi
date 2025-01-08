package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.repository

import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog

interface DogRepositoryInterface {
    fun getDogs() : List<Dog>
    fun getBreedDogs(breed:String) : List<Dog>
}