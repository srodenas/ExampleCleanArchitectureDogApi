package com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.network.models

/*
Esta clase contiene la respuesta de todas las imágenes de Dogs por raza.
 */
data class DogsBreedImagesResponse(
    val status : String,
    val message : List<String>
)
