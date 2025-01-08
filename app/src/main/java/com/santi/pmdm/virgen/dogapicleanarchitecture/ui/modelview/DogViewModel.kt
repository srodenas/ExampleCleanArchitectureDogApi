package com.santi.pmdm.virgen.dogapicleanarchitecture.ui.modelview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.repository.InMemoryDogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsBreedUseCase
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
viewModelScope.launch, es la forma de crear una corrutinas. Son tareas
que se hacen en segundo plano, cuando requieren de operaciones asíncronas.
Es conveniente utilizarlas, para no bloquear a la aplicación. En este ejemplo,
no es necesario, ya que simulamos un acceso a memoria. Como queremos utilizar un
progressbar por un tiempo, no tengo mas remedio que lanzarlo como si fuera una
operación asíncrona. De esta forma, la operación se duerme durante un tiempo
mientras la interfaz va funcionando por otro lado.

Será totalmente necesarias utilizar las corrutinas, en operaciones de datos
por internet.
 */
class DogViewModel : ViewModel() {


    var dogListLiveData = MutableLiveData<List<Dog>>() //repositorio observable
    var progressBarLiveData = MutableLiveData<Boolean> () //progressbar observable
    var search = MutableLiveData<String>() //para el campo search

    lateinit var useCaseList : GetDogsUseCase
    lateinit var useCaseBreedList : GetDogsBreedUseCase


    fun list() {
        viewModelScope.launch {
            progressBarLiveData.value = true //notifico
            delay(2000)
            useCaseList = GetDogsUseCase(InMemoryDogRepository())
            var data : List<Dog> ? = useCaseList()  //aquí se invoca y se obtienen los datos.
            data.let {
                dogListLiveData.value = it  //notifico
                progressBarLiveData.value = false  //notifico
            }
        }
    }

     fun searchByBreed(breed: String){
         //Log.i("TAG-DOGS", "La raza elegida es $breed")
        search.value = breed  //notificamos cambio
    }

    fun listForBreed(breed: String) {
        viewModelScope.launch {
            progressBarLiveData.value = true //notifico
            delay(2000)
            useCaseBreedList = GetDogsBreedUseCase(InMemoryDogRepository(), breed)
            var data : List<Dog> ? = useCaseBreedList()  //aquí se invoca y se obtienen los datos.
            data.let {
                dogListLiveData.value = it  //notifico
                progressBarLiveData.value = false  //notifico
            }
        }
    }

}