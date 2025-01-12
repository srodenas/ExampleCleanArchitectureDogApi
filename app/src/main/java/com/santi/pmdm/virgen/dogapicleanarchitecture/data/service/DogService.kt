package com.santi.pmdm.virgen.dogapicleanarchitecture.data.service

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.Dogs


/*
    1.-Esta clase, simula el servicio de acceso a los datos nativos.
    2.- Clase que devuelve la lista de Dogs (simulamos el acceso a la api)
    3.- Clase que devuelve la lista de Dogs por raza (simulamos el acceso a la api por raza)
 */
class DogService  {
   fun getDogs(): List<Pair<String, String>> {
        return Dogs.dogs
    }

    /*
    Aquí, estoy filtrando por raza. Simulo un acceso por filtro.
     */
   fun getBreedDogs(breed: String): List<Pair<String,String>> {
        val newDogs = Dogs.dogs.filter {
            it.first.equals(breed)
        }
        return newDogs
    }

}