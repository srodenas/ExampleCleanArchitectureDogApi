package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.models.DogRepositoryDaoDao
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.models.Dog
import javax.inject.Inject

/*
Con @Inyect constructor(), estamos diciendo que esa clase ya se puede inyectar.
También inyectaremos el repositorio
 */


class GetDogsUseCase @Inject constructor(
    private val dogRepositoryDao: DogRepositoryDaoDao
) {

   // private val dogRepository = DogRepository()
    operator fun invoke(): List<Dog>?{
        return dogRepositoryDao.getDogs()
    }
}