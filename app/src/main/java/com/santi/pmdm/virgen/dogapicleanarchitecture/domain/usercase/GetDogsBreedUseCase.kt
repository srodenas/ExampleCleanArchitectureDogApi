package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.models.DogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.model.Dog
import javax.inject.Inject

/*
Con @Inyect constructor(), estamos diciendo que esa clase ya se puede inyectar.
Necesito la raza.
 */
class GetDogsBreedUseCase @Inject constructor(
    private val dogRepository: DogRepository
){
    private var breed: String = ""
    //private val dogRepository = DogRepository()

    fun setBreed(breed: String){
        this.breed = breed
    }
    operator fun invoke() : List<Dog>{
        return dogRepository.getBreedDogs(breed)
    }

}