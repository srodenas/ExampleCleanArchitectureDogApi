package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.repository.DogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.mapper.toDomain
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.DogModel
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Repository
import javax.inject.Inject

/*
Con @Inyect constructor(), estamos diciendo que esa clase ya se puede inyectar.
Necesito la raza.
 */
class GetDogsBreedUseCase @Inject constructor(
    private val dogRepositoryDao: DogRepository
){
    private var breed: String = ""
    //private val dogRepository = DogRepository()

    fun setBreed(breed: String){
        this.breed = breed
    }


    suspend operator fun invoke() : List<DogModel>{
        Repository.dogs = dogRepositoryDao.getBreedDogsEntity(breed)  //Aquí tengo los datos.

            /*
           1.- Si no tengo nada en la BBDD, lo que haremos es cargarlos de memoria
           e insertarlo directamente en la BBDD.
           2.- Para ello, tengo que insertarlos en la BBDD, pero no como DogModel, sino como DogEntity, por tanto
           utilizo otro mapper.
           3.- Con los datos mapeados a DogEntity, ahora lo insertaremos en la BBDD.
            */
        if (Repository.dogs.isEmpty()){
            Repository.dogs  = dogRepositoryDao.getBreedDogs(breed) //Aquí tengo los datos de memoria.
            val dataDogEntity : List<DogEntity> = Repository.dogs.map { it.toDomain() }  //lo mapeamos a Entity.
            dogRepositoryDao.insertBreedEntitytoDatabase(dataDogEntity)
        }
        return  return Repository.dogs

    }

}