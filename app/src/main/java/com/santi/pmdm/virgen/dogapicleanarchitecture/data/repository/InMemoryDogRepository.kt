package com.santi.pmdm.virgen.dogapicleanarchitecture.data.repository

import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.DogsData
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.service.DogService
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.repository.DogRepositoryInterface

/*
Clase que devuelve los datos, a partir de un acceso al servicio.
El servicio, devuelve los datos de forma nativa.

Esta clase, se encargará de adaptar dichos datos a los que necesite
la aplicación.
 */
class InMemoryDogRepository : DogRepositoryInterface {
    private val service : DogService = DogService()  //simula las peticiones a la API, pero será memoria.

    /*
    Método que a partir de los datos nativos, devuelve la lista
    de objetos que necesita el modelo.
     */
   override  suspend fun getDogs(): List<Dog> {
        var mutableDogs : MutableList<Dog> = mutableListOf()
        val dataSource = service.getDogs()  //aquí tenemos la lista de Dog nativos. Pair.
        dataSource.forEach{ dogPair->
            mutableDogs.add(Dog(dogPair.first, dogPair.second)) //para todos los Pair --> creamos Dog(modelo del dominio)
        }
        DogsData.dogs = mutableDogs //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return DogsData.dogs
    }

    /*
    Cargo todos los datos filtrados por raza, desde nuestra lista Pair (datos nativos) a memoria.
     */
    override suspend fun getBreedDogs(breed: String): List<Dog> {
        var mutableDogs : MutableList<Dog> = mutableListOf()
        val dataSource = service.getBreedDogs(breed)
        dataSource.forEach{ dogPair->
            mutableDogs.add(Dog(dogPair.first, dogPair.second))
        }
        DogsData.dogs = mutableDogs //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return DogsData.dogs
    }
}