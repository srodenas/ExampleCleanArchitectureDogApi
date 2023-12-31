package com.pmdm.virgen.dogapi.test

import android.util.Log
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.models.DogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsBreedUseCase
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsUseCase

class TestApi {

    companion object {

         fun testDogApi() {
             var useCase = GetDogsUseCase(DogRepository())
             var useCaseBreed = GetDogsBreedUseCase(DogRepository(),"raza1")
             val listDogs = useCaseBreed() //invoca a su método definido como operator fun invoke()

             listDogs?.forEach() {
                 Log.i("TAG-DOGS", it.image)
             }

        }
    }
}