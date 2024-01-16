package com.pmdm.virgen.dogapi.test

import android.util.Log
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.models.Dog
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsBreedUseCase
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsUseCase
import javax.inject.Inject
import javax.inject.Provider

class TestApi @Inject constructor(
    private val useCaseList:GetDogsUseCase,
    private val getDogsBreedUseCaseProvider : Provider<GetDogsBreedUseCase>
){



    fun testDogApi() {
        var listDogs: List<Dog> ? = useCaseList()  //aquí se invoca y se obtienen los datos.
        listDogs?.forEach() {
            Log.i("TAG-DOGS", it.image)
        }

        val useCaseBreedList = getDogsBreedUseCaseProvider.get()
        useCaseBreedList.setBreed("raza1") //invoca a su método definido como operator fun invoke()
        var listDogsBreed : List<Dog> ? = useCaseBreedList()
        listDogsBreed?.forEach() {
            Log.i("TAG-DOGS", it.image)
        }

    }

}