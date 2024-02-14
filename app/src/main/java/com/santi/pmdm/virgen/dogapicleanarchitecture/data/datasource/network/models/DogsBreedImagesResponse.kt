package com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.network.models

/*
Esta clase contiene la respuesta de todas las imágenes de Dogs por raza.
 */

/*
endpoint https://dog.ceo/api/breed/hound/images

el json que devuelve será algo como:
{
    "message": [
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1023.jpg",
         ],
    "status": "success"
}
Si nos damos cuenta, message es una lista de String.
 */
data class DogsBreedImagesResponse(
    val status : String,
    val message : List<String>
)
