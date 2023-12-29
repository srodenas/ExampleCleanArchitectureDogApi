package com.santi.pmdm.virgen.dogapicleanarchitecture.data.service


/*
Interface que deben de implementar todos los servicios.
 */
interface DogServiceInterface {
    fun getDogs(): List<String>
}