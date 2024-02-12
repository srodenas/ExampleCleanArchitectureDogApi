package com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.network.models

/*
DogsResponse: contendrá el status de la respuesta y un Map con una lista
de imagenes String por clave raza.
Aquí tenemos la lista completa.
 */
data class DogsResponse(
    val status : String,
    val message : Map<String, List<String>>
)