package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.repository

import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog

/*
Contrato que deberán adquirir las clases Repository dependiendo del tipo de acceso. De momento, es a memoria.
//Desacoplamiento.
 */
interface DogRepositoryInterface {
    fun getDogs() : List<Dog>   //deberá devolver una lista de Dog (dominio)
    fun getBreedDogs(breed:String) : List<Dog>  //deberá devolver una lista por raza de Dog (dominio)
}