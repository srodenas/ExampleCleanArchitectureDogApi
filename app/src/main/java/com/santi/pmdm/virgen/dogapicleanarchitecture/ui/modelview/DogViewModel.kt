package com.santi.pmdm.virgen.dogapicleanarchitecture.ui.modelview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.models.DogRepository
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.model.Dog
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsUseCase

class DogViewModel : ViewModel() {
    var dogListLiveData = MutableLiveData<List<Dog>>() //repositorio observable
    var progressBarLiveData = MutableLiveData<Boolean> () //progressbar observable
    lateinit var useCaseList : GetDogsUseCase

    init {
        //Inicializamos todo el ViewModel
        progressBarLiveData.postValue(true)  //notificamos un cambio
        useCaseList = GetDogsUseCase(DogRepository())
        var data : List<Dog> ? = useCaseList()  //aquí se invoca y se obtienen los datos.
        data.let {
            dogListLiveData.postValue(it)  //aquí están los datos. Hay que notificar a la interfaz.
            progressBarLiveData.postValue(false) //notificamos a la interfaz, del cambio.
        }

    }
}