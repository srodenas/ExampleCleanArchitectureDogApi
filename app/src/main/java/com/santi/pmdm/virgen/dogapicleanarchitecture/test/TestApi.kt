package com.pmdm.virgen.dogapi.test

import android.util.Log
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.repository.InMemoryDogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsBreedUseCase
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsUseCase

class TestApi {

    companion object {

        /* fun testDogApi() {
             var useCase = GetDogsUseCase(InMemoryDogRepository())
             var useCaseBreed = GetDogsBreedUseCase(InMemoryDogRepository(),"raza1")
             val listDogs = useCaseBreed() //invoca a su método definido como operator fun invoke()

             listDogs?.forEach() {
                 Log.i("TAG-DOGS", it.image)
             }

        }

         */
    }
}