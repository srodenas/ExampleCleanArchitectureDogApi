package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.repository.InMemoryDogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog

/*
recibo el repositorio como privado y la raza a filtar.
Invoca al repositorio de datos, por filtro.
 */
class GetDogsBreedUseCase(val inMemoryDogRepository : InMemoryDogRepository,
                          val breed: String) {

    operator fun invoke() : List<Dog>{
        return inMemoryDogRepository.getBreedDogs(breed)
    }

}