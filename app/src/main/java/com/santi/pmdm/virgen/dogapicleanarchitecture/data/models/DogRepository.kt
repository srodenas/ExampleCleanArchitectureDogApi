package com.santi.pmdm.virgen.dogapicleanarchitecture.data.models

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.service.DogService
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.model.Dog
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.model.Repository

/*
Clase que devuelve los datos, a partir de un acceso al servicio.
El servicio, devuelve los datos de forma nativa.

Esta clase, se encargará de adaptar dichos datos a los que necesite
la aplicación.
 */
class DogRepository : DogRepositoryInterface{
    private val service : DogService = DogService()

    /*
    Método que a partir de los datos nativos, devuelve la lista
    de objetos que necesita el modelo.
     */
    override fun getDogs(): List<Dog> {
        var mutableDogs : MutableList<Dog> = mutableListOf()
        val dataSource = service.getDogs()
        dataSource.forEach{ dog->
            mutableDogs.add(Dog(dog.first, dog.second))
        }
        Repository.dogs = mutableDogs //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return Repository.dogs
    }

    override fun getBreedDogs(breed: String): List<Dog> {
        var mutableDogs : MutableList<Dog> = mutableListOf()
        val dataSource = service.getBreedDogs(breed)
        dataSource.forEach{ dog->
            mutableDogs.add(Dog(dog.first, dog.second))
        }
        Repository.dogs = mutableDogs //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return Repository.dogs
    }
}