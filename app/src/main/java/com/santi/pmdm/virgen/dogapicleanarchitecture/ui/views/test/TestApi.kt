package com.pmdm.virgen.dogapi.test

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.mem.models.Dog
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.DogModel
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsBreedUseCase
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class TestApi @Inject constructor(
    private val useCaseList:GetDogsUseCase,
    private val getDogsBreedUseCaseProvider : Provider<GetDogsBreedUseCase>
){



    suspend fun testDogApi() {
        var listDogs: List<DogModel>? = useCaseList()  //aquí se invoca y se obtienen los datos.
            listDogs?.forEach() {
                Log.i("TAG-DOGS", it.image)
            }


        val useCaseBreedList = getDogsBreedUseCaseProvider.get()
        useCaseBreedList.setBreed("raza1") //invoca a su método definido como operator fun invoke()
        var listDogsBreed : List<DogModel> ? = useCaseBreedList()
        listDogsBreed?.forEach() {
            Log.i("TAG-DOGS", it.image)
        }

    }

}