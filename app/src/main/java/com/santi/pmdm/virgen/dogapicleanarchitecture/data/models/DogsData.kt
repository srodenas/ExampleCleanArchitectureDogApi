package com.santi.pmdm.virgen.dogapicleanarchitecture.data.models

import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog


/*
Este será nuestro modelo de datos.
 */
class DogsData {
    companion object{
        var dogs:List<Dog> = emptyList()
    }
}