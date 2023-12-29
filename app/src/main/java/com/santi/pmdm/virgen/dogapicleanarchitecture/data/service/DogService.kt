package com.santi.pmdm.virgen.dogapicleanarchitecture.data.service

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.Dogs

/*
Esta clase, simula el servicio de acceso a los datos nativos.
 */
class DogService : DogServiceInterface{
    override fun getDogs(): List<String> {
        return Dogs.dogs
    }

}