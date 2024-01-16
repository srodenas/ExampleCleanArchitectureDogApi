package com.santi.pmdm.virgen.dogapicleanarchitecture.data.models

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.models.Dog


/*
Este será nuestro modelo de datos.
 */
class Repository {
    companion object{
        var dogs:List<Dog> = emptyList()
    }
}