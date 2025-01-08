package com.santi.pmdm.virgen.dogapicleanarchitecture.data.service

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.Dogs
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.service.DogServiceInterface

/*
Esta clase, simula el servicio de acceso a los datos nativos.
 */
class DogService : DogServiceInterface {
    override fun getDogs(): List<Pair<String, String>> {
        return Dogs.dogs
    }

    /*
    Aquí, estoy filtrando por raza. Simulo un acceso por filtro.
     */
    override fun getBreedDogs(breed: String): List<Pair<String,String>> {
        val newDogs = Dogs.dogs.filter {
            it.first.equals(breed)
        }
        return newDogs
    }

}