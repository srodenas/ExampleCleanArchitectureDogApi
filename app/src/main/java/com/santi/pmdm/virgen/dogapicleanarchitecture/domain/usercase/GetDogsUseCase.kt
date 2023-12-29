package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.models.DogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.model.Dog

class GetDogsUseCase(private val dogRepository : DogRepository) {

    operator fun invoke(): List<Dog>?{
        return dogRepository.getDogs()
    }
}