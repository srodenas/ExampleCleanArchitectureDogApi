package com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.network.service

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.network.models.DogsBreedImagesResponse
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.network.models.DogsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


/*
Interfaz que define los servicios a nuestra API.
1.- Devolver todos los Dogs de la API en forma de DogsResponse
    DogsResponse: contendrá el status de la respuesta y un Map con una lista
  de imagenes String por clave raza.
2.- Devolver todas las imágenes Dogs de la API como DogsImagesResponseForBreed
    DogsImagesResponseForBreed, contendrá el status de la respuesta y una lista
    de imágenes String. Para este caso necesitamos modificar la url.
 */
interface DogApiServiceInterface {

    @GET("breed/list/all")
    fun getAllDogsApi() : Response<DogsResponse>


    @GET("breed/{breed}/images")
    fun getAllImagesApi(breed:String): Response<DogsBreedImagesResponse>

}