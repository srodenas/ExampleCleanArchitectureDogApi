package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.models.DogRepositoryDaoDao
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.models.Dog
import javax.inject.Inject

/*
Con @Inyect constructor(), estamos diciendo que esa clase ya se puede inyectar.
Necesito la raza.
 */
class GetDogsBreedUseCase @Inject constructor(
    private val dogRepositoryDao: DogRepositoryDaoDao
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