package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.network.service.DogApiService
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.mapper.toDomain
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.DogModel
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.DogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Repository
import javax.inject.Inject

class GetDogsBreedFromApiUseCase @Inject constructor(
    val dogRepository: DogRepository
){
    private var breed : String = ""

    fun setBreed(breed : String){
        this.breed = breed
    }

    suspend operator fun invoke(): List<DogModel>{
        Repository.dogs = dogRepository.getBreedDogsEntity(breed)
        if (Repository.dogs.isEmpty()){
            Repository.dogs = dogRepository.getBreedDogsApi(breed)
            val dataDogEntity : List<DogEntity> = Repository.dogs.map { it.toDomain() }  //lo mapeamos a Entity.
            dogRepository.insertBreedEntitytoDatabase(dataDogEntity)
        }
        return Repository.dogs
    }


}