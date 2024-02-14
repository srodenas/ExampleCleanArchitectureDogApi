package com.santi.pmdm.virgen.dogapicleanarchitecture.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmdm.virgen.dogapi.test.TestApi
import com.pmdm.virgen.dogapi.ui.adapter.DogAdapter
import com.santi.pmdm.virgen.dogapicleanarchitecture.R
import com.santi.pmdm.virgen.dogapicleanarchitecture.databinding.ActivityMainBinding
import com.santi.pmdm.virgen.dogapicleanarchitecture.ui.modelview.DogViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
@author santiago rodenas herráiz
srodher115@g.educaand.es

La idea de esta versión, es introducir un acceso a datos por API.
Tenemos dos endpoint.
1.- Devolvemos todas las listas de perros y subperros.
2.- Devolvemos todas las imagenes de una raza de perro determinada.

Este ejemplo, está sin terminar porque la idea es cargar todas las razas y subrazas
en un spinner y de ahí bien al seleccionar llamamos al segundo endpoint y seguimos dejando
nuestro edittext igual.

Lo que he hecho, es sólo cargar las imágenes predeterminadas de la raza hound y que la búsqueda
me cargue aquellas imágenes de razas que escribamos.

Para terminarlo, sólo abria que modificar tanto el Activity como el viewmodel, para que cargara
la lista de todos los perros en un spinner.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    @Inject lateinit var testApi: TestApi
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: DogAdapter
    val dogViewModel: DogViewModel by viewModels() //tiene que ser constante.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mySearch.setOnQueryTextListener(this)  //cargamos el listener
        registerLiveData()  //Observamos cambios.
        loadDada() //se cargan todos los datos.
        initRecyclerView()  //inicializamos el recyclerView.
        initEvent()
        //test()
    }

    private fun initEvent() {
        binding.btnDelete.setOnClickListener{
            dogViewModel.delete()
        }
    }

    /*
    Ejemplo de cargar una raza por defecto. La raza es hound.
     */
    private fun loadDada() {
       // dogViewModel.list()  //simulamos un evento para iniciar la carga de datos desde el viewmodel
        dogViewModel.listForBreed("hound") //mostramos por defecto de una raza.
    }

    /*
    Quiero suscribir al activity, cuando los datos de dogListLiveData,
    cambién. En el momento que haya ese cambio, el ViewModel notificará
    al activity y ejecutará la lambda.
     */
    private fun registerLiveData() {
        dogViewModel.dogListLiveData.observe(
            this, {  myList->
                //Aquí hacemos la actualización del adapter.
                adapter.dogRepository = myList!!  //aseguro los datos.
                binding.myRecyclerPpal.adapter = adapter  //le asigno el adapter.
                adapter.notifyDataSetChanged()  //No hace falta, pero por si acaso.
            })

        /*
        Cuando exista un cambio en el modelo, quiero que el Activity
        sea notificado para que actualize la ui.
         */
        dogViewModel.progressBarLiveData.observe(
            this, { visible ->
                binding.progressBar.isVisible = visible
                Log.i("TAG-DOGS","ProgressBar esta $visible")            }
        )

        /*
        Observamos un cambio en el search.
         */
        dogViewModel.breed.observe(  //el campo search, ha cambiado
            this, { bread->
                dogViewModel.listForBreed(bread)  //cambiamos los datos.
                hideKeyBoard()

            }
        )
    }

    /*
    Pone todo en funcionamiento
     */
    private fun initRecyclerView(){
        binding.myRecyclerPpal.layoutManager = LinearLayoutManager(this)
        adapter = DogAdapter()
    }


    /*
    Este método, es llamado cuando se escribe algo en el campo y se pulsa.
     */
    override fun onQueryTextSubmit(query: String?): Boolean {

        if (!query.isNullOrEmpty())
            dogViewModel.searchByBreed(query!!)
        return true
    }

    /*
    Cualquier cambio, llamará a este método. Estoy obligado a ponerlo
    Principalmente, lo utilizo para cargar toda la lista de nuevo, al
    estar el campo vacío.
     */
    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()) {
   //         dogViewModel.list()
            dogViewModel.listForBreed("hound") //mostramos por defecto de una raza.
            hideKeyBoard()
        }
        return true
    }



/*
Método que cierra el teclado. MUY INTERESANTE...
 */
    private fun hideKeyBoard() {
        val imn = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow(binding.myLayoutPpal.windowToken, 0)
    }

    private fun test() {
        lifecycleScope.launch{
            testApi.testDogApi()
        }

    }
}