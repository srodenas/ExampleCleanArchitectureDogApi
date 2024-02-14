package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.mapper.toDomain
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.DogModel
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.DogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Repository
import javax.inject.Inject

class GetDogsFromApiUseCase @Inject constructor(
    val dogRepository: DogRepository
){

   /* suspend operator fun invoke() : List<DogModel>?{
        Repository.dogs = dogRepository.getDogsEntity()
        if (Repository.dogs.isEmpty()){
            Repository.dogs = dogRepository.getDogsApi()
            val dataDogEntity : List<DogEntity> = Repository.dogs.map { it.toDomain() }  //lo mapeamos a Entity.
            dogRepository.insertBreedEntitytoDatabase(dataDogEntity)

        }
        return Repository.dogs
    }

    */
   /*
         Este caso de uso, es para invocar las razas dentro de un spinner.
          */
    suspend operator fun invoke() : List<DogModel>?{

        Repository.dogs = dogRepository.getDogsApi()
        val dataDogEntity : List<DogEntity> = Repository.dogs.map { it.toDomain() }  //lo mapeamos a Entity.

        return Repository.dogs
    }
}