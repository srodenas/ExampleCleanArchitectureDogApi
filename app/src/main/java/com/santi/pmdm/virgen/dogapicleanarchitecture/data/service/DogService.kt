package com.santi.pmdm.virgen.dogapicleanarchitecture.data.service

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.Dogs
import kotlinx.coroutines.delay


/*
    1.-Esta clase, simula el servicio de acceso a los datos nativos.
    2.- Clase que devuelve la lista de Dogs (simulamos el acceso a la api)
    3.- Clase que devuelve la lista de Dogs por raza (simulamos el acceso a la api por raza)
 */
class DogService  {
   suspend fun getDogs(): List<Pair<String, String>> {
       delay(1000)  // Simulando un retardo de red (como si estuviera esperando una respuesta de la API)
       return Dogs.dogs
    }

    /*
    Aquí, estoy filtrando por raza. Simulo un acceso por filtro.
     */
   suspend fun getBreedDogs(breed: String): List<Pair<String,String>> {
        delay(1000)
        val newDogs = Dogs.dogs.filter {
            it.first.equals(breed)
        }
        return newDogs
    }

}