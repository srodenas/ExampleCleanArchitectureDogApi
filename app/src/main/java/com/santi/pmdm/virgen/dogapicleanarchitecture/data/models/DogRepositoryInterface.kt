package com.santi.pmdm.virgen.dogapicleanarchitecture.data.models

import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.model.Dog

interface DogRepositoryInterface {
    fun getDogs() : List<Dog>
}