package com.santi.pmdm.virgen.dogapicleanarchitecture.ui.modelview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.repository.InMemoryDogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsBreedUseCase
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
viewModelScope.launch, es la forma de crear una corrutinas. Son tareas
que se hacen en segundo plano, cuando requieren de operaciones asíncronas.
Es conveniente utilizarlas, para no bloquear a la aplicación. En este ejemplo,
no es necesario, ya que simulamos un acceso a memoria.

Será totalmente necesarias utilizar las corrutinas, en operaciones de datos
por internet.
 */
class DogViewModel : ViewModel() {
    var dogListLiveData = MutableLiveData<List<Dog>>() //repositorio observable
    var progressBarLiveData = MutableLiveData<Boolean> () //progressbar observable
    var search = MutableLiveData<String>() //para el campo search
    val repository = InMemoryDogRepository()
    val useCaseList = GetDogsUseCase( repository)
    val useCaseBreedList =  GetDogsBreedUseCase(repository)


    /*
    Un viewModelScopy.launch, lanza una nueva corrutina ejecutándose en el contexto
    del viewModelScope  --> hilo principal. De esa manera, facilita sin problemas la actualización
    de la IU.

    Cuando se hace un withContext dentro de una corrutina, lo que hacemos es realizar un cambio
    de contexto y hasta que ese bloque dentro del sitchContext no se complete, la corrutina permanece
    bloqueada, pero deja al hilo totalmente libre para que la UI sigua trabajando sin problemas.
     */
    fun list() {
        viewModelScope.launch {  //Se destruye al eliminarse el ViewModel
            progressBarLiveData.value = true //notifico
           // delay(2000)
            var data : List<Dog>?
            withContext(Dispatchers.IO){
                data  = useCaseList()  //aquí se invoca y se obtienen los datos.
            }
            //Se suspende este flujo, a la espera de la operación asíncrona en otro hilo.
            //La UI puede seguir trabajando sin problemas.
            if (data !=null )
                dogListLiveData.value = data!!  //notifico
            progressBarLiveData.value = false  //notifico
        }
    }


    //otra forma de implementar la función anterior.
    fun list1() {
        viewModelScope.launch {  //Se destruye al eliminarse el ViewModel
            progressBarLiveData.value = true //notifico
           // delay(2000)
            var data : List<Dog>?
            withContext(Dispatchers.IO){
                data  = useCaseList()  //aquí se invoca y se obtienen los datos.
                if (data !=null )
                    dogListLiveData.postValue(data!!)  //notifico
                progressBarLiveData.postValue(false)
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
           // delay(2000)
            var data : List<Dog>?
            withContext(Dispatchers.IO){
                useCaseBreedList.breed = breed
                data = useCaseBreedList()  //aquí se invoca y se obtienen los datos.
            }
            if (data != null)
                dogListLiveData.value = data!!  //notifico
            progressBarLiveData.value = false  //notifico

        }
    }

}