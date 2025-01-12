package com.santi.pmdm.virgen.dogapicleanarchitecture.data.models

import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog


/*
Este será nuestro modelo de datos ya cargado. Aquí están todos los datos.

        DogsData.dogs es nuestra lista de datos
 */
class DogsData {
    companion object{
        var dogs:List<Dog> = emptyList()
    }
}