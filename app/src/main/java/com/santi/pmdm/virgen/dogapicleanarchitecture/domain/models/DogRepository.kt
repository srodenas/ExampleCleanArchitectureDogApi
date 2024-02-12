package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.dao.DogDao
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.mem.service.DogService
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.network.service.DogApiService
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.mapper.toDomain
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
    private val dogDao : DogDao,
    private val dogApiService : DogApiService
) : DogRepositoryInterface {

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
      //  Repository.dogs = mutableDogs //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return mutableDogs
    }

    override fun getBreedDogs(breed: String): List<DogModel> {
        var mutableDogs : MutableList<DogModel> = mutableListOf()
        val dataSource = service.getBreedDogs(breed)

        dataSource.forEach{ dog->
            mutableDogs.add(DogModel(dog.first, dog.second))
        }
      //  Repository.dogs = mutableDogs //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return mutableDogs
    }

    override suspend fun getDogsEntity(): List<DogModel> {
        val listEntity : List<DogEntity> = dogDao.getAll()  //aquí tengo todos los datos Dog
        return  listEntity.map { it.toDomain()}  //convertimos a DogModel (dominio) y lo cargamos en memoria
    }

    override suspend fun getBreedDogsEntity(breed: String): List<DogModel> {
        val listEntity : List<DogEntity> = dogDao.getDogsByBreed(breed)  //aquí tengo todos los datos Entity filtrados
        return listEntity.map { it.toDomain()}  //convertimos a DogModel (dominio) y lo cargamos en memoria
     //   return Repository.dogs

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


    /*
    Este método, recibe la respuesta de Retrofit y debe
    encapsular los datos en DogModel
     */
    override suspend fun getDogsApi(): List<DogModel> {
        val result = dogApiService.getAllDogsApiService()
        result
            .onSuccess { 
                dogResponse ->  return convertMapToListDogs(dogResponse.message)
            }
            .onFailure { 
                exception -> println("Error en la excepcion ${exception.message}")
            }
        return emptyList()
    }


    override suspend fun getBreedDogsApi(breed: String): List<DogModel> {
        val result = dogApiService.getAllImagesService(breed)
        result
            .onSuccess {
                dogResponse -> return convertListToListDogs(breed,dogResponse.message)
            }
            .onFailure {
                exception -> println("Error en la excepcion ${exception.message}")
                return emptyList()
            }
        return emptyList()
    }



    private fun convertMapToListDogs(dogResponse: Map<String,List<String>>): List<DogModel> {
        var listDogs : MutableList<DogModel> = mutableListOf() //lista vacía
        dogResponse.map { (breed, dogs) ->
            dogs.forEach{
                dog-> listDogs.add(DogModel(breed, dog))
            }
        }
       // Repository.dogs = listDogs
        return listDogs
    }

    private fun convertListToListDogs(breed: String, dogResponse: List<String>): List<DogModel> {
        var listDogs : MutableList<DogModel> = mutableListOf()
        dogResponse.map { dog-> listDogs.add(DogModel(breed, dog)) }
      //  Repository.dogs = listDogs
        return listDogs
    }

}