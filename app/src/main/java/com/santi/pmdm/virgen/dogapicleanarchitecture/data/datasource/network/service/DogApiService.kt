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


/*
Los servicios, en vez de devolver directamente la respuesta, DogsResponse o DogsBreedImagesResponse,
lo que devuelvo es un objeto Result de la respuesta. ¿por qué?
    1.- Porque quiero devolver una misma respuesta, en caso de éxito que en caso de algún tipo de error.
    2.- De esta forma, manejo los errores desde donde llamo al Servicio de una manera muy estructurada.

    El funcionamiento es que en caso de que la respuesta sea satisfactoria, cojo encapsulo el body
    dentro del Result. Esto lo hacemos con el método success. En caso contrario, lo que hacemos es crearnos
    nuestro Result, pero añadiéndole el tipo de error causado. al método failure, le pasamos una instancia
    de la excepción que se ha podido producir. No queremos capturar la excepción y tampoco propagarlo, sino
    tratar la excepción como un aviso de error. NO LANZAMOS LA EXCEPCIÓN, SÓLO QUIERO ENCAPSULAR EL TIPO DE ERROR.

    Al ser @Singleton, Hilt sólo creará una instancia.

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
            return Result.failure(e) //Al generarse la excepción, lo que hacemos es volver a encapsular dicha excepción.
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