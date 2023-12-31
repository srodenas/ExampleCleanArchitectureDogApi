package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.models.DogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.model.Dog

/*
recibo el repositorio como privado y la raza a filtar.
Invoca al repositorio de datos, por filtro.
 */
class GetDogsBreedUseCase(val dogRepository : DogRepository,
                          val breed: String) {

    operator fun invoke() : List<Dog>{
        return dogRepository.getBreedDogs(breed)
    }

}