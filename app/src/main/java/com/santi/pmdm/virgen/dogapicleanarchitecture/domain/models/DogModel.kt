package com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models

import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.entities.DogEntity
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.mem.models.Dog

/*
Este será nuestro modelo en el dominio. Este modelo es totalmente independiente a los dos modos de
acceso a datos.
 */
data class DogModel (val breed: String, val image: String)