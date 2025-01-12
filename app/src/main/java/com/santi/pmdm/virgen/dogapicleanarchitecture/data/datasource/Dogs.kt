package com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource

/*
1.-Fuente de datos en memoria.
2.-Simulamos nuestros datos como una lista de Pair("raza", "url_imagen").
La idea de este tipo de datos, es simular que la fuente de datos es totalmente diferente a los modelos con
los que cuenta en dominio.
 */
object Dogs{
    val dogs : List<Pair<String, String>> = listOf(
        Pair("raza1", "https://images.dog.ceo/breeds/bulldog-boston/n02096585_10823.jpg"),
        Pair("raza2","https://images.dog.ceo/breeds/mountain-swiss/n02107574_97.jpg"),
        Pair("raza3","https://images.dog.ceo/breeds/terrier-wheaten/n02098105_1009.jpg"),
        Pair("raza4", "https://images.dog.ceo/breeds/entlebucher/n02108000_99.jpg"),
        Pair("raza5","https://images.dog.ceo/breeds/mastiff-english/3.jpg")
    )
}
