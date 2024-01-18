package com.santi.pmdm.virgen.dogapicleanarchitecture.ui.modelview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.mem.models.Dog
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.DogModel
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.DeleteDogsFromDataBaseUseCase
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsBreedUseCase
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.usercase.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

/*
* Autor: santiago rodenas herráiz
* Email: srodher115@g.educaand.es

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
/*
Con la anotación siguiente:
1.- Preparamos para que podamos inyectar aquí.
2.- Modificamos el constructor de nuestro DogViewModel con
los objetos que necesitamos.
3.- Necesito un Provider para el segundo caso de uso, porque dicha clase que debe inyectar,
antes de crearse el objeto, necesito parsarle un parámetro. Cada vez que llamamos al método get() dentro
del Provider, se está creando una nueva instancia de GetDogsBreedUseCase. Hilt, creará inyecta
el Proveedor de GetDogsBreedUseCase, y Hilt creará autormáticamente un objeto de tipo GetDogsBreedUseCAse
cuando se llame al método get del provider. De esta forma, aseguramos que se cumple todo el ciclo de vida.
 */
@HiltViewModel
class DogViewModel @Inject constructor(
    private val useCaseList : GetDogsUseCase,
    private val getDogsBreedUseCaseProvider: Provider<GetDogsBreedUseCase>,
    private val userCaseDeleteDatabase : DeleteDogsFromDataBaseUseCase

): ViewModel() {


    var dogListLiveData = MutableLiveData<List<DogModel>>() //repositorio observable
    var progressBarLiveData = MutableLiveData<Boolean> () //progressbar observable
    var breed = MutableLiveData<String>() //para el campo search con la raza.

    /*
    Queremos que estas dos clases, se inyecten directamente, es decir que no aparezcan aquí.
     */
   // lateinit var useCaseList : GetDogsUseCase
 //   lateinit var useCaseBreedList : GetDogsBreedUseCase


    fun list() {
        viewModelScope.launch {
            progressBarLiveData.value = true //notifico
            delay(500)
           // useCaseList = GetDogsUseCase()  //Ya no me hace falta, porque se crea por Hilt.
            var data : List<DogModel> ? = useCaseList()  //aquí se invoca y se obtienen los datos.
            data.let {
                dogListLiveData.value = it  //notifico
                progressBarLiveData.value = false  //notifico
            }
        }
    }

     fun searchByBreed(breed: String){
         //Log.i("TAG-DOGS", "La raza elegida es $breed")
        this.breed.value = breed  //notificamos cambio
    }

    fun listForBreed(breed: String) {
        viewModelScope.launch {
            progressBarLiveData.value = true //notifico
            delay(500)
           // useCaseBreedList = GetDogsBreedUseCase( breed)
            val useCaseBreedList = getDogsBreedUseCaseProvider.get()  //aquí se crea realmente la instancia dento del Provider.
            useCaseBreedList.setBreed(breed) //Aquí le paso el parámetro que necesitaba el caso de uso.
            var data : List<DogModel> ? = useCaseBreedList()  //aquí se invoca y se obtienen los datos.
            data.let {
                dogListLiveData.value = it  //notifico
                progressBarLiveData.value = false  //notifico
            }
        }
    }

    /*
    Este caso de uso, es para eliminar los datos de la base de datos.
     */
    fun delete() {
        viewModelScope.launch {
            userCaseDeleteDatabase() //si invocamos para borrar la base de datos.
            list() //Vuelvo a cargar los datos desde Dogs.
        }
    }

}