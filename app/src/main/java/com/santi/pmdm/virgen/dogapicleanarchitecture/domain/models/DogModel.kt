package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.mem.models.Dog

data class DogModel (val breed: String, val image: String)