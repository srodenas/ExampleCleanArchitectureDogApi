package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.repository.InMemoryDogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog

class GetDogsUseCase(private val inMemoryDogRepository : InMemoryDogRepository) {

    operator fun invoke(): List<Dog>?{
        return inMemoryDogRepository.getDogs()
    }
}