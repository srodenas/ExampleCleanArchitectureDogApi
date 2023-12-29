package com.pmdm.virgen.dogapi.test

import android.util.Log
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.models.DogRepository

class TestApi {

    companion object {

         fun testDogApi() {
            val listDogs = DogRepository().getDogs()

            if (listDogs.isNotEmpty()) {
                listDogs.forEach() {
                    Log.i("TAG-DOGS", it.image)
                }
            } else {
                Log.i("TAG-ERROR", "No hay perros para mostrar")
            }
        }
    }
}