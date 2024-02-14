package com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.network.models

/*
DogsResponse: contendrá el status de la respuesta y un Map con una lista
de imagenes String por clave raza.
Aquí tenemos la lista completa.
 */

/*
endpoint:   https://dog.ceo/api/breeds/list/all

Ejemplo de JSON:

{
    "message": {
        "australian": [
            "shepherd"
        ],
        "buhund": [
            "norwegian"
        ],
        "bulldog": [
            "boston",
            "english",
            "french"
        ]
    },
    "status": "success"
}

Si nos damos cuenta, message está compuesto de un conjunto de par Clave=>valor, donde valor es una lista de String.
 */
data class DogsResponse(
    val status : String,
    val message : Map<String, List<String>>
)