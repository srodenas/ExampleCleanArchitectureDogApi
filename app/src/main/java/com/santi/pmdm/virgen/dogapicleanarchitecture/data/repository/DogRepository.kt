package com.santi.pmdm.virgen.dogapicleanarchitecture.data.repository

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.dao.DogDao
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.mem.service.DogService
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.mapper.toDomain
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.DogModel
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Repository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.repository.DogRepositoryInterface
import javax.inject.Inject

/*
Clase que devuelve los datos, a partir de un acceso al servicio.
El servicio, devuelve los datos de forma nativa.

Esta clase, se encargará de adaptar dichos datos a los que necesite
la aplicación.

Para Room
1.- Añadimos la dependencia del dao.
 */


class DogRepository @Inject constructor(
    private val service : DogService,
    private val dogDao : DogDao
) : DogRepositoryInterface {
    //private val service : DogService = DogService()

    /*
    Método que a partir de los datos nativos, devuelve la lista
    de objetos que necesita el modelo.
     */
    override fun getDogs(): List<DogModel> {
        var mutableDogs : MutableList<DogModel> = mutableListOf()
        val dataSource = service.getDogs()
       // var id: Int = 0
        dataSource.forEach{ dog->
            mutableDogs.add(DogModel(dog.first, dog.second))
        //    id++
        }
        Repository.dogs = mutableDogs //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return Repository.dogs
    }

    override fun getBreedDogs(breed: String): List<DogModel> {
        var mutableDogs : MutableList<DogModel> = mutableListOf()
        val dataSource = service.getBreedDogs(breed)

        dataSource.forEach{ dog->
            mutableDogs.add(DogModel(dog.first, dog.second))

        }
        Repository.dogs = mutableDogs //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return Repository.dogs
    }

    override suspend fun getDogsEntity(): List<DogModel> {
        val listEntity : List<DogEntity> = dogDao.getAll()  //aquí tengo todos los datos Dog
        Repository.dogs = listEntity.map { it.toDomain()}  //convertimos a DogModel (dominio) y lo cargamos en memoria
       return Repository.dogs

    }

    override suspend fun getBreedDogsEntity(breed: String): List<DogModel> {
        val listEntity : List<DogEntity> = dogDao.getDogsByBreed(breed)  //aquí tengo todos los datos Entity filtrados
        Repository.dogs = listEntity.map { it.toDomain()}  //convertimos a DogModel (dominio) y lo cargamos en memoria
        return Repository.dogs

    }

    /*
    Necesito un método que inserte una lista de Entity en la BBDD.
     */
    override suspend fun insertBreedEntitytoDatabase(listEntity : List<DogEntity>) {
        dogDao.insertAllDog(listEntity)
    }

    override suspend fun deleteDatabase() {
        dogDao.deleteAll()
    }

}