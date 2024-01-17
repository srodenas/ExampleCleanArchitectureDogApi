package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.DogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.mem.models.Dog
import javax.inject.Inject

/*
Con @Inyect constructor(), estamos diciendo que esa clase ya se puede inyectar.
También inyectaremos el repositorio
 */


class GetDogsUseCase @Inject constructor(
    private val dogRepositoryDao: DogRepository
) {

   // private val dogRepository = DogRepository()
    operator fun invoke(): List<Dog>?{
        return dogRepositoryDao.getDogs()
    }
}