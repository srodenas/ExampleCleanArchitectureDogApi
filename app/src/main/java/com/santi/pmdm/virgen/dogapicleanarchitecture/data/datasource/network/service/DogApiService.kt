package com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.network.service

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.network.models.DogsBreedImagesResponse
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.network.models.DogsResponse
import retrofit2.Response
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Singleton

/*
Clase que implementará todos los métodos para cada servicio.
1.- Necesito una instancia del servicio
2.- Realizará la llamada a cada servicio y devolverá los datos.
 */

@Singleton
class DogApiService @Inject constructor(val apiService: DogApiServiceInterface){

    suspend fun getAllDogsApiService(): Result<DogsResponse> {
        try {
            val response: Response<DogsResponse> = apiService.getAllDogsApi()
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.success(it) //encapsulo la respuesta.
                } ?: return Result.failure(RuntimeException("Respuesta nula sin datos"))
            } else {
                return Result.failure(RuntimeException("Error en la llamada y sin respuesta"))
            }
        } catch(e: Exception){
            return Result.failure(e) //encapsulamos el fallo y devolvemos.
        }
    }


    suspend fun getAllImagesService(breed: String): Result<DogsBreedImagesResponse> {
        try {
            val response  = apiService.getAllImagesApi(breed)
            return if (response.isSuccessful){
                response.body()?.let {
                    Result.success(it)
                }?:Result.failure(RuntimeException("Respuesta nula"))
            } else {
                Result.failure(RuntimeException("Error en la llamada y sin respuesta"))
            }
        }
        catch (e: Exception) {
            return Result.failure(e)
        }

    }
}