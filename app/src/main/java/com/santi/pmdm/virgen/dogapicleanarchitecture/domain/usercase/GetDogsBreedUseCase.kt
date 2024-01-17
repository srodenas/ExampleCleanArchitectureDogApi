package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.DogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.mem.models.Dog
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
    operator fun invoke() : List<Dog>{
        return dogRepositoryDao.getBreedDogs(breed)
    }

}